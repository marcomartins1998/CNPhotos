import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.ComputeScopes;
import com.google.api.services.compute.model.Instance;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import javafx.util.Pair;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Monitor {
    public static String PROJECT_ID = ServiceOptions.getDefaultProjectId();
    public static String INSTANCE_GROUP = "cn-d1-g1-worker-instance-group";
    public static String ZONE_NAME = "europe-west6-a";
    public static double[] DEFAULT_CPU_LIMIT = {20.0, 80.0};
    public static double[] DEFAULT_MEM_LIMIT = {20.0, 60.0};
    public static double[] DEFAULT_IMG_PER_SEC_LIMIT = {0.1, 0.2};
    public static long DEFAULT_ACTION_PERIOD_LIMIT = 30;

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final Object limitMutex = new Object();
        LimitMetrics lm = new LimitMetrics(DEFAULT_CPU_LIMIT, DEFAULT_MEM_LIMIT, DEFAULT_IMG_PER_SEC_LIMIT, DEFAULT_ACTION_PERIOD_LIMIT);

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = GoogleCredential.getApplicationDefault();
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

        if(credential.createScopedRequired()){
            List<String> scopes = new ArrayList<>();
            scopes.add(ComputeScopes.COMPUTE);
            credential = credential.createScoped(scopes);
        }
        Compute computeService = new Compute.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("CNPhotos")
                .build();
        ComputeHelper ch = new ComputeHelper(computeService, PROJECT_ID);
        List<Instance> grouplist = ch.getInstancesInInstanceGroup(INSTANCE_GROUP, ZONE_NAME);
        InstanceGroupMetrics igm = new InstanceGroupMetrics(grouplist);

        //#1 -- Metric Subscriber Setup
        AppMetricReceiveHandler arh = new AppMetricReceiveHandler(igm);
        CpuMemMetricReceiveHandler cmrh = new CpuMemMetricReceiveHandler(igm);
        ProjectSubscriptionName appmetricsub = ProjectSubscriptionName.of(PROJECT_ID, "cn-d1-g1-appmetric-subscription");
        ProjectSubscriptionName cpumemsub = ProjectSubscriptionName.of(PROJECT_ID, "cn-d1-g1-cpumem-subscription");
        Subscriber appsubscriber = Subscriber.newBuilder(appmetricsub, arh).build();
        Subscriber cpumemsubscriber = Subscriber.newBuilder(cpumemsub, cmrh).build();
        appsubscriber.startAsync().awaitRunning();
        cpumemsubscriber.startAsync().awaitRunning();

        //#2 -- Scheduled Action for Adding or Removing Instances
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable task = () -> { synchronized (limitMutex) {
            Map<String, Double> currMetrics = igm.getGlobalMetrics();
            int currGroupSize = 0;
            try {
                currGroupSize = ch.getInstancesInInstanceGroup(INSTANCE_GROUP, ZONE_NAME).size();
                igm.updateGroup(ch.getInstancesInInstanceGroup(INSTANCE_GROUP, ZONE_NAME));
            } catch (IOException e) { e.printStackTrace(); }
            System.out.println("Existing VMs: " + currGroupSize);
            currMetrics.forEach((k, v) -> System.out.println(k + ": " + v.toString()));
            if(currMetrics.get("app") > lm.IMG_PER_SEC_LIMIT[1]){
                try {
                    ch.waitOperation(ch.resizeInstanceGroup(ZONE_NAME, INSTANCE_GROUP, currGroupSize + 1));
                    //igm.updateGroup(ch.getInstancesInInstanceGroup(INSTANCE_GROUP, ZONE_NAME));
                    System.out.println("Added one VM.");
                } catch (Exception e) { System.out.println(e.getMessage()); }
            }
            else if(currMetrics.get("app") < lm.IMG_PER_SEC_LIMIT[0]){
                try {
                    if(currGroupSize != 1) {
                        ch.waitOperation(ch.resizeInstanceGroup(ZONE_NAME, INSTANCE_GROUP, currGroupSize - 1));
                        //igm.updateGroup(ch.getInstancesInInstanceGroup(INSTANCE_GROUP, ZONE_NAME));
                        System.out.println("Deleted one VM.");
                    }
                } catch (Exception e) { System.out.println(e.getMessage()); }
            }
        }};
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task, 5, lm.ACTION_PERIOD_LIMIT, TimeUnit.SECONDS);

        //#3 GRPC Server Setup for Monitoring Management
        Server svc = ServerBuilder
                .forPort(80)
                .addService(new MonitorService(lm, limitMutex, igm, ses, scheduledFuture, task))
                .build()
                .start();
        Logger.getGlobal().info("Server started, listening on " + 80);

        for(;;){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
