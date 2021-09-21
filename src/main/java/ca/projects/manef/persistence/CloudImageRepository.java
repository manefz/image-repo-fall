package ca.projects.manef.persistence;

import ca.projects.manef.persistence.storage.Storage;
import ca.projects.manef.domain.Image;
import ca.projects.manef.domain.ImageProcessing;
import ca.projects.manef.domain.exceptions.ImageNotFoundException;
import ca.projects.manef.domain.ImageInput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CloudImageRepository implements ImageRepository {
  private Storage storage;

  public CloudImageRepository(Storage storage) {
    this.storage = storage;
  }

  public Image getImage(String imageName){
    try{
      return storage.getImage(imageName);
    }
    catch(RuntimeException e){
      throw new ImageNotFoundException(imageName);
    }
  }

  public List<Image> addImages(List<ImageInput> imagesInputToUpload) {
    List<Image> uploadedImages = new ArrayList<>();
    List<Image> imagesToUpload = imagesInputToUpload.stream().map(ImageProcessing::getImageFromInput).collect(
        Collectors.toList());

    uploadedImages.addAll(imagesToUpload);

    storage.storeImages(imagesToUpload);
    return uploadedImages;
  }
}
