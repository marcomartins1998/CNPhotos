import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.Descriptors;
import com.google.pubsub.v1.PubsubMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ImageReceiveHandler implements MessageReceiver {
    private final Storage storage;
    private final Firestore firestore;
    private final ImageAnnotatorClient imageAnnotatorClient;
    private AtomicInteger numImg = new AtomicInteger(0);

    public ImageReceiveHandler(Storage storage, Firestore firestore, ImageAnnotatorClient imageAnnotatorClient){
        this.storage = storage;
        this.firestore = firestore;
        this.imageAnnotatorClient = imageAnnotatorClient;
    }

    public int NumberImages(){
        return numImg.incrementAndGet();
    }
    public int getNumberImages(){
        int ret = numImg.intValue();
        return ret;
    }


    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        //System.out.println(pubsubMessage.getData().toStringUtf8());
        String imagename = pubsubMessage.getData().toStringUtf8();
        Bucket bucket = storage.get("cn-d1-g1-image-bucket");
        Blob imageblob = bucket.get(imagename);

        // Builds the image annotation request
        List<AnnotateImageRequest> requests = new ArrayList<>();
        ImageSource isource = ImageSource.newBuilder().setGcsImageUri("gs://cn-d1-g1-image-bucket/" + imageblob.getName()).build();
        Image img = Image.newBuilder().setSource(isource)/*.setContent(imgBytes)*/.build();
        Feature labelfeat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        Feature facefeat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
        AnnotateImageRequest request1 = AnnotateImageRequest.newBuilder()
                .addFeatures(labelfeat)
                .addFeatures(facefeat)
                .setImage(img)
                .build();
        requests.add(request1);

        // Performs label detection on the image file
        BatchAnnotateImagesResponse response = imageAnnotatorClient.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        ArrayList<String> labels = new ArrayList<>();
        AnnotateImageResponse imageresponse = responses.get(0);

        if (imageresponse.getFaceAnnotationsCount() > 0) {
            try {
                FaceDetect.updateImageWithFaces(bucket, imageblob, imageresponse.getFaceAnnotationsList());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Map<Descriptors.FieldDescriptor, Object>> lea = imageresponse.getLabelAnnotationsList().stream()
                .map(EntityAnnotation::getAllFields)
                .collect(Collectors.toList());

        for(Map<Descriptors.FieldDescriptor, Object> map : lea){
            Set<Descriptors.FieldDescriptor> set = map.keySet();
            for(Descriptors.FieldDescriptor fd : set){
                if(fd.getName().equals("description")) {
                    labels.add(map.get(fd).toString());
                }
            }
        }

        Labels pojo = new Labels();
        pojo.faceNum = imageresponse.getFaceAnnotationsCount();
        pojo.labels = labels;
        CollectionReference colref = firestore.collection("cn-d1-g1-images");
        DocumentReference docref = colref.document(imagename);
        ApiFuture<WriteResult> result = docref.set(pojo);
        try {
            result.get();
            ackReplyConsumer.ack();
            System.out.println("Image " + imagename + " processed succesfully.");
            NumberImages();
        } catch (Exception e) { System.out.println(e.getMessage()); }

    }
}