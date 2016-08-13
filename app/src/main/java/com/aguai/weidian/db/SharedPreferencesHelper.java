package com.aguai.weidian.db;

import android.content.Context;

import com.aguai.weidian.utils.SharedPreferencesUtils;

/**
 * SharedPreferences数据处理类
 */
public class SharedPreferencesHelper {

    public static final String APP_KEY = "appkey";
    public static final String SECRET = "secret";
    public static final String IsComment = "IsComment";

    private static SharedPreferencesHelper staticInstance;

    public static SharedPreferencesHelper getInstance() {
        if (staticInstance == null) {
            staticInstance = new SharedPreferencesHelper();
        }
        return staticInstance;
    }

    public String getAppKey(Context context) {
        return (String) SharedPreferencesUtils.get(context, APP_KEY,"");
    }

    public void setAppKey(Context context, String appkey) {
        SharedPreferencesUtils.put(context, APP_KEY, appkey);
    }


    public String getSecret(Context context) {
        return (String) SharedPreferencesUtils.get(context, SECRET,"");
    }

    public void setSecret(Context context, String appkey) {
        SharedPreferencesUtils.put(context, SECRET, appkey);
    }


    public boolean getIsComment(Context context) {
        return (boolean) SharedPreferencesUtils.get(context, IsComment,false);
    }

    public void  setIsComment(Context context, boolean iscommnet) {
        SharedPreferencesUtils.put(context, IsComment, iscommnet);
    }
}
