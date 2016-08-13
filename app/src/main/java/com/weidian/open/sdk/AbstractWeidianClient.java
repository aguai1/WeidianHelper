package com.weidian.open.sdk;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.http.HttpService;
import com.weidian.open.sdk.http.Param;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.AbstractResponse;

public abstract class AbstractWeidianClient {

  protected HttpService httpService;

  public abstract <T extends AbstractResponse> T executeGet(AbstractRequest<T> request) throws OpenException;

  public abstract <T extends AbstractResponse> String executeGetForString(AbstractRequest<T> request)
      throws OpenException;

  public abstract String executeGetForString(String url) throws OpenException;

  public abstract <T extends AbstractResponse> T executePost(AbstractRequest<T> request) throws OpenException;

  public abstract <T extends AbstractResponse> String executePostForString(AbstractRequest<T> request)
      throws OpenException;

  public abstract String executePostForString(String url, Param publicParam, Param bizParam) throws OpenException;

  public abstract <T extends AbstractResponse> T multipart(AbstractRequest<T> request) throws OpenException;

  public void setHttpService(HttpService httpService) {
    this.httpService = httpService;
  }
}
