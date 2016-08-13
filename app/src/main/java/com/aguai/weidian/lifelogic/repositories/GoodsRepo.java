package com.aguai.weidian.lifelogic.repositories;

import com.aguai.weidian.lifelogic.repositories.net.GoodsNet;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.discount.VdianSeckillItemDeleteRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillItemSetRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillListGetRequest;
import com.weidian.open.sdk.request.product.VdianItemListGetRequest;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

public class GoodsRepo extends BaseRepo {

    private GoodsNet mNet;

    public GoodsRepo() {
        mNet = new GoodsNet(this);
    }

    public Observable<VdianItemListGetResponse> getVdianItemListGet(final VdianItemListGetRequest vdianItemListGetRequest, final int size, final int page) {
        return Observable.create(new Observable.OnSubscribe<VdianItemListGetResponse>() {
            @Override
            public void call(Subscriber<? super VdianItemListGetResponse> subscriber) {
                try {
                    VdianItemListGetResponse item = mNet.getVdianItemListGet(vdianItemListGetRequest,size,page);
                    if (item != null) {
                        subscriber.onNext(item);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(newIOException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }
    public Observable<DiscountResponse> getDiscountListGet(final VdianSeckillListGetRequest vdianItemListGetRequest, final int size, final int page, final String order) {
        return Observable.create(new Observable.OnSubscribe<DiscountResponse>() {
            @Override
            public void call(Subscriber<? super DiscountResponse> subscriber) {
                try {
                    DiscountResponse item = mNet.getDiscountListGet(vdianItemListGetRequest,size,page,order);
                    if (item != null) {
                        subscriber.onNext(item);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(newIOException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }


    public  Observable<DiscountOpResponse> vdianSeckillItemSet(final VdianSeckillItemSetRequest vdianSeckillItemSetRequest, final String itemid, final String price, final String end_time, final String start_time, final String quantity) throws OpenException {
        return Observable.create(new Observable.OnSubscribe<DiscountOpResponse>() {
            @Override
            public void call(Subscriber<? super DiscountOpResponse> subscriber) {
                try {
                    DiscountOpResponse item = mNet.vdianSeckillItemSet(vdianSeckillItemSetRequest,itemid,price,start_time,end_time,quantity);
                    if (item != null) {
                        subscriber.onNext(item);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(newIOException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

    public  Observable<DiscountOpResponse> vdianSeckillItemDelete(final VdianSeckillItemDeleteRequest vdianSeckillItemDeleteRequest, final String itemid) throws OpenException{
        return Observable.create(new Observable.OnSubscribe<DiscountOpResponse>() {
            @Override
            public void call(Subscriber<? super DiscountOpResponse> subscriber) {
                try {
                    DiscountOpResponse item = mNet.vdianSeckillItemDelete(vdianSeckillItemDeleteRequest,itemid);
                    if (item != null) {
                        subscriber.onNext(item);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(newIOException());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        });
    }

}
