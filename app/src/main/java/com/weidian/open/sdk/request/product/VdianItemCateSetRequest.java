package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 设置商品的分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%AE%BE%E7%BD%AE%E5%95%86%E5%93%81%E7%9A%84%E5%88%86%E7%B1%BB">查看接口文档</a>
 * */
public class VdianItemCateSetRequest extends AbstractRequest<CommonResponse> {

  private String[] itemIds;
  private String[] cateIds;

  public VdianItemCateSetRequest(String accessToken, String[] itemIds, String[] cateIds) {
    super(accessToken);
    this.itemIds = itemIds;
    this.cateIds = cateIds;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("item_ids", this.itemIds);
    map.put("cate_ids", this.cateIds);
    return JsonUtils.toJson(map);
  }

  public String[] getItemIds() {
    return itemIds;
  }

  public void setItemIds(String[] itemIds) {
    this.itemIds = itemIds;
  }

  public String[] getCateIds() {
    return cateIds;
  }

  public void setCateIds(String[] cateIds) {
    this.cateIds = cateIds;
  }

}
