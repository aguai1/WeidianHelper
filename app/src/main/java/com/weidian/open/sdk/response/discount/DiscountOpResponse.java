package com.weidian.open.sdk.response.discount;

import com.weidian.open.sdk.entity.DiscountItem;
import com.weidian.open.sdk.response.AbstractResponse;

public class DiscountOpResponse extends AbstractResponse {

  private boolean result;

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }
}
