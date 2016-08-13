package com.weidian.open.sdk.response.product;

import com.weidian.open.sdk.entity.Sku;
import com.weidian.open.sdk.response.AbstractResponse;

public class CommonSkuResponse extends AbstractResponse {

  private Sku[] result;

  public Sku[] getResult() {
    return result;
  }

  public void setResult(Sku[] result) {
    this.result = result;
  }

}
