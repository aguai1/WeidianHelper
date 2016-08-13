package com.weidian.open.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.http.DefaultHttpService;
import com.weidian.open.sdk.http.HttpService;
import com.weidian.open.sdk.http.Param;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.AbstractResponse;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.StringUtils;
import com.weidian.open.sdk.util.SystemConfig;
import com.weidian.open.sdk.util.UrlUtils;

import java.io.IOException;

public class DefaultWeidianClient extends AbstractWeidianClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultWeidianClient.class);

  private static DefaultWeidianClient instance = new DefaultWeidianClient();

  public DefaultWeidianClient() {
    super();
  }

  public static DefaultWeidianClient getInstance() {
    return instance;
  }

  @Override
  public <T extends AbstractResponse> T executeGet(AbstractRequest<T> request) throws OpenException {
    try {
      String response = this.executeGetForString(request);
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> String executeGetForString(AbstractRequest<T> request) throws OpenException {
    try {
      String url = this.buildUrlForGet(request);
      return this.executeGetForString(url);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public String executeGetForString(String url) throws OpenException {
    try {
      LOGGER.debug("url:{}", url);
      return this.get(url);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> T executePost(AbstractRequest<T> request) throws OpenException {
    try {
      String response = this.executePostForString(request);
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> String executePostForString(AbstractRequest<T> request) throws OpenException {
    try {
      return this.executePostForString(SystemConfig.API_URL_FOR_POST,
          new Param(SystemConfig.PUBLIC_PARAM, request.getPublic()),
          new Param(SystemConfig.BIZ_PARAM, request.getParam()));
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public String executePostForString(String url, Param publicParam, Param bizParam) throws OpenException {
    try {
      LOGGER.debug("url:{}", url);
      return this.post(url, publicParam, bizParam);
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  @Override
  public <T extends AbstractResponse> T multipart(AbstractRequest<T> request) throws OpenException {
    try {
      String url = String.format(SystemConfig.MEDIA_UPLOAD_URL_TEMPLATE, request.getAccessToken());
      LOGGER.debug("url:{}", url);
      String response = this.getHttpService().multipart(url, request.getMultipartName(), request.getMultipartContent());
      LOGGER.debug("response:{}", response);
      return this.parse(response, request.getResponseClass());
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw new OpenException("WeidianClient execute failed:", e);
    }
  }

  public HttpService getHttpService() {
    if (httpService == null) {
      this.setHttpService(DefaultHttpService.getInstance());
    }
    return httpService;
  }

  private <T extends AbstractResponse> String buildUrlForGet(AbstractRequest<T> request) throws OpenException {
    String pub = UrlUtils.encode(request.getPublic());
    String param = request.getParam();
    param = StringUtils.isEmpty(param) ? "" : UrlUtils.encode(param);
    return String.format(SystemConfig.API_URL_TEMPLATE_FOR_GET, pub, param);
  }

  private String get(String url) throws OpenException {
    return this.getHttpService().get(url);
  }

  private String post(String url, Param... params) throws IOException {
    return this.getHttpService().post(url, params);
  }

  private <T extends AbstractResponse> T parse(String response, Class<T> cls) throws OpenException {
    return JsonUtils.toObject(response, cls);
  }

}
