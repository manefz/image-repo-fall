package ca.projects.manef.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HeartbeatResource {

  @GET
  public String getHeartbeatMessage(){
    return "Server is running.";
  }
}
