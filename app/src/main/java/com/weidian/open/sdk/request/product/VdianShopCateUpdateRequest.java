package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.entity.Cate;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 编辑商品分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E7%BC%96%E8%BE%91%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB">查看接口文档</a>
 * */
public class VdianShopCateUpdateRequest extends AbstractRequest<CommonResponse> {

  private Cate[] cates;

  public VdianShopCateUpdateRequest(String accessToken, Cate[] cates) {
    super(accessToken);
    this.cates = cates;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("cates", this.cates);
    return JsonUtils.toJson(map);
  }

  public Cate[] getCates() {
    return cates;
  }

  public void setCates(Cate[] cates) {
    this.cates = cates;
  }

}
