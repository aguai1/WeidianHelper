package com.weidian.open.sdk.request.product;

import com.weidian.open.sdk.entity.Item;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.CommonItemResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 创建微店商品<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E5%88%9B%E5%BB%BA%E5%BE%AE%E5%BA%97%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class VdianItemAddRequest extends AbstractRequest<CommonItemResponse> {

  private Item item;

  public VdianItemAddRequest(String accessToken, Item item) {
    super(accessToken);
    this.item = item;
  }

  @Override
  public String getParam() throws OpenException {
    return JsonUtils.toJson(this.item);
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

}
