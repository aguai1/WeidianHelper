package com.weidian.open.sdk.request.product;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.MediaUploadResponse;

/**
 * 上传商品图片<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E4%B8%8A%E4%BC%A0%E5%95%86%E5%93%81%E5%9B%BE%E7%89%87">查看接口文档</a>
 * */
public class MediaUploadRequest extends AbstractRequest<MediaUploadResponse> {

  public MediaUploadRequest(String accessToken, byte[] multipartContent) {
    super(accessToken);
    this.multipartName = "media";
    this.multipartContent = multipartContent;
  }

  @Override
  public String getParam() throws OpenException {
    return null;
  }

}
