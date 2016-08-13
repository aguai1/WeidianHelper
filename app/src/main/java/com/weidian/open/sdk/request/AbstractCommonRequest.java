package com.weidian.open.sdk.request;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.util.JsonUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wangyadi on 16/7/5.
 * @Email:wangyadi@weidian.com
 */
public abstract class AbstractCommonRequest {

    protected String accessToken;

    public AbstractCommonRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    protected String buildPublicValue(String method, String version) throws OpenException {
        return this.buildPublicValue(method, version, "json");
    }

    protected String buildPublicValue(String method, String version, String format) throws OpenException {
        Map<String, String> map = new HashMap<String, String>(8);
        map.put("method", method);
        map.put("access_token", this.accessToken);
        map.put("version", version);
        map.put("format", format);
        map.put("lang", "java");
        return JsonUtils.toJson(map);
    }

    protected Map<String, Object> removeNullValue(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            if(entry.getValue() == null) {
                it.remove();
            }
        }
        return map;
    }
}
