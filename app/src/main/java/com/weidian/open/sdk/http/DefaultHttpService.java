package com.weidian.open.sdk.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.aguai.weidian.lifelogic.network.request.OkHttpGetRequest;
import com.aguai.weidian.lifelogic.network.request.OkHttpPostRequest;
import com.squareup.okhttp.Response;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.util.SystemConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DefaultHttpService implements HttpService {

  public DefaultHttpService() {
    super();
  }

  private static DefaultHttpService instance = new DefaultHttpService();

  public static DefaultHttpService getInstance() {
    return instance;
  }

  @Override
  public String get(String url) throws OpenException {
//    return this.httpExecute(new HttpGet(url), url);
    return "";
  }

//  json格式是：形式 需要组装https://api.vdian.com/api?param={"page_num":1,"page_size":10,"orderby":1,"update_start":"2012-11-12 16:36:08","update_end":"2015-11-12 16:36:08","status":1}&public={"method":"vdian.item.list.get","access_token":"40be967eabb8057fc7975ed64895b5d900023716b1","version":"1.0","format":"json"}
  @Override
  public String post(String url, Param... params) throws IOException {
      Map<String, String> map = new HashMap<>();
      if (params != null && params.length > 0) {
        for (Param p : params) {
          map.put(p.getName(), p.getValue());
        }
        String s1 = map.toString();
        s1 = s1.substring(1, s1.length() - 1);
        OkHttpPostRequest postRequest = new OkHttpPostRequest(url,s1,map,null);
        Response s = postRequest.execute();
        return s.body().string();
      }
    return "";
  }
  @Override
  public String multipart(String url, String name, byte[] content) throws OpenException {
//    HttpPost httpPost = new HttpPost(url);
//    HttpEntity entity = MultipartEntityBuilder.create().addPart(name, new ByteArrayBody(content, "media.jpg")).build();
//    httpPost.setEntity(entity);
//    return httpExecute(httpPost, url);
    return "";
  }

}
