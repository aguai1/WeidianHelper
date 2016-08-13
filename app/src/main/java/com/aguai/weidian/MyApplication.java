package com.aguai.weidian;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.aguai.weidian.utils.ToastUtils;

/**
 * Created by 阿怪 on 2016/7/20.
 */
public class MyApplication extends Application {
    private String accessToken;
    private static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mInstance=this;
        //内存泄露
//        LeakCanary.install(this);
        ToastUtils.register(this);

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
