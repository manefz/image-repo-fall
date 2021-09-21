package ca.projects.manef.rest.mappers;

import ca.projects.manef.rest.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServerErrorExceptionMapper implements ExceptionMapper<Exception> {
  private final String message = Status.INTERNAL_SERVER_ERROR.toString();
  private final String error = Status.INTERNAL_SERVER_ERROR.toString().toUpperCase();


  @Override
  public Response toResponse(Exception exception) {
    return Response.status(Status.INTERNAL_SERVER_ERROR)
        .entity(new ErrorResponse(error, message))
        .type("application/json")
        .build();
  }
}
