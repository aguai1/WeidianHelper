package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 添加商品图片<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%B7%BB%E5%8A%A0%E5%95%86%E5%93%81%E5%9B%BE%E7%89%87">查看接口文档</a>
 * */
public class VdianItemImageAddRequest extends AbstractRequest<CommonResponse> {

  private String itemId;
  private String[] imgs;

  public VdianItemImageAddRequest(String accessToken, String itemId, String[] imgs) {
    super(accessToken);
    this.itemId = itemId;
    this.imgs = imgs;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("itemid", this.itemId);
    map.put("imgs", this.imgs);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String[] getImgs() {
    return imgs;
  }

  public void setImgs(String[] imgs) {
    this.imgs = imgs;
  }

}
