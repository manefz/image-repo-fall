package ca.projects.manef.domain;

import static org.junit.jupiter.api.Assertions.*;

import helper.ImageInputLoaderHelper;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;

class ImageProcessingTest {
  private String IMAGE_PATH = "./src/test/java/images/amzon.jpeg";

  @Test
  void whenGettingImageFromInputThenReturnImageWithCorrectContent() {
    ImageInput imageInput = ImageInputLoaderHelper.getImageInput(this.IMAGE_PATH);
    Image image = ImageProcessing.getImageFromInput(imageInput);
    assertNotNull(image.getBufferedImage());
  }

  @Test
  void whenConvertingToByteArrayThenReturnNonEmptyByteArray() {
    ImageInput imageInput = ImageInputLoaderHelper.getImageInput(this.IMAGE_PATH);
    Image image = ImageProcessing.getImageFromInput(imageInput);
    byte[] bytes = ImageProcessing.toByteArray(image.getBufferedImage(),image.getExtension());
    assertTrue(bytes.length > 0);
  }

}
