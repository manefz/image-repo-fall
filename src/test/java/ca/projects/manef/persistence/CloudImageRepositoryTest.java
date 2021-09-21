package ca.projects.manef.persistence;

import static org.junit.jupiter.api.Assertions.*;

import ca.projects.manef.domain.Image;
import ca.projects.manef.domain.ImageInput;
import ca.projects.manef.domain.ImageProcessing;
import ca.projects.manef.domain.exceptions.ImageNotFoundException;
import ca.projects.manef.persistence.storage.Storage;
import helper.ImageInputLoaderHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.mockito.MockitoAnnotations.initMocks;


import org.mockito.Mock;
import static org.mockito.Mockito.*;


@TestInstance(Lifecycle.PER_CLASS)
class CloudImageRepositoryTest {

  @Mock private Storage storage;
  private CloudImageRepository cloudImageRepository;
  private String IMAGE_PATH = "./src/test/java/images/amzon.jpeg";
  private String EXISTING_IMAGE_NAME = "existing_image.jpeg";
  private String NON_EXISTING_IMAGE_NAME = "non_existing_image.jpeg";

  private ImageInput anyImageInput;
  private Image existingImage;


  @BeforeAll
  void loadImage(){
    this.anyImageInput = ImageInputLoaderHelper.getImageInput(IMAGE_PATH);
    this.existingImage = ImageProcessing.getImageFromInput(this.anyImageInput);
  }

  @BeforeEach
  void setUp() {
    initMocks(this);
    this.cloudImageRepository= new CloudImageRepository(storage);
    when(storage.getImage(this.EXISTING_IMAGE_NAME)).thenReturn(this.existingImage);
    when(storage.getImage(this.NON_EXISTING_IMAGE_NAME)).thenThrow(ImageNotFoundException.class);
  }

  @Test
  void whenAddingOneImageThenOneImageIsReturned() {
    List<ImageInput> imageInputs = new ArrayList<>();
    imageInputs.add(this.anyImageInput);
    List<Image> uploadedImages = this.cloudImageRepository.addImages(imageInputs);
    assertEquals(uploadedImages.size(), 1);
  }

  @Test
  void whenAddingXImagesThenXImageIsReturned() {
    List<ImageInput> imageInputs = Arrays.asList(this.anyImageInput,this.anyImageInput);
    int X = imageInputs.size();
    List<Image> uploadedImages = this.cloudImageRepository.addImages(imageInputs);
    assertEquals(uploadedImages.size(), X);
  }

  @Test
  void whenGettingAnExistingImageThenAnImageIsReturned() {
    Image image = this.cloudImageRepository.getImage(this.EXISTING_IMAGE_NAME);
    assertEquals(this.existingImage, image);
  }

  @Test
  void whenGettingANonExistingImageThenErrorOccurs() {
    assertThrows(ImageNotFoundException.class, () -> this.cloudImageRepository.getImage(this.NON_EXISTING_IMAGE_NAME));
  }

}
