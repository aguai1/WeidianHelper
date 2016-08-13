package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
  //计算后的库存
  private int finalstock;
  private int stock;
  private int istop;
  private int sold;
// 计算后的价格
  private String finalprice;
  private String price;
  private String status;
//计算后的name
  private String finalItemName;
  @JsonProperty("item_name")
  private String itemName;
  private String[] imgs;
  private String[] thumb_imgs;

  private Sku[] skus;
  private Cate[] cates;

  @JsonProperty("itemid")
  private String itemId;



  @JsonProperty("seller_id")
  private String sellerId;

  @JsonProperty("merchant_code")
  private String merchantCode;

  @JsonProperty("fx_fee_rate")
  private String fxFeeRate;

  @JsonProperty("item_desc")
  private String itemDesc;

  public Cate[] getCates() {
    return cates;
  }

  public void setCates(Cate[] cates) {
    this.cates = cates;
  }

  public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
    this.finalstock=stock;
  }

  public int getIstop() {
    return istop;
  }

  public void setIstop(int istop) {
    this.istop = istop;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
    this.finalprice=price;
  }

  public int getFinalstock() {
    return finalstock;
  }

  public void setFinalstock(int finalstock) {
    this.finalstock = finalstock;
  }

  public String getFinalItemName() {
    return finalItemName;
  }

  public void setFinalItemName(String finalItemName) {
    this.finalItemName = finalItemName;
  }

  public String getFinalprice() {
    return finalprice;
  }

  public void setFinalprice(String finalprice) {
    this.finalprice = finalprice;
  }

  public int getSold() {
    return sold;
  }

  public void setSold(int sold) {
    this.sold = sold;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String[] getImgs() {
    return imgs;
  }

  public void setImgs(String[] imgs) {
    this.imgs = imgs;
  }

  public String[] getThumb_imgs() {
    return thumb_imgs;
  }

  public void setThumb_imgs(String[] thumb_imgs) {
    this.thumb_imgs = thumb_imgs;
  }

  public Sku[] getSkus() {
    return skus;
  }

  public void setSkus(Sku[] skus) {
    this.skus = skus;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.finalItemName=itemName;
    this.itemName = itemName;
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId;
  }

  public String getMerchantCode() {
    return merchantCode;
  }

  public void setMerchantCode(String merchantCode) {
    this.merchantCode = merchantCode;
  }

  public String getFxFeeRate() {
    return fxFeeRate;
  }

  public void setFxFeeRate(String fxFeeRate) {
    this.fxFeeRate = fxFeeRate;
  }

}
