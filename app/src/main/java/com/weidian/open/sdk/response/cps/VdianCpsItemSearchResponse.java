package com.weidian.open.sdk.response.cps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weidian.open.sdk.response.AbstractResponse;

public class VdianCpsItemSearchResponse extends AbstractResponse {

  private VdianCpsItemSearchResult result;

  public VdianCpsItemSearchResult getResult() {
    return result;
  }

  public void setResult(VdianCpsItemSearchResult result) {
    this.result = result;
  }


  /***** VdianCpsItemSearchResult *****/
  public static class VdianCpsItemSearchResult {

    private CpsSearchItem[] items;

    @JsonProperty("total_num")
    private int totalNum;

    public CpsSearchItem[] getItems() {
      return items;
    }

    public void setItems(CpsSearchItem[] items) {
      this.items = items;
    }

    public int getTotalNum() {
      return totalNum;
    }

    public void setTotalNum(int totalNum) {
      this.totalNum = totalNum;
    }
  }


  /***** CpsSearchItem *****/
  public static class CpsSearchItem {

    private int sold;

    private double rate;

    private String price;
    private String img;

    @JsonProperty("itemid")
    private String itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("seller_id")
    private String sellerId;

    @JsonProperty("item_url")
    private String itemUrl;

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

    public String getImg() {
      return img;
    }

    public void setImg(String img) {
      this.img = img;
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

  }
}
