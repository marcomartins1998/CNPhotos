import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Vertex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FaceDetect {

    public static void updateImageWithFaces(Bucket bucket, Blob blob, List<FaceAnnotation> faces) throws IOException {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(blob.getContent()));
        for (FaceAnnotation face : faces) {

            Graphics2D gfx = img.createGraphics();
            Polygon poly = new Polygon();
            for (Vertex vertex : face.getFdBoundingPoly().getVerticesList()) {
                poly.addPoint(vertex.getX(), vertex.getY());
            }
            gfx.setStroke(new BasicStroke(5));
            gfx.setColor(new java.awt.Color(0x00ff00));
            gfx.draw(poly);
        }

        blob.delete();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        bucket.create(blob.getName(), is, "image/jpeg");
    }

}
