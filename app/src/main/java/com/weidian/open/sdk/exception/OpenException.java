package com.weidian.open.sdk.exception;

import java.io.IOException;

public class OpenException extends IOException {

  private static final long serialVersionUID = 466968164282917646L;

  public OpenException(String message, Throwable cause) {
    super(message, cause);
  }

  public OpenException(String message) {
    super(message);
  }

}
