package com.weidian.open.sdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUtils {

  public static String encode(String s) {
    try {
      return URLEncoder.encode(s, SystemConfig.ENC_UTF8);
    } catch (UnsupportedEncodingException e) {
      return "";
    }
  }
}
