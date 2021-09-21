package ca.projects.manef.service;

import ca.projects.manef.domain.Image;
import ca.projects.manef.domain.ImageInput;
import ca.projects.manef.domain.ImageProcessing;
import ca.projects.manef.persistence.CloudImageRepository;
import ca.projects.manef.rest.ImageResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

public class ImageService {

  private CloudImageRepository imageRepository;
  private ImageResponseAssembler imageResponseAssembler;

  @Inject
  public ImageService(CloudImageRepository imageRepository, ImageResponseAssembler imageResponseAssembler) {
    this.imageRepository = imageRepository;
    this.imageResponseAssembler = imageResponseAssembler;
  }

  public ImageResponse getImage(String id){
    return imageResponseAssembler.toResponse(imageRepository.getImage(id));
  }

  public List<ImageResponse> uploadImages(List<ImageInput> imagesToUpload){
    List<Image> uploadedImages = imageRepository.addImages(imagesToUpload);
    return uploadedImages.stream().map(image -> imageResponseAssembler.toResponse(image)).collect(
        Collectors.toList());
  }
}
