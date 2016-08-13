package com.weidian.open.sdk.response.product;

import com.weidian.open.sdk.entity.Item;
import com.weidian.open.sdk.response.AbstractResponse;

public class CommonItemResponse extends AbstractResponse {

  private Item result;

  public Item getResult() {
    return result;
  }

  public void setResult(Item result) {
    this.result = result;
  }

}
