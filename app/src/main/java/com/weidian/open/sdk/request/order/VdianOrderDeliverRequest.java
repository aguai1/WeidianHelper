package com.weidian.open.sdk.request.order;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 订单发货<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%AE%A2%E5%8D%95%E5%8F%91%E8%B4%A7">查看接口文档</a>
 * */
public class VdianOrderDeliverRequest extends AbstractRequest<CommonResponse> {

  private String orderId;
  private String expressType;
  private String expressNo;
  private String expressCustom;

  public VdianOrderDeliverRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (4 / .75f) + 1);
    map.put("order_id", this.orderId);
    map.put("express_type", this.expressType);
    map.put("express_no", this.expressNo);
    map.put("express_custom", this.expressCustom);
    return JsonUtils.toJson(map);
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getExpressType() {
    return expressType;
  }

  public void setExpressType(String expressType) {
    this.expressType = expressType;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getExpressCustom() {
    return expressCustom;
  }

  public void setExpressCustom(String expressCustom) {
    this.expressCustom = expressCustom;
  }

}
