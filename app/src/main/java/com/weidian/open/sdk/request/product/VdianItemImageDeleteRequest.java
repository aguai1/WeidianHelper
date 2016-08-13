package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 删除商品图片<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%88%A0%E9%99%A4%E5%95%86%E5%93%81%E5%9B%BE%E7%89%87">查看接口文档</a>
 * */
public class VdianItemImageDeleteRequest extends AbstractRequest<CommonResponse> {

  private String itemId;
  private String[] deleteImgs;

  public VdianItemImageDeleteRequest(String accessToken, String itemId, String[] deleteImgs) {
    super(accessToken);
    this.itemId = itemId;
    this.deleteImgs = deleteImgs;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (2 / .75f) + 1);
    map.put("itemid", this.itemId);
    map.put("delete_imgs", this.deleteImgs);
    return JsonUtils.toJson(map);
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String[] getDeleteImgs() {
    return deleteImgs;
  }

  public void setDeleteImgs(String[] deleteImgs) {
    this.deleteImgs = deleteImgs;
  }

}
