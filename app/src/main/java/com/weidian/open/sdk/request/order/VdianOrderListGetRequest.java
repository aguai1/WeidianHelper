package com.weidian.open.sdk.request.order;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.order.VdianOrderListGetResponse;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.StringUtils;

/**
 * 获取订单列表<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E5%88%97%E8%A1%A8">查看接口文档</a>
 * */
public class VdianOrderListGetRequest extends AbstractRequest<VdianOrderListGetResponse> {

  private int pageNum = 1;
  private int pageSize = 30;
  private String orderType;
  private String addStart;
  private String addEnd;
  private String updateStart;
  private String updateEnd;

  public VdianOrderListGetRequest(String accessToken) {
    super(accessToken);
    super.version = "1.1";
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (7 / .75f) + 1);
    map.put("page_num", this.pageNum);
    map.put("page_size", this.pageSize);
    map.put("order_type", this.orderType);
    map.put("add_start", this.addStart);
    map.put("add_end", this.addEnd);

    if (StringUtils.isNotEmpty(this.addStart)) {
      map.put("add_start", this.addStart);
    }

    if (StringUtils.isNotEmpty(this.addEnd)) {
      map.put("add_end", this.addEnd);
    }

    if (StringUtils.isNotEmpty(this.updateStart)) {
      map.put("update_start", this.updateStart);
    }

    if (StringUtils.isNotEmpty(this.updateEnd)) {
      map.put("update_end", this.updateEnd);
    }
    return JsonUtils.toJson(map);
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getAddStart() {
    return addStart;
  }

  public void setAddStart(String addStart) {
    this.addStart = addStart;
  }

  public String getAddEnd() {
    return addEnd;
  }

  public void setAddEnd(String addEnd) {
    this.addEnd = addEnd;
  }

  public String getUpdateStart() {
    return updateStart;
  }

  public void setUpdateStart(String updateStart) {
    this.updateStart = updateStart;
  }

  public String getUpdateEnd() {
    return updateEnd;
  }

  public void setUpdateEnd(String updateEnd) {
    this.updateEnd = updateEnd;
  }

}
