package ca.projects.manef.domain;

import java.io.InputStream;
import org.glassfish.jersey.media.multipart.ContentDisposition;

public class ImageInput {

  private InputStream inputStream;
  private ContentDisposition meta;


  public ImageInput(InputStream inputStream,
      ContentDisposition meta) {
    this.inputStream = inputStream;
    this.meta = meta;
  }


  public InputStream getInputStream() {
    return inputStream;
  }


  public ContentDisposition getMeta() {
    return meta;
  }
}
