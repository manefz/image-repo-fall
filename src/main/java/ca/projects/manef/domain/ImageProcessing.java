package ca.projects.manef.domain;

import ca.projects.manef.domain.exceptions.ImageProcessingException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing {

  public static Image getImageFromInput(ImageInput imageToUpload){
    Image image;
    try {
      image = new Image(imageToUpload.getMeta().getFileName());
      BufferedImage bufferedImage = ImageIO.read(imageToUpload.getInputStream());
      image.setImageContent(bufferedImage);
    } catch (IOException e) {
      throw new ImageProcessingException(e.getMessage());
    }
    return image;
  }

  public static byte[] toByteArray(BufferedImage bi, String format) {
    byte[] bytes;
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(bi, format, baos);
      bytes = baos.toByteArray();
      return bytes;
    }
    catch(IOException e){
      throw new ImageProcessingException(e.getMessage());
    }
  }

  public static BufferedImage toBufferedImage(byte[] imageBytes){
    ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
    try {
      return ImageIO.read(bais);
    } catch (IOException e) {
      throw new ImageProcessingException(e.getMessage());
    }
  }


}
