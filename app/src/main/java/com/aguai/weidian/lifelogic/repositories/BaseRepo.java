package com.aguai.weidian.lifelogic.repositories;

import java.io.IOException;

public class BaseRepo {

    /**
     * 网络请求数据,通信失败
     *
     * @param e
     */
    public void onNetReject(Exception e) {

    }
    /**
     * 请求数据成功
     *
     * @param entity
     */
    public void onNetResponse(Object entity) {

    }

    protected IOException newIOException() {
        return new IOException("网络连接异常,请稍后再试");
    }



}
