package ca.projects.manef.service;

import ca.projects.manef.domain.Image;
import ca.projects.manef.rest.ImageResponse;

public class ImageResponseAssembler {

  public ImageResponse toResponse(Image image){
    return new ImageResponse(image.getName(),image.getBufferedImage());

  }
}
