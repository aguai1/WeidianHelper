package com.weidian.open.sdk.response.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weidian.open.sdk.response.AbstractResponse;

public class VdianOrderListGetResponse extends AbstractResponse {

  private VdianOrderListGetResult result;

  public VdianOrderListGetResult getResult() {
    return result;
  }

  public void setResult(VdianOrderListGetResult result) {
    this.result = result;
  }


  /***** VdianOrderListGetResult *****/
  public static class VdianOrderListGetResult {

    @JsonProperty("total_num")
    private int totalNum;

    @JsonProperty("order_num")
    private int orderNum;

    private ListOrder[] orders;

    public int getTotalNum() {
      return totalNum;
    }

    public void setTotalNum(int totalNum) {
      this.totalNum = totalNum;
    }

    public int getOrderNum() {
      return orderNum;
    }

    public void setOrderNum(int orderNum) {
      this.orderNum = orderNum;
    }

    public ListOrder[] getOrders() {
      return orders;
    }

    public void setOrders(ListOrder[] orders) {
      this.orders = orders;
    }

  }


  /***** ListOrder *****/
  public static class ListOrder {

    private String status;
    private String img;
    private String time;
    private String status2;

    @JsonProperty("f_phone")
    private String fPhone;

    @JsonProperty("express_type")
    private String expressType;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("update_time")
    private String updateTime;

    @JsonProperty("seller_note")
    private String sellerNote;

    @JsonProperty("buyer_note")
    private String buyerNote;

    @JsonProperty("f_seller_id")
    private String fSellerId;

    @JsonProperty("express_no")
    private String expressNo;

    @JsonProperty("f_shop_name")
    private String fShopName;

    @JsonProperty("buyer_info")
    private BuyerInfo buyerInfo;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getImg() {
      return img;
    }

    public void setImg(String img) {
      this.img = img;
    }

    public String getTime() {
      return time;
    }

    public void setTime(String time) {
      this.time = time;
    }

    public String getStatus2() {
      return status2;
    }

    public void setStatus2(String status2) {
      this.status2 = status2;
    }

    public String getfPhone() {
      return fPhone;
    }

    public void setfPhone(String fPhone) {
      this.fPhone = fPhone;
    }

    public String getExpressType() {
      return expressType;
    }

    public void setExpressType(String expressType) {
      this.expressType = expressType;
    }

    public String getOrderId() {
      return orderId;
    }

    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    public String getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
    }

    public String getSellerNote() {
      return sellerNote;
    }

    public void setSellerNote(String sellerNote) {
      this.sellerNote = sellerNote;
    }

    public String getBuyerNote() {
      return buyerNote;
    }

    public void setBuyerNote(String buyerNote) {
      this.buyerNote = buyerNote;
    }

    public String getfSellerId() {
      return fSellerId;
    }

    public void setfSellerId(String fSellerId) {
      this.fSellerId = fSellerId;
    }

    public String getExpressNo() {
      return expressNo;
    }

    public void setExpressNo(String expressNo) {
      this.expressNo = expressNo;
    }

    public String getfShopName() {
      return fShopName;
    }

    public void setfShopName(String fShopName) {
      this.fShopName = fShopName;
    }

    public BuyerInfo getBuyerInfo() {
      return buyerInfo;
    }

    public void setBuyerInfo(BuyerInfo buyerInfo) {
      this.buyerInfo = buyerInfo;
    }

  }
}
