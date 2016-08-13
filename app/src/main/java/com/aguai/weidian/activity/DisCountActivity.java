package com.aguai.weidian.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.aguai.weidian.MyApplication;
import com.aguai.weidian.adpter.GoodsAdapter;
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
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.discount.VdianSeckillItemSetRequest;
import com.weidian.open.sdk.request.product.VdianItemListGetRequest;
import com.weidian.open.sdk.response.discount.DiscountOpResponse;
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
public  class DisCountActivity extends BaseActivity{
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.refreshlay)SwipeRefreshLayout swipeRefreshLayout;
	@Bind(R.id.recycler)RecyclerView recyclerView;
	@Bind(R.id.ballview)BallView ballView;
	@Bind(R.id.tv_discounttime)TextView tv_discounttime;
	@Bind(R.id.bannerContainer) ViewGroup bannerContainer;
	BannerView bv;
	@Bind(R.id.spinner_discountnum) Spinner spinner_discountnum;
	private GoodsRepo goodsRepo;
	private GoodsNet goodnet;
	private VdianItemListGetRequest vdianItemListGetRequest;
	private VdianSeckillItemSetRequest vdianSeckillItemSetRequest;
	private GoodsAdapter addTextAdapter;
	private List<VdianItemListGetResponse.ListItem> list;
	private int currentpage=1;
	private boolean isSucceed=false;
	private boolean isSelectAll=false;
	private int allOpNum;
	private int currentOpNum;
	private int year,month,day,hour,minute,sec;
	private String endTime;
	private String startTime;
	@Override
	public int bindLayout() {
		return R.layout.activity_discount;
	}

	@Override
	public void initView(View view) {
		initActionBar();
		initSpinner();
		list=new ArrayList<>();
		recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
		addTextAdapter=new GoodsAdapter(DisCountActivity.this,list,recyclerView);
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
	private  final Integer op[]={1,2,3,4,5,6,7,8,9};
	private int currentDiscountNum=1;
	private void initSpinner() {
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, op);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_discountnum.setAdapter(adapter);
		spinner_discountnum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				currentDiscountNum=op[position];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	@Override
	public void doBusiness(Context mContext) {
		goodsRepo=new GoodsRepo();
		goodnet=new GoodsNet(goodsRepo);
		if(MyApplication.getInstance().getAccessToken()==null){
			Intent intent =  new Intent(DisCountActivity.this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			return;
		}
		vdianSeckillItemSetRequest=new VdianSeckillItemSetRequest(MyApplication.getInstance().getAccessToken());
		vdianItemListGetRequest=new VdianItemListGetRequest(MyApplication.getInstance().getAccessToken());
		getGoods();
		initBanner();
		bv.loadAD();

		Time time = new Time("GMT+8");
		time.setToNow();
		 year = time.year;
		 month = time.month;
		 day = time.monthDay;
		 minute = time.minute;
		 hour = time.hour;
		 sec = time.second+15;
		startTime=year+"-"+month+"-"+day+" 00:00:00";
		endTime=year+"-"+(month+1)+"-"+day+" 00:00:00";
		tv_discounttime.setText(endTime.substring(0,endTime.length()-9));
	}
	private void getGoods() {
		Subscription s =  goodsRepo.getVdianItemListGet(vdianItemListGetRequest, UrlConstants.pagenum, currentpage)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(this.<VdianItemListGetResponse>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Action1<VdianItemListGetResponse>() {
							   @Override
							   public void call(VdianItemListGetResponse tokenInfo) {
								   if(tokenInfo.getStatus().getStatusCode()==0){
									   isSucceed=true;
									   if(tokenInfo!=null){
										   Collections.addAll(list, tokenInfo.getResult().getItems());
										   addTextAdapter.notifyDataChanged();
										   if(tokenInfo.getResult().getItems().length<UrlConstants.pagenum){
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
	@OnClick({R.id.btn_op,R.id.btn_lookalldiscount,R.id.tv_discounttime})
	public void OnClick(View view){
		switch (view.getId()){
			case R.id.btn_op:
				opList();
				break;
			case R.id.btn_lookalldiscount:
				startActivity(new Intent(DisCountActivity.this,AllDisCountActivity.class));
				break;
			case R.id.tv_discounttime:
				DatePickerDialog datePickerDialog=new DatePickerDialog(DisCountActivity.this, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						endTime=year+"-"+monthOfYear+"-"+dayOfMonth+" 00:00:00";
						tv_discounttime.setText(endTime.substring(0,endTime.length()-9));
					}
				},year,month,day);
				datePickerDialog.show();
				break;

		}
	}

	private void opList() {
		List<VdianItemListGetResponse.ListItem> selectedItems = addTextAdapter.getSelectedItems();
		allOpNum=selectedItems.size();
		currentOpNum=0;
		loadingDialog.setMsg("删除进度0/"+allOpNum);
		loadingDialog.show();
		discount(selectedItems);
	}

	private void discount(List<VdianItemListGetResponse.ListItem> selectedItems) {
		Observable.from(selectedItems)
				.map(new Func1<VdianItemListGetResponse.ListItem, DiscountOpResponse>() {
					@Override
					public DiscountOpResponse call(VdianItemListGetResponse.ListItem listItem) {
						DiscountOpResponse profile = null;
						try {
							listItem.setPrice(Double.parseDouble(listItem.getPrice())*currentDiscountNum/10+"");
							profile = goodnet.vdianSeckillItemSet(vdianSeckillItemSetRequest,listItem.getItemId(),listItem.getPrice(),endTime,startTime,"100");
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
						showCommentActivity();
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
								loadingDialog.setMsg("设置折扣进度"+currentOpNum+"/"+allOpNum);
							}
							addTextAdapter.notifyDataChanged();
						}
					}
				});
	}

}
