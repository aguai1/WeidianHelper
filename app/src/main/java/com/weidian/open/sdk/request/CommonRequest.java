package com.weidian.open.sdk.request;

import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.DefaultWeidianClient;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.http.Param;
import com.weidian.open.sdk.util.JsonUtils;
import com.weidian.open.sdk.util.SystemConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyadi on 16/7/5.
 * @Email:wangyadi@weidian.com
 * 通用的接口调用方式,将wiki中示例代码放入即可
 */
public class CommonRequest extends AbstractCommonRequest {

    private AbstractWeidianClient client = DefaultWeidianClient.getInstance();

    public CommonRequest(String accessToken) {
        super(accessToken);
    }

    public String vdianOrderListGet(String page_num, String page_size, String order_type, String add_start, String add_end, String update_start, String update_end) throws OpenException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page_num", page_num);
        map.put("page_size", page_size);
        map.put("order_type", order_type);
        map.put("add_start", add_start);
        map.put("add_end", add_end);
        map.put("update_start", update_start);
        map.put("update_end", update_end);
        super.removeNullValue(map);
        return client.executePostForString(SystemConfig.API_URL_FOR_POST,
                new Param(SystemConfig.PUBLIC_PARAM, buildPublicValue("vdian.order.list.get", "1.1")),
                new Param(SystemConfig.BIZ_PARAM, JsonUtils.toJson(map)));
    }



}
