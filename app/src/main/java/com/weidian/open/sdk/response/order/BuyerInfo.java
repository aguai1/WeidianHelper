package com.weidian.open.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyerInfo {

  private String region;
  private String phone;
  private String post;
  private String address;
  private String name;
  private String province;
  private String city;

  @JsonProperty("self_address")
  private String selfAddress;

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPost() {
    return post;
  }

  public void setPost(String post) {
    this.post = post;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getSelfAddress() {
    return selfAddress;
  }

  public void setSelfAddress(String selfAddress) {
    this.selfAddress = selfAddress;
  }

}
