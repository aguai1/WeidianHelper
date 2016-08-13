package com.aguai.weidian.lifelogic.repositories.net;

import android.support.annotation.NonNull;

import com.aguai.weidian.lifelogic.repositories.BaseRepo;
import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.DefaultWeidianClient;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.discount.VdianSeckillItemDeleteRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillItemSetRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillListGetRequest;
import com.weidian.open.sdk.request.product.VdianItemDeleteRequest;
import com.weidian.open.sdk.request.product.VdianItemListGetRequest;
import com.weidian.open.sdk.request.product.VdianItemUpdateRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.response.product.CommonItemResponse;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;

/**
 * Created by 阿怪 on 2016/8/12.
 */
public class GoodsNet extends NetBase{
    private AbstractWeidianClient client = DefaultWeidianClient.getInstance();

    public GoodsNet(@NonNull BaseRepo repository) {
        super(repository);
    }
    public VdianItemListGetResponse getVdianItemListGet(VdianItemListGetRequest vdianItemListGetRequest, int num, int page) throws OpenException {
        vdianItemListGetRequest.setPageNum(page);
        vdianItemListGetRequest.setPageSize(num);
        return client.executePost(vdianItemListGetRequest);
    }

    public CommonResponse deleteGoods(VdianItemDeleteRequest vdianItemListGetRequest) throws OpenException {
        return client.executePost(vdianItemListGetRequest);
    }

    public CommonItemResponse updateGoods(VdianItemUpdateRequest vdianItemListGetRequest) throws OpenException {
        return client.executePost(vdianItemListGetRequest);
    }
    public DiscountResponse getDiscountListGet(VdianSeckillListGetRequest vdianItemListGetRequest, int size, int page, String order) throws OpenException {
        vdianItemListGetRequest.setPageNum(page);
        vdianItemListGetRequest.setPageSize(size);
        vdianItemListGetRequest.setOrderby(order);
        return client.executePost(vdianItemListGetRequest);
    }

    public DiscountOpResponse vdianSeckillItemSet(VdianSeckillItemSetRequest vdianSeckillItemSetRequest, String itemid, String price, String end_time, String start_time, String quantity) throws OpenException{
        vdianSeckillItemSetRequest.setItemid( itemid);
        vdianSeckillItemSetRequest.setPrice(price);
        vdianSeckillItemSetRequest.setEnd_time(end_time);
        vdianSeckillItemSetRequest.setQuantity(quantity);
        vdianSeckillItemSetRequest.setStart_time(start_time);
        return client.executePost(vdianSeckillItemSetRequest);
    }

    public DiscountOpResponse vdianSeckillItemDelete(VdianSeckillItemDeleteRequest vdianSeckillItemDeleteRequest, String itemid) throws OpenException{
        vdianSeckillItemDeleteRequest.setItemID( itemid);
        return client.executePost(vdianSeckillItemDeleteRequest);
    }
}
