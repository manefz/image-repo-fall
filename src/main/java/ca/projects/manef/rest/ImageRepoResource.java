package ca.projects.manef.rest;

import ca.projects.manef.context.ServerContext;
import ca.projects.manef.domain.ImageInput;
import ca.projects.manef.service.ImageService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
public class ImageRepoResource {
  private ImageService imageService;
  private static final String FORM_DATA_PARAM = "file";

  @Inject
  public ImageRepoResource(ServerContext serverContext) {
    this.imageService = serverContext.getImageService();
  }

  @GET
  @Path("/{id}")
  @Produces({"image/png", "image/jpeg", "image/gif","application/json"})
  public Response getPicture(@PathParam("id") String id)
  {
    ImageResponse imageResponse = imageService.getImage(id);
    return Response.ok(imageResponse.bufferedImage).build();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public Response uploadMultiple(@FormDataParam(FORM_DATA_PARAM) FormDataBodyPart body){
    List<ImageInput> imageInputs = new ArrayList<>();
    for(BodyPart part : body.getParent().getBodyParts()){
      InputStream is = part.getEntityAs(InputStream.class);
      ContentDisposition meta = part.getContentDisposition();
      imageInputs.add(new ImageInput(is,meta));
    }

    List<ImageResponse> ids = imageService.uploadImages(imageInputs);
    return Response.ok(ids).status(201).build();
  }

}

