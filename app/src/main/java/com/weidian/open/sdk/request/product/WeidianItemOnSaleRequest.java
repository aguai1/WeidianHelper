package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 商品上下架<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%95%86%E5%93%81%E4%B8%8A%E4%B8%8B%E6%9E%B6">查看接口文档</a>
 * */
public class WeidianItemOnSaleRequest extends AbstractRequest<CommonResponse> {

  private String itemId;
  private int opt;

  public WeidianItemOnSaleRequest(String accessToken, String itemId, int opt) {
    super(accessToken, "weidian.item.onSale");
    this.itemId = itemId;
    this.opt = opt;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("itemid", this.itemId);
    map.put("opt", this.opt);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public int getOpt() {
    return opt;
  }

  public void setOpt(int opt) {
    this.opt = opt;
  }

}
