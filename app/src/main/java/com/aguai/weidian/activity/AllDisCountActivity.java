package com.aguai.weidian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aguai.weidian.MyApplication;
import com.aguai.weidian.adpter.DiscountAdapter;
import com.aguai.weidian.adpter.ProgressAdapter;
import com.aguai.weidian.lifelogic.repositories.GoodsRepo;
import com.aguai.weidian.lifelogic.repositories.net.GoodsNet;
import com.aguai.weidian.utils.GdtConstants;
import com.aguai.weidian.utils.ToastUtils;
import com.aguai.weidian.utils.UrlConstants;
import com.example.testzjut.R;
import com.github.glomadrian.loadingballs.BallView;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.trello.rxlifecycle.ActivityEvent;
import com.weidian.open.sdk.entity.DiscountItem;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.CommonRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillItemDeleteRequest;
import com.weidian.open.sdk.request.discount.VdianSeckillListGetRequest;
import com.weidian.open.sdk.request.product.VdianItemDeleteRequest;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
import com.weidian.open.sdk.response.discount.DiscountResponse;
import com.weidian.open.sdk.response.product.VdianItemListGetResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *折扣
 * */
public  class AllDisCountActivity extends BaseActivity{
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.refreshlay)SwipeRefreshLayout swipeRefreshLayout;
	@Bind(R.id.recycler)RecyclerView recyclerView;
	@Bind(R.id.ballview)BallView ballView;
	@Bind(R.id.bannerContainer) ViewGroup bannerContainer;
	BannerView bv;
	private GoodsRepo goodsRepo;
	private GoodsNet goodnet;
	private VdianSeckillListGetRequest discountRequest;
	private DiscountAdapter addTextAdapter;
	private List<DiscountItem> list;
	private int currentpage=1;
	private boolean isSucceed=false;
	private boolean isSelectAll=false;

	private int allOpNum;
	private int currentOpNum;
	private VdianSeckillItemDeleteRequest deleteRequest;


	@Override
	public int bindLayout() {
		return R.layout.activity_alldiscount;
	}

	@Override
	public void initView(View view) {
		initActionBar();
		list=new ArrayList<>();
		recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
		addTextAdapter=new DiscountAdapter(AllDisCountActivity.this,list,recyclerView);
		recyclerView.setAdapter(addTextAdapter);
		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				if(!isSucceed){
					getGoods();
				}
				swipeRefreshLayout.setRefreshing(false);
			}
		});
		addTextAdapter.setOnLoadMoreListener(new ProgressAdapter.OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				getGoods();
			}
		});
	}


	@Override
	public void doBusiness(Context mContext) {
		goodsRepo=new GoodsRepo();
		deleteRequest=new VdianSeckillItemDeleteRequest(MyApplication.getInstance().getAccessToken());
		goodnet=new GoodsNet(goodsRepo);
		if(MyApplication.getInstance().getAccessToken()==null){
			Intent intent =  new Intent(AllDisCountActivity.this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			return;
		}
		discountRequest=new VdianSeckillListGetRequest(MyApplication.getInstance().getAccessToken());
		getGoods();
		initBanner();
		bv.loadAD();
	}
	private void getGoods() {
		Subscription s =  goodsRepo.getDiscountListGet(discountRequest,50, currentpage,"time")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(this.<DiscountResponse>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Action1<DiscountResponse>() {
							   @Override
							   public void call(DiscountResponse tokenInfo) {
								   if(tokenInfo.getStatus().getStatusCode()==0){
									   isSucceed=true;
									   if(tokenInfo!=null){
										   Collections.addAll(list, tokenInfo.getResult().getList());
										   addTextAdapter.notifyDataChanged();
										   if(tokenInfo.getResult().getList().length<50){
											   addTextAdapter.setFixed(true);
										   }
										   currentpage++;
									   }
								   }else{
									   addTextAdapter.setLoading(false);
									   ToastUtils.showShort(tokenInfo.getStatus().getStatusReason());
								   }
								   ballView.setVisibility(View.INVISIBLE);
							   }
						   },
						new Action1<Throwable>() {
							@Override
							public void call(Throwable throwable) {
								addTextAdapter.setLoading(false);
								addTextAdapter.notifyDataChanged();
								handleThrowable(throwable);
							}
						});

	}

	@Override
	public void initPrame(Bundle bundle) {

	}
	private void initBanner() {
		this.bv = new BannerView(this, ADSize.BANNER, GdtConstants.APPID, GdtConstants.DisCountActivityBannerPosID);
		bv.setRefresh(30);
		bv.setADListener(new AbstractBannerADListener() {

			@Override
			public void onNoAD(int arg0) {
				Log.i("AD_DEMO", "BannerNoAD，eCode=" + arg0);
			}

			@Override
			public void onADReceiv() {
				Log.i("AD_DEMO", "ONBannerReceive");
			}
		});
		bannerContainer.addView(bv);
	}
	private void initActionBar() {
		if (toolbar != null) {
			toolbar.setTitle(getString(R.string.discount));
			toolbar.inflateMenu(R.menu.menu_select_goods);
			toolbar.setNavigationIcon(R.mipmap.ic_back);
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
			toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()){
						case R.id.action_select_all:
							isSelectAll=!isSelectAll;
							addTextAdapter.selectAll(isSelectAll);
							break;
					}
					return false;
				}
			});
		}
	}
	@OnClick(R.id.delete_discount)
	public void OnClick(View view){
		switch (view.getId()){
			case R.id.delete_discount:
				List<DiscountItem> oplist = addTextAdapter.getSelectedItems();
				allOpNum=oplist.size();
				currentOpNum=0;
				loadingDialog.setMsg("删除进度0/"+allOpNum);
				loadingDialog.show();
				delete(oplist);
				break;

		}
	}

	private void delete(List<DiscountItem> oplist){
		Observable.from(oplist)
				.map(new Func1<DiscountItem, DiscountOpResponse>() {
					@Override
					public DiscountOpResponse call(DiscountItem listItem) {
						DiscountOpResponse profile = null;
						list.remove(listItem);
						try {
							profile = goodnet.vdianSeckillItemDelete(deleteRequest,listItem.getItemid());
						} catch (OpenException e) {
							e.printStackTrace();
						}
						return profile;
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(this.<DiscountOpResponse>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Subscriber<DiscountOpResponse>() {
					@Override
					public void onCompleted() {
						loadingDialog.dismiss();
					}

					@Override
					public void onError(Throwable e) {
						loadingDialog.dismiss();
						addTextAdapter.notifyDataChanged();
					}
					@Override
					public void onNext(DiscountOpResponse commonResponse) {
						if(commonResponse!=null) {
							if(commonResponse.getStatus().getStatusCode()==0){
								currentOpNum++;
								loadingDialog.setMsg("删除进度"+currentOpNum+"/"+allOpNum);
							}
							addTextAdapter.notifyDataChanged();
						}
					}
				});
	}
}
