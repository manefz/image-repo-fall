package ca.projects.manef.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
  @JsonProperty("error")
  private final String error;

  @JsonProperty("description")
  private final String description;

  @JsonCreator
  public ErrorResponse(String error, String description) {
    this.error = error;
    this.description = description;
  }
}
