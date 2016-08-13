package com.aguai.weidian.lifelogic.network;


public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
