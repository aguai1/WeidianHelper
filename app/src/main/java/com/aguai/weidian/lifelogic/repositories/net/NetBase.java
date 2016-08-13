package com.aguai.weidian.lifelogic.repositories.net;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.aguai.weidian.MyApplication;
import com.aguai.weidian.lifelogic.JsonUtil;
import com.aguai.weidian.lifelogic.repositories.BaseRepo;
import com.aguai.weidian.utils.Settings;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class NetBase {
    protected final static String mDomain = Settings.RY_SERVER_DOMAIN();

    protected final Gson mGson = JsonUtil.getParser();

    protected BaseRepo mRepository;

    private String X_VERSION;

    public NetBase() {
        PackageManager pm = MyApplication.getInstance().getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            X_VERSION = "Android" + pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public NetBase(@NonNull BaseRepo repository) {
        mRepository = repository;
        PackageManager pm = MyApplication.getInstance().getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(MyApplication.getInstance().getPackageName(), 0);
            X_VERSION = "Android-" + pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    protected Map<String, String> getHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("X-ApiKey", "j8slb29fbalc83pna2af2c2954hcw65");
//        map.put("X-AuthToken", getToken());
//        map.put("X-User", "" + getUserId());
        map.put("Content-Type", "application/json;charset=utf-8");
        map.put("X-VERSION", X_VERSION);
        return map;
    }

    protected Map<String, String> getHeaders(int uid,String token) {
        Map<String, String> map = new HashMap<>();
        map.put("X-ApiKey", "j8slb29fbalc83pna2af2c2954hcw65");
        map.put("X-AuthToken", token);
        map.put("X-User", "" + uid);
        map.put("Content-Type", "application/json;charset=utf-8");
        map.put("X-VERSION", X_VERSION);
        return map;
    }

    protected Map<String, String> getNoAuthHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("X-ApiKey", "j8slb29fbalc83pna2af2c2954hcw65");
        map.put("Content-Type", "application/json;charset=utf-8");
        map.put("X-VERSION", X_VERSION);
        return map;
    }

    protected Map<String, String> getCleanHeaders() {
        Map<String, String> map = new HashMap<>();
        map.put("X-ApiKey", "j8slb29fbalc83pna2af2c2954hcw65");
        map.put("X-VERSION", X_VERSION);
        return map;
    }
//
//    protected Map<String, String> getRefreshTokenHeader() {
//        Map<String, String> map = new HashMap<>();
//        map.put("X-ApiKey", "j8slb29fbalc83pna2af2c2954hcw65");
//        map.put("X-User", "" + getUserId());
//        map.put("X-Refresh", getRefreshToken());
//        map.put("X-VERSION", X_VERSION);
//        return map;
//    }
//    protected int getUserId() {
//        return RyApplication.getInstance().getLocalUser().getUser().getUid();
//    }
//    protected String getToken() {
//        return RyApplication.getInstance().getLocalUser().getToken();
//    }
//
//
//
//    protected String getRefreshToken() {
//        return RyApplication.getInstance().getLocalUser().getRefresh_token();
//    }
}
