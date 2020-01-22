import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import monitorstubs.ActionPeriod;
import monitorstubs.MetricLimit;
import monitorstubs.MonitorServiceGrpc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CNPhotosClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        StorageOptions options = StorageOptions.getDefaultInstance();
        String projectId = options.getProjectId();
        Storage storage = options.getService();
        Firestore firestore = FirestoreOptions.getDefaultInstance().getService();
        String bucketName = "cn-d1-g1-image-bucket";
        String monitorIp = null;
        ManagedChannel channel = null;

        StorageHelper sh = new StorageHelper(storage);
        TopicHelper th = new TopicHelper(projectId);
        FirestoreHelper fh = new FirestoreHelper(firestore);
        Scanner scanner = new Scanner(System.in);

        String helpmsg = "To upload an image: /upload <image directory>\n" +
                "To search images by category: /search <category1:category2:etc...>\n" +
                "To interact with th monitor: /monitor";
        System.out.println(helpmsg);

        for (;;){
            String[] line = scanner.nextLine().trim().split(" ", 3);
            switch (line[0]){
                case "/upload":
                    try {
                        System.out.println("Image name:");
                        String imgName = scanner.nextLine().trim();
                        while (sh.getEveryBlobName(bucketName).contains(imgName)) {
                            System.out.println("This name already exists, please chose a different name:");
                            imgName = scanner.nextLine().trim();
                        }

                        String imagename = sh.storeImage(imgName, line[1], bucketName);
                        th.publishTopicMsg("cn-d1-g1-images", imagename);
                    } catch (FileNotFoundException e){ System.out.println(e.getMessage()); }
                    break;
                case "/search":
                    String[] categories = line[1].split(":");
                    List<String> images = fh.getImagesByCategories(Arrays.asList(categories));
                    images.forEach(img -> System.out.println(img + " : " + sh.getLinkByName(img, bucketName)));
                    break;

                case "/monitor":
                    if (monitorIp == null){
                        System.out.println("Monitor IP:");
                        monitorIp = scanner.nextLine().trim();

                        channel = ManagedChannelBuilder
                                .forAddress(monitorIp,80)
                                .usePlaintext()
                                .build();
                    }

                    MonitorServiceGrpc.MonitorServiceStub noBlockStub =
                            MonitorServiceGrpc.newStub(channel);

                    String monitorhelpmsg = "To get number of active VMs: /getinstn\n" +
                            "To get the global status: /getgs\n" +
                            "To set the CPU limit: /setcpul <low%limit> <high%limit>\n" +
                            "To set the MEM limit: /setmeml <low%limit> <high%limit>\n" +
                            "To set the Image per second limit: /setimgpsl <low%limit> <high%limit>\n" +
                            "To set the action period: /setap <valueinseconds>";
                    System.out.println(monitorhelpmsg);
                    line = scanner.nextLine().trim().split(" ", 3);
                    switch (line[0]){
                        case "/getinstn":
                            INStreamObserver inso = new INStreamObserver();
                            noBlockStub.getInstanceNum(Empty.newBuilder().build(), inso);
                            break;
                        case "/getgs":
                            GSStreamObserver gsso = new GSStreamObserver();
                            noBlockStub.getGlobalStatus(Empty.newBuilder().build(), gsso);
                            break;
                        case "/setcpul":
                            noBlockStub.setCpuLimit(MetricLimit
                                    .newBuilder()
                                    .setLowLimit(Double.parseDouble(line[1]))
                                    .setHighLimit(Double.parseDouble(line[2]))
                                    .build(), new EmptyStreamObserver());
                            break;
                        case "/setmeml":
                            noBlockStub.setMemLimit(MetricLimit
                                    .newBuilder()
                                    .setLowLimit(Double.parseDouble(line[1]))
                                    .setHighLimit(Double.parseDouble(line[2]))
                                    .build(), new EmptyStreamObserver());
                            break;
                        case "/setimgpsl":
                            noBlockStub.setImgPerSecLimit(MetricLimit
                                    .newBuilder()
                                    .setLowLimit(Double.parseDouble(line[1]))
                                    .setHighLimit(Double.parseDouble(line[2]))
                                    .build(), new EmptyStreamObserver());
                            break;
                        case "/setap":
                            noBlockStub.setActionPeriod(ActionPeriod
                                    .newBuilder()
                                    .setVal(Long.parseLong(line[1]))
                                    .build(), new EmptyStreamObserver());
                            break;
                    }
                    break;
                default:
                    System.out.println(helpmsg);
            }
        }
    }
}
