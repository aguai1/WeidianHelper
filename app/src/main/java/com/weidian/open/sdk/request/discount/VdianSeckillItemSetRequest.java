package com.weidian.open.sdk.request.discount;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置折扣信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%A8%E5%BA%97%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianSeckillItemSetRequest extends AbstractRequest<DiscountOpResponse> {

  private String itemid;
  private String  price;
  private String  end_time;
  private String start_time;
  private String quantity="100";
  public VdianSeckillItemSetRequest(String accessToken) {
      super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (5 / .75f) + 1);
    map.put("itemid", this.itemid);
    map.put("price", this.price);
    map.put("start_time", this.start_time);
    map.put("end_time", this.end_time);
    map.put("quantity", this.quantity);

    return JsonUtils.toJson(map);
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getItemid() {
    return itemid;
  }

  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getEnd_time() {
    return end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }
}
