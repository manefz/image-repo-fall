package ca.projects.manef.domain;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class Image {

  private String name;
  private String extension;

  private BufferedImage bufferedImage;

  public Image(String name) {
    this.name = name;
    this.extension = parseExtension(name);
  }


  public String getName() {
    return name;
  }

  public String getExtension() {
    return extension;
  }

  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  public void setImageContent(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  private String parseExtension(String name){
    int dot = name.lastIndexOf(".");
    return name.substring(dot + 1);
  }
}
