import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StorageHelper {
    private final Storage storage;

    public StorageHelper(Storage storage){
        this.storage = storage;
    }

    public String storeImage(String newName, String directory, String bucketname) throws FileNotFoundException {
        File imagefile = new File(directory);
        InputStream inputStream = new FileInputStream(imagefile);

        Bucket bucket = this.storage.get(bucketname);
        bucket.create(newName, inputStream, "image/jpeg");
        return newName;

    }

    public String getLinkByName(String blobname, String bucketname){
        Bucket bucket = this.storage.get(bucketname);
        return bucket.get(blobname).getMediaLink();
    }

    public List<String> getEveryBlobName(String bucketname){
        Bucket bucket = this.storage.get(bucketname);
        return StreamSupport.stream(bucket.list().iterateAll().spliterator(), false)
                .map(Blob::getName)
                .collect(Collectors.toList());
    }
}
