package com.weidian.open.sdk.request.order;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 修改物流信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E4%BF%AE%E6%94%B9%E7%89%A9%E6%B5%81%E4%BF%A1%E6%81%AF">查看接口文档</a>
 * */
public class VdianOrderExpressModifyRequest extends AbstractRequest<CommonResponse> {

  private String orderId;
  private String expressType;
  private String expressNo;
  private String expressNote;
  private String expressCustom;

  public VdianOrderExpressModifyRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (5 / .75f) + 1);
    map.put("order_id", this.orderId);
    map.put("express_type", this.expressType);
    map.put("express_no", this.expressNo);
    map.put("express_note", this.expressNote);
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

  public String getExpressNote() {
    return expressNote;
  }

  public void setExpressNote(String expressNote) {
    this.expressNote = expressNote;
  }

  public String getExpressCustom() {
    return expressCustom;
  }

  public void setExpressCustom(String expressCustom) {
    this.expressCustom = expressCustom;
  }

}
