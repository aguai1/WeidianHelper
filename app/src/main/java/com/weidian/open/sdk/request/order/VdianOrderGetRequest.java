package com.weidian.open.sdk.request.order;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.order.VdianOrderGetResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 获取订单详情<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E8%AF%A6%E6%83%85">查看接口文档</a>
 * */
public class VdianOrderGetRequest extends AbstractRequest<VdianOrderGetResponse> {

  private String orderId;

  public VdianOrderGetRequest(String accessToken, String orderId) {
    super(accessToken);
    this.orderId = orderId;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("order_id", this.orderId);
    return JsonUtils.toJson(map);
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

}
