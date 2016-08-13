package com.aguai.weidian.utils;

/**
 * 配置文件工具类
 */
public class Settings {
    public static boolean isTest = true;
    public static String RY_SERVER_DOMAIN() {
        return isTest ? "http://testry.renyan.cn/rest" : "http://app.ry.api.renyan.cn/rest";
    }
}
