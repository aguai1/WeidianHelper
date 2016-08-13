package com.weidian.open.sdk.request.cps;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.cps.VdianItemGetPublicResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 获取商品公开信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%95%86%E5%93%81%E5%85%AC%E5%BC%80%E4%BF%A1%E6%81%AF">查看接口文档</a>
 * */
public class VdianItemGetPublicRequest extends AbstractRequest<VdianItemGetPublicResponse> {

  private String itemId;

  public VdianItemGetPublicRequest(String accessToken, String itemId) {
    super(accessToken, "vdian.item.getpublic");
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
