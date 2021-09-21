package ca.projects.manef.persistence.storage;

import ca.projects.manef.domain.Image;
import java.util.List;

public interface Storage {

  void storeImages(List<Image> images);

  Image getImage(String imageId);

}
