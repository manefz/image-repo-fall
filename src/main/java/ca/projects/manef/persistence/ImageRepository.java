package ca.projects.manef.persistence;

import ca.projects.manef.domain.Image;
import ca.projects.manef.domain.ImageInput;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public interface ImageRepository {
  Image getImage(String id);

  List<Image> addImages(List<ImageInput> imagesInput);

}
