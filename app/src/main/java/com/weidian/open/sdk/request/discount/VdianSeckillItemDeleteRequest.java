package com.weidian.open.sdk.request.discount;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除折扣信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%A8%E5%BA%97%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianSeckillItemDeleteRequest extends AbstractRequest<DiscountOpResponse> {

  private String itemID = "";

  public VdianSeckillItemDeleteRequest(String accessToken) {
      super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (5 / .75f) + 1);
    map.put("itemid", this.itemID);
    return JsonUtils.toJson(map);
  }

  public String getItemID() {
    return itemID;
  }

  public void setItemID(String itemID) {
    this.itemID = itemID;
  }
}
