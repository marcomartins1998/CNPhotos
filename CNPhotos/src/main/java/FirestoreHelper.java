import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirestoreHelper {
    private final Firestore firestore;

    FirestoreHelper(Firestore firestore){
        this.firestore = firestore;
    }

    public List<String> getImagesByCategories(List<String> categories) throws ExecutionException, InterruptedException {
        CollectionReference colref = firestore.collection("cn-d1-g1-images");

        List<Query> queryls = new ArrayList<>();
        for(int i = 0; i < categories.size(); i++){
            Query query = colref.whereArrayContains("labels", categories.get(i));
            queryls.add(query);
        }

        List<List<String>> imagesgroup = new ArrayList<>();
        for(Query query : queryls){
            List<String> images = new ArrayList<>();
            for(DocumentSnapshot docsnapshot : query.get().get().getDocuments()){
                images.add(docsnapshot.getId());
            }
            imagesgroup.add(images);
        }

        return imagesgroup.stream().reduce((ls1, ls2) -> {
            Stream<String> acc = ls2.stream();
            return acc.filter(ls1::contains).collect(Collectors.toList());
        }).get();

    }
}
