import com.google.cloud.ServiceOptions;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.pubsub.v1.ProjectSubscriptionName;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Worker {
    public static String PROJECT_ID = ServiceOptions.getDefaultProjectId();

    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println(PROJECT_ID);
        String hostName = InetAddress.getLocalHost().getHostName();
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Firestore firestore = FirestoreOptions.getDefaultInstance().getService();
        ImageAnnotatorClient vision = ImageAnnotatorClient.create();

        ImageReceiveHandler irh = new ImageReceiveHandler(storage, firestore, vision);
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(PROJECT_ID, "cn-d1-g1-images-subscription");
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, irh).build();
        subscriber.startAsync().awaitRunning();

        TopicHelper th = new TopicHelper(PROJECT_ID);
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        StatsCalculator Scalc = new StatsCalculator();
        final int[] imagesProcessed10Sec = {0};

        ses.scheduleAtFixedRate(() -> {
            double time = 10;
            int Process = irh.getNumberImages() - imagesProcessed10Sec[0];
            double imgTenSecInterval = Process/time;
            String txtMsg = hostName + ":" + imgTenSecInterval;
            th.publishTopicMsg("cn-d1-g1-appmetric", txtMsg);
            System.out.println(txtMsg);
            imagesProcessed10Sec[0] = imagesProcessed10Sec[0]+ Process;
        }, 0, 10, TimeUnit.SECONDS);

        ses.scheduleAtFixedRate(() -> {
            Scalc.calculateStats();
            String txtMsg = hostName + ":" + Scalc.CpuPerc().getCombined()*100 + ":" + Scalc.getUsedRAM();
            th.publishTopicMsg("cn-d1-g1-cpumem", txtMsg);
            System.out.println(txtMsg);

        }, 0, 10, TimeUnit.SECONDS);

        for(;;){
            Thread.sleep(2000);
        }
    }
}
