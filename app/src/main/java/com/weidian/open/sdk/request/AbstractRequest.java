package com.weidian.open.sdk.request;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.response.AbstractResponse;
import com.weidian.open.sdk.util.JsonUtils;

public abstract class AbstractRequest<T extends AbstractResponse> {

  protected String method;
  protected String accessToken;
  protected String version = "1.0";
  protected String format = "json";
  private String lang = "java";

  protected String multipartName;
  protected byte[] multipartContent;

  public AbstractRequest(String accessToken) {
    super();
    this.method = this.buildMethod();
    this.accessToken = accessToken;
  }

  public AbstractRequest(String accessToken, String method) {
    super();
    this.accessToken = accessToken;
    this.method = method;
  }

  public String getPublic() throws OpenException {
    Map<String, String> map = new HashMap<String, String>((int) (4 / .75f) + 1);
    map.put("method", this.method);
    map.put("access_token", this.accessToken);
    map.put("version", this.version);
    map.put("format", this.format);
    map.put("lang", this.lang);
    return JsonUtils.toJson(map);
  }

  public abstract String getParam() throws OpenException;

  @SuppressWarnings("unchecked")
  public Class<T> getResponseClass() {
    return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getMultipartName() {
    return multipartName;
  }

  public void setMultipartName(String multipartName) {
    this.multipartName = multipartName;
  }

  public byte[] getMultipartContent() {
    return multipartContent;
  }

  public void setMultipartContent(byte[] multipartContent) {
    this.multipartContent = multipartContent;
  }

  @Override
  public String toString() {
    try {
      return JsonUtils.toJson(this);
    } catch (OpenException e) {
      e.printStackTrace();
      return null;
    }
  }

  private String buildMethod() {
    String s = this.getClass().getSimpleName();
    int end = s.indexOf("Request");
    s = s.substring(0, end);

    StringBuilder sb = new StringBuilder();
    char[] cs = s.toCharArray();

    for (char c : cs) {
      int type = Character.getType(c);
      if (type == Character.UPPERCASE_LETTER) {
        sb.append(".").append(Character.toLowerCase(c));
      } else {
        sb.append(c);
      }
    }
    return sb.substring(1).toString();
  }

}
