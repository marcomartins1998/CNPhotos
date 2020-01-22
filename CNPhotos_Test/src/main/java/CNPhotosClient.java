import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CNPhotosClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        StorageOptions options = StorageOptions.getDefaultInstance();
        String projectId = options.getProjectId();
        Storage storage = options.getService();
        Firestore firestore = FirestoreOptions.getDefaultInstance().getService();
        StorageHelper sh = new StorageHelper(storage);
        TopicHelper th= new TopicHelper(projectId);
        //sh.deleteBlob(storage.get("cn-d1-g1-image-bucket"));      //APAGA TODAS AS IMAGENS DE Batata-Frita........
        ArrayList<String> ImageNames =new ArrayList<>();
        ArrayList<String> docName=new ArrayList<>();
        int[] id ={0};
        int[] count={0};
        int [] ImageCounter={0};
        int[] NrImages={0};
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        Scanner scanner = new Scanner(System.in);

        String helpmsg = "To upload images input: 1";
        System.out.println(helpmsg);

        for (;;){
            final String[] action = {scanner.nextLine()};
            switch (action[0]){
                case "1":
                    System.out.println("Input the directory path where the images that you want to send are stored:");
                    String directory= scanner.nextLine();
                    System.out.println("Input how many images you want to add");
                    NrImages[0]= Integer.parseInt(scanner.nextLine());

                    //String escaped = directory.replace("\\", "\\\\");
                    File folder = new File(directory);
                    File[] listOfFiles = folder.listFiles();


                        exec.scheduleAtFixedRate(new Runnable() {              //Runnable para adicionar Imagens ao bucket de 2 em 2s
                            @Override
                            public void run() {
                                if (NrImages[0] > ImageCounter[0]) {

                                    id[0] = id[0] + 1;
                                    count[0] += 1;
                                    String ImageName = "Image" + id[0];
                                    docName.add(ImageName);                                                 //Identificadores dos documentos na firestore
                                    String bucketName = "cn-d1-g1-image-bucket";
                                    try {
                                        sh.storeImage(ImageName, listOfFiles[count[0]].toString(), bucketName);
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    ImageNames.add(ImageName);                          //Nome dos blobs a eliminar
                                    //System.out.println("Imagem publicada com Id:"+ ImageName);
                                    th.publishTopicMsg("cn-d1-g1-images", ImageName);
                                    ImageCounter[0]++;
                                }
                                else {
                                    System.out.println("If you want to delete the images from the Bucket and labels from Firestore input: 2");
                                    while(true){
                                        action[0] =scanner.nextLine();
                                        break;
                                    }
                                }
                            }

                        }, 0, 3, TimeUnit.SECONDS);

                    break;
                case "2":
                    sh.deleteBlob(storage.get("cn-d1-g1-image-bucket"));
                    //docName.forEach(s-> System.out.println(s));
                    
                   docName
                           .forEach(doc->firestore.collection("cn-d1-g1-images").document(doc).delete());

                    break;
                default:
                    System.out.println(helpmsg);
            }
        }



    }

}
