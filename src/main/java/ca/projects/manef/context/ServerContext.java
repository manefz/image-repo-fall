package ca.projects.manef.context;

import ca.projects.manef.persistence.storage.AwsS3Client;
import ca.projects.manef.persistence.storage.Storage;
import ca.projects.manef.service.ImageResponseAssembler;
import ca.projects.manef.persistence.CloudImageRepository;
import ca.projects.manef.service.ImageService;

public class ServerContext {
  private static ServiceLocator serviceLocator = ServiceLocator.getInstance();


  public void applyServerContext(){
    serviceLocator.registerInstance(ImageResponseAssembler.class,new ImageResponseAssembler());
    this.applyImagePersistenceContext();
    this.applyImageServiceContext();
  }

  public ImageService getImageService(){
    return serviceLocator.resolveInstance(ImageService.class);
  }

  private void applyImageServiceContext(){
    CloudImageRepository imageRepository = serviceLocator.resolveInstance(CloudImageRepository.class);
    ImageResponseAssembler imageResponseAssembler = serviceLocator.resolveInstance(ImageResponseAssembler.class);
    serviceLocator.registerInstance(ImageService.class, new ImageService(imageRepository,imageResponseAssembler));
  }

  private void applyImagePersistenceContext(){
    Storage awsS3Client = new AwsS3Client();
    serviceLocator.registerInstance(Storage.class, awsS3Client);
    serviceLocator.registerInstance(
        CloudImageRepository.class, new CloudImageRepository(awsS3Client));
  }

}
