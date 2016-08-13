package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 删除商品分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%88%A0%E9%99%A4%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB">查看接口文档</a>
 * */
public class VdianShopCateDeleteRequest extends AbstractRequest<CommonResponse> {

  private String cateId;

  public VdianShopCateDeleteRequest(String accessToken, String cateId) {
    super(accessToken);
    this.cateId = cateId;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("cate_id", this.cateId);
    return JsonUtils.toJson(map);
  }

  public String getCateId() {
    return cateId;
  }

  public void setCateId(String cateId) {
    this.cateId = cateId;
  }

}
