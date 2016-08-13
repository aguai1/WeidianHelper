package com.weidian.open.sdk.request.cps;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.cps.VdianCpsItemSearchResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 搜索CPS商品<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E6%90%9C%E7%B4%A2CPS%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianCpsItemSearchRequest extends AbstractRequest<VdianCpsItemSearchResponse> {

  private int page = 1;
  private int pageSize = 20;

  private String keyword;
  private String minPrice;
  private String maxPrice;
  private String minCpsRate;
  private String maxCpsRate;

  public VdianCpsItemSearchRequest(String accessToken) {
    super(accessToken);
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (7 / .75f) + 1);
    map.put("page", this.page);
    map.put("page_size", this.pageSize);
    map.put("keyword", this.keyword);
    map.put("min_price", this.minPrice);
    map.put("max_price", this.maxPrice);
    map.put("min_cps_rate", this.minCpsRate);
    map.put("max_cps_rate", this.maxCpsRate);
    return JsonUtils.toJson(map);
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(String minPrice) {
    this.minPrice = minPrice;
  }

  public String getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(String maxPrice) {
    this.maxPrice = maxPrice;
  }

  public String getMinCpsRate() {
    return minCpsRate;
  }

  public void setMinCpsRate(String minCpsRate) {
    this.minCpsRate = minCpsRate;
  }

  public String getMaxCpsRate() {
    return maxCpsRate;
  }

  public void setMaxCpsRate(String maxCpsRate) {
    this.maxCpsRate = maxCpsRate;
  }

}
