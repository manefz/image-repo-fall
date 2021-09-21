package helper;

import ca.projects.manef.domain.ImageInput;
import ca.projects.manef.domain.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.glassfish.jersey.media.multipart.ContentDisposition;

public class ImageInputLoaderHelper {

  public static ImageInput getImageInput(String pathToImage){
    ImageInput imageInput = null;
    File imageFile = new File(pathToImage);
    String imageName = imageFile.getName();
    try {
      BufferedImage bf = ImageIO.read(imageFile);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ContentDisposition disposition = ContentDisposition.type("any").fileName(imageName).build();ImageIO.write(bf, "jpeg", baos);
      InputStream is = new ByteArrayInputStream(baos.toByteArray());
      imageInput = new ImageInput(is, disposition);
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
    return imageInput;
  }

}
