package ca.projects.manef.rest.mappers;

import ca.projects.manef.domain.exceptions.ImageNotFoundException;
import ca.projects.manef.rest.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ImageNotFoundExceptionMapper implements ExceptionMapper<ImageNotFoundException> {
  @Override
  public Response toResponse(ImageNotFoundException e) {
    return Response.status(Status.NOT_FOUND)
        .entity(new ErrorResponse(e.error.toUpperCase(), e.description))
        .build();
  }
}
