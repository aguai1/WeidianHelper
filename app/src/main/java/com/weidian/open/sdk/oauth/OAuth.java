package com.weidian.open.sdk.oauth;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.http.DefaultHttpService;
import com.weidian.open.sdk.http.HttpService;
import com.weidian.open.sdk.response.oauth.OAuthResponse;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.SystemConfig;
import com.weidian.open.sdk.util.UrlUtils;

/**
 * 获取Token<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96Token">查看接口文档</a>
 * */
public class OAuth {

  private HttpService httpService;

  private static OAuth instance = new OAuth();

  public OAuth() {
    super();
  }

  public static OAuth getInstance() {
    return instance;
  }

  /********** 自用型应用获取Token **********/
  public OAuthResponse getPersonalToken() throws OpenException {
    String response = this.getPersonalTokenForString();
    return JsonUtils.toObject(response, OAuthResponse.class);
  }

  public String getPersonalTokenForString() throws OpenException {
    String appkey = UrlUtils.encode(SystemConfig.APPKEY);
    String secret = UrlUtils.encode(SystemConfig.SECRET);
    String url = String.format(SystemConfig.PERSONAL_TOKEN_URL_TEMPLATE, appkey, secret);
    return this.getHttpService().get(url);
  }

  /********** 生成OAuth2授权跳转url **********/
  public String getOAuth2AuthorizeUrl(String redirectUriWithoutEncoding, String state) throws OpenException {
    String appkey = UrlUtils.encode(SystemConfig.APPKEY);
    String uri = UrlUtils.encode(redirectUriWithoutEncoding);
    state = UrlUtils.encode(state);
    return String.format(SystemConfig.OAUTH2_AUTHORIZE_URL_TEMPLATE, appkey, uri, state);
  }

  /********** OAuth2获取Token **********/
  public OAuthResponse getOAuth2Token(String code) throws OpenException {
    String response = this.getOAuth2TokenForString(code);
    return JsonUtils.toObject(response, OAuthResponse.class);
  }

  public String getOAuth2TokenForString(String code) throws OpenException {
    String appkey = UrlUtils.encode(SystemConfig.APPKEY);
    String secret = UrlUtils.encode(SystemConfig.SECRET);
    code = UrlUtils.encode(code);
    String url = String.format(SystemConfig.OAUTH2_TOKEN_URL_TEMPLATE, appkey, secret, code);
    return this.getHttpService().get(url);
  }

  /********** OAuth2刷新Token **********/
  public OAuthResponse refreshOAuth2Token(String refreshToken) throws OpenException {
    String response = this.refreshOAuth2TokenForString(refreshToken);
    return JsonUtils.toObject(response, OAuthResponse.class);
  }

  public String refreshOAuth2TokenForString(String refreshToken) throws OpenException {
    String appkey = UrlUtils.encode(SystemConfig.APPKEY);
    refreshToken = UrlUtils.encode(refreshToken);
    String url = String.format(SystemConfig.OAUTH2_REFRESH_TOKEN_URL_TEMPLATE, appkey, refreshToken);
    return this.getHttpService().get(url);
  }

  public HttpService getHttpService() {
    if (httpService == null) {
      this.setHttpService(DefaultHttpService.getInstance());
    }
    return httpService;
  }

  public void setHttpService(HttpService httpService) {
    this.httpService = httpService;
  }

}
