package ca.projects.manef.domain.exceptions;

public class ImageNotFoundException extends RuntimeException {
  public static String error;
  public static String description;

  public ImageNotFoundException(String name) {
    this.error = "IMAGE NOT FOUND";
    this.description = String.format("Image with name %s not found",name);
  }
}
