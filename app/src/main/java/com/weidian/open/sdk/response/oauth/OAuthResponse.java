package com.weidian.open.sdk.response.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weidian.open.sdk.response.AbstractResponse;

public class OAuthResponse extends AbstractResponse {

  private OAuthResult result;

  public OAuthResult getResult() {
    return result;
  }

  public void setResult(OAuthResult result) {
    this.result = result;
  }

}


class OAuthResult {

  public String openid;

  public int scope;

  @JsonProperty("access_token")
  public String accessToken;

  @JsonProperty("expire_in")
  public int expire_in;

  @JsonProperty("refresh_token")
  public String refreshToken;

  @JsonProperty("shop_name")
  public String shopName;

  @JsonProperty("shop_logo")
  public String shopLogo;

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public int getScope() {
    return scope;
  }

  public void setScope(int scope) {
    this.scope = scope;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getShopLogo() {
    return shopLogo;
  }

  public void setShopLogo(String shopLogo) {
    this.shopLogo = shopLogo;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpire_in() {
    return expire_in;
  }

  public void setExpire_in(int expire_in) {
    this.expire_in = expire_in;
  }

}
