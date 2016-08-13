package com.aguai.weidian.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {

    public static Context sContext;


    public static void register(Context context) {
        sContext = context.getApplicationContext();
    }


    private static boolean check() {
        return sContext != null;
    }


    public static void showShort(@android.support.annotation.StringRes int resId) {
        if (check()) {
            Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
        }

    }


    public static void showShort(String message) {
        if (check()) {
            Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
        }

    }


    public static void showLong(@android.support.annotation.StringRes int resId) {
        if (check()) {
            Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
        }

    }


    public static void showLong(String message) {
        if (check()) {
            Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
        }
    }
}
