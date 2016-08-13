package com.weidian.open.sdk.response.cps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weidian.open.sdk.entity.Sku;
import com.weidian.open.sdk.response.AbstractResponse;

public class VdianCpsItemGetResponse extends AbstractResponse {

  private CpsItemResult result;

  public CpsItemResult getResult() {
    return result;
  }

  public void setResult(CpsItemResult result) {
    this.result = result;
  }


  /***** CpsItemResult *****/
  public static class CpsItemResult {

    private int stock;
    private int instock;
    private int sold;

    private double rate;

    private String price;

    private String[] imgs;
    private String[] thumb_imgs;

    private Sku[] skus;

    @JsonProperty("itemid")
    private String itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("item_url")
    private String itemUrl;

    @JsonProperty("click_url")
    private String clickUrl;

    @JsonProperty("h5_click_url")
    private String h5ClickUrl;

    public int getStock() {
      return stock;
    }

    public void setStock(int stock) {
      this.stock = stock;
    }

    public int getInstock() {
      return instock;
    }

    public void setInstock(int instock) {
      this.instock = instock;
    }

    public int getSold() {
      return sold;
    }

    public void setSold(int sold) {
      this.sold = sold;
    }

    public double getRate() {
      return rate;
    }

    public void setRate(double rate) {
      this.rate = rate;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
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
      this.itemName = itemName;
    }

    public String getSellerId() {
      return sellerId;
    }

    public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
    }

    public String getItemUrl() {
      return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
      this.itemUrl = itemUrl;
    }

    public String getClickUrl() {
      return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
      this.clickUrl = clickUrl;
    }

    public String getH5ClickUrl() {
      return h5ClickUrl;
    }

    public void setH5ClickUrl(String h5ClickUrl) {
      this.h5ClickUrl = h5ClickUrl;
    }

  }
}
