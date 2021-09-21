package ca.projects.manef.domain.exceptions;

public class ImageProcessingException extends RuntimeException {
  public static String error;
  public static String description;

  public ImageProcessingException(String description) {
    this.error = "IMAGE_PROCESSING_ERROR";
    this.description = description;
  }
}
