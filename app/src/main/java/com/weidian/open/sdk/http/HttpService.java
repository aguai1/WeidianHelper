package com.weidian.open.sdk.http;

import com.weidian.open.sdk.exception.OpenException;

import java.io.IOException;

public interface HttpService {

  public String get(String url) throws OpenException;

  public String post(String url, Param... params) throws IOException;

  public String multipart(String url, String name, byte[] content) throws OpenException;

}
