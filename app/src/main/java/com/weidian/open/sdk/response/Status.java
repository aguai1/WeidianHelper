package com.weidian.open.sdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

  @JsonProperty("status_code")
  private int statusCode;

  @JsonProperty("status_reason")
  private String statusReason;

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusReason() {
    return statusReason;
  }

  public void setStatusReason(String statusReason) {
    this.statusReason = statusReason;
  }

}
