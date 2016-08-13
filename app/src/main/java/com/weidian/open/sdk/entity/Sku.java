package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sku {

  public String id;
  public String title;
  public String price;
  public int stock;

  @JsonProperty("sku_merchant_code")
  String skuMerchantCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getSkuMerchantCode() {
    return skuMerchantCode;
  }

  public void setSkuMerchantCode(String skuMerchantCode) {
    this.skuMerchantCode = skuMerchantCode;
  }

}
