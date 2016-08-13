package com.aguai.weidian.lifelogic.repositories.net;

import android.support.annotation.NonNull;
import com.aguai.weidian.lifelogic.network.request.OkHttpGetRequest;
import com.aguai.weidian.lifelogic.network.request.OkHttpPostRequest;
import com.aguai.weidian.lifelogic.repositories.BaseRepo;
import com.aguai.weidian.lifelogic.repositories.entities.TokenInfo;
import com.aguai.weidian.utils.UrlConstants;
import com.squareup.okhttp.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zixintechno on 16/7/27.
 */
public class TokenNet extends NetBase {

    public TokenNet(@NonNull BaseRepo repository) {
        super(repository);
    }

    public TokenInfo getToken(String appkey, String secret) throws IOException, JSONException{
        Map<String,String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appkey", appkey);
        params.put("secret", secret);
        OkHttpGetRequest request = new OkHttpGetRequest(UrlConstants.getToken,
                null,params,null);
        Response response = request.execute();
        return mGson.fromJson(response.body().string(), TokenInfo.class);
    }



}

