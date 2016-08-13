package com.aguai.weidian.lifelogic;

import com.google.gson.Gson;

/**
 * 目前该类功能只是持有Gson的单例,
 * 后期所有json解析都应经过此类
 */
public class JsonUtil {

    private static Gson mGson;

    public static Gson getParser() {
        if(mGson == null) {
            synchronized (JsonUtil.class) {
                if(mGson == null) {
                    mGson = new Gson();
                }
            }
        }
        return mGson;
    }
}
