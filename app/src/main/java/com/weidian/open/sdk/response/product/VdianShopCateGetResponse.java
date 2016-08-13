package com.weidian.open.sdk.response.product;

import com.weidian.open.sdk.entity.Cate;
import com.weidian.open.sdk.response.AbstractResponse;

public class VdianShopCateGetResponse extends AbstractResponse {

  private Cate[] result;

  public Cate[] getResult() {
    return result;
  }

  public void setResult(Cate[] result) {
    this.result = result;
  }

}
