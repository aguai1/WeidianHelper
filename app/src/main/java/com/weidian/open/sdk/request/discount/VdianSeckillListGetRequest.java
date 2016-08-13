package com.weidian.open.sdk.request.discount;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取折扣信息<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%A8%E5%BA%97%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianSeckillListGetRequest extends AbstractRequest<DiscountResponse> {

  private int pageNum = 1;
  private int pageSize = 30;
  private String orderby = "1";

  public VdianSeckillListGetRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (5 / .75f) + 1);
    map.put("page_num", this.pageNum);
    map.put("page_size", this.pageSize);
    map.put("orderby", this.orderby);
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

  public String getOrderby() {
    return orderby;
  }

  public void setOrderby(String orderby) {
    this.orderby = orderby;
  }



}
