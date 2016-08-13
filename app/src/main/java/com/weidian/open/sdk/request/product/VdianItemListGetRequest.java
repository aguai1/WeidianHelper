package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.StringUtils;

/**
 * 获取全店商品<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%85%A8%E5%BA%97%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianItemListGetRequest extends AbstractRequest<VdianItemListGetResponse> {

  private int pageNum = 1;
  private int pageSize = 30;
  private int orderby = 1;
  private String updateStart;
  private String updateEnd;

  public VdianItemListGetRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (5 / .75f) + 1);
    map.put("page_num", this.pageNum);
    map.put("page_size", this.pageSize);
    map.put("orderby", this.orderby);

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

  public int getOrderby() {
    return orderby;
  }

  public void setOrderby(int orderby) {
    this.orderby = orderby;
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
