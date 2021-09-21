package ca.projects.manef.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.awt.image.BufferedImage;
import java.util.UUID;

public class ImageResponse {
  public String imageFileName;
  @JsonIgnore
  public BufferedImage bufferedImage;

  public ImageResponse(String name, BufferedImage bufferedImage) {
    this.imageFileName = name;
    this.bufferedImage = bufferedImage;
  }
}
