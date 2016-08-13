package com.weidian.open.sdk.request.cps;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.cps.VdianCpsItemGetResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 获取CPS商品<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96CPS%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianCpsItemGetRequest extends AbstractRequest<VdianCpsItemGetResponse> {

  private String itemId;

  public VdianCpsItemGetRequest(String accessToken, String itemId) {
    super(accessToken);
    this.itemId = itemId;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("itemid", this.itemId);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

}
