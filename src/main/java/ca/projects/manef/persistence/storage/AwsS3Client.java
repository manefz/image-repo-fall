package ca.projects.manef.persistence.storage;

import ca.projects.manef.domain.Image;
import ca.projects.manef.domain.ImageProcessing;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class AwsS3Client implements Storage {

  private String PROPERTIES_FILE = "./resources/s3_config.properties";
  private String accessKey;
  private String secretKey;
  private String bucketName;
  private String region;
  private S3Client s3Client;

  public AwsS3Client() {
    Properties prop = this.getAwsConfigs();
    this.accessKey = prop.getProperty("aws_access_key_id");
    this.secretKey = prop.getProperty("aws_secret_access_key");
    this.bucketName = prop.getProperty("bucket_name");
    this.region = prop.getProperty("region");
    this.s3Client = this.createS3Client();
  }

  private S3Client createS3Client(){
    Region region = Region.of(this.region);
    AwsBasicCredentials awsCreds = AwsBasicCredentials.create(this.accessKey, this.secretKey);
    S3Client s3Client = S3Client.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
        .region(region)
        .build();
    return s3Client;
  }


  @Override
  public void storeImages(List<Image> imagesToUpload){
    try {
      for (Image image:imagesToUpload){
        String key = image.getName();

        PutObjectRequest putOb = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        byte[] imageBytes = ImageProcessing.toByteArray(image.getBufferedImage(), image.getExtension());
        PutObjectResponse response = s3Client.putObject(putOb,
            RequestBody.fromBytes(imageBytes));

        }
      }catch (S3Exception e) {
        System.err.println(e.getMessage());
        System.exit(1);
      }
   }

   public Image getImage(String imageId){
     try {
       Image image;

       GetObjectRequest objectRequest = GetObjectRequest
           .builder()
           .key(imageId)
           .bucket(bucketName)
           .build();

       ResponseBytes<GetObjectResponse> objectBytes = this.s3Client.getObjectAsBytes(objectRequest);

       byte[] data = objectBytes.asByteArray();
       BufferedImage bufferedImage = ImageProcessing.toBufferedImage(data);
       image = new Image(imageId);
       image.setImageContent(bufferedImage);
       return image;

     }catch (S3Exception e) {
       System.err.println(e.awsErrorDetails().errorMessage());
       throw e;
    }
   }

  private Properties getAwsConfigs() {
    Properties prop = new Properties();
    try {
      FileInputStream fis = new FileInputStream(PROPERTIES_FILE);
      prop.load(fis);
    }
    catch (IOException e){
      throw new RuntimeException(e.getMessage());
    }
    return prop;
  }
}
