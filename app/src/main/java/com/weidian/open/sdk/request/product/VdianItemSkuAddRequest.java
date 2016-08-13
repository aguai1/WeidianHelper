package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.entity.Sku;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.CommonSkuResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 添加商品型号<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%B7%BB%E5%8A%A0%E5%95%86%E5%93%81%E5%9E%8B%E5%8F%B7">查看接口文档</a>
 * */
public class VdianItemSkuAddRequest extends AbstractRequest<CommonSkuResponse> {

  private String itemId;
  private Sku[] skus;

  public VdianItemSkuAddRequest(String accessToken, String itemId, Sku[] skus) {
    super(accessToken);
    this.itemId = itemId;
    this.skus = skus;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("itemid", this.itemId);
    map.put("skus", skus);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Sku[] getSkus() {
    return skus;
  }

  public void setSkus(Sku[] skus) {
    this.skus = skus;
  }

}
