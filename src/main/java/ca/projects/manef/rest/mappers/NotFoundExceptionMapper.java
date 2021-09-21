package ca.projects.manef.rest.mappers;

import ca.projects.manef.rest.ErrorResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
  private final String message = "URI not found";
  private final String error = Status.NOT_FOUND.toString().toUpperCase();

  @Override
  public Response toResponse(NotFoundException e) {
    return Response.status(Status.NOT_FOUND).entity(new ErrorResponse(error,message)).type("application/json").build();
  }
}
