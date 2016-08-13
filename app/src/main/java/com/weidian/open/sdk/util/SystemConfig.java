package com.weidian.open.sdk.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemConfig {

  private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfig.class);

  public static final String ENC_UTF8 = "utf-8";

  public static final String MEDIA_UPLOAD_URL_TEMPLATE = "https://api.vdian.com/media/upload?access_token=%s";

  public static final String API_URL_FOR_POST = "https://api.vdian.com/api";

  public static final String PUBLIC_PARAM = "public";

  public static final String BIZ_PARAM = "param";

  public static final String API_URL_TEMPLATE_FOR_GET = API_URL_FOR_POST + "?" + PUBLIC_PARAM + "=%s&" + BIZ_PARAM
      + "=%s";

  public static final String PERSONAL_TOKEN_URL_TEMPLATE =
      "https://api.vdian.com/token?grant_type=client_credential&appkey=%s&secret=%s";

  public static final String OAUTH2_AUTHORIZE_URL_TEMPLATE =
      "https://api.vdian.com/oauth2/authorize?response_type=code&appkey=%s&redirect_uri=%s&state=%s";

  public static final String OAUTH2_TOKEN_URL_TEMPLATE =
      "https://api.vdian.com/oauth2/access_token?grant_type=authorization_code&appkey=%s&secret=%s&code=%s";

  public static final String OAUTH2_REFRESH_TOKEN_URL_TEMPLATE =
      "https://api.vdian.com/oauth2/refresh_token?grant_type=refresh_token&appkey=%s&refresh_token=%s";

  public static String APPKEY = "";
  public static String SECRET = "";

  public static int HTTP_POOL_MAX_TOTAL = 1000;
  public static int HTTP_MAX_PER_ROUTE = 400;
  public static int HTTP_CONNECTION_TIMEOUT = 2000;
  public static int HTTP_READ_TIMEOUT = 5000;

  static {
    InputStream in = null;
    try {
      Properties prop = new Properties();
      in = SystemConfig.class.getClassLoader().getResourceAsStream("weidian_open.properties");
      prop.load(in);

      APPKEY = empty2Default(prop.getProperty("appkey"), APPKEY);
      SECRET = empty2Default(prop.getProperty("secret"), SECRET);

      HTTP_POOL_MAX_TOTAL = empty2Default(prop.getProperty("http_pool_max_total"), HTTP_POOL_MAX_TOTAL);
      HTTP_MAX_PER_ROUTE = empty2Default(prop.getProperty("http_max_per_route"), HTTP_MAX_PER_ROUTE);
      HTTP_CONNECTION_TIMEOUT = empty2Default(prop.getProperty("http_connection_timeout"), HTTP_CONNECTION_TIMEOUT);
      HTTP_READ_TIMEOUT = empty2Default(prop.getProperty("http_read_timeout"), HTTP_READ_TIMEOUT);
    } catch (Exception e) {
      LOGGER.error("SystemConfig init error", e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          // ignore
        }
      }
    }
  }

  private static String empty2Default(String str, String defaultValue) {
    return str == null || str.length() == 0 ? defaultValue : str.trim();
  }

  private static int empty2Default(String str, int defaultValue) {
    return str == null || str.length() == 0 ? defaultValue : Integer.valueOf(str.trim());
  }

  public static void main(String[] args) {
    System.out.println(SystemConfig.HTTP_POOL_MAX_TOTAL);
  }
}
