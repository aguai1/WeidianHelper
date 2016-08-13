package com.weidian.open.sdk.response.discount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weidian.open.sdk.entity.DiscountItem;
import com.weidian.open.sdk.response.AbstractResponse;

public class DiscountResponse extends AbstractResponse {

  private DiscountRequest result;

  public DiscountRequest getResult() {
    return result;
  }

  public void setResult(DiscountRequest result) {
    this.result = result;
  }


  /***** VdianItemListGetResult *****/
  public static class DiscountRequest {

    private DiscountItem[] list;

    @JsonProperty("total")
    private int totalNum;

    public DiscountItem[] getList() {
      return list;
    }

    public void setList(DiscountItem[] list) {
      this.list = list;
    }

    public int getTotalNum() {
      return totalNum;
    }

    public void setTotalNum(int totalNum) {
      this.totalNum = totalNum;
    }
  }


}
