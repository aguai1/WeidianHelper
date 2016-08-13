package com.aguai.weidian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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
import com.weidian.open.sdk.request.product.VdianItemListGetRequest;
import com.weidian.open.sdk.request.product.VdianItemUpdateRequest;
import com.weidian.open.sdk.response.product.CommonItemResponse;
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
 *选择商品
 * */
public  class ModifyInfoActivity extends BaseActivity{
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.refreshlay)SwipeRefreshLayout swipeRefreshLayout;
	@Bind(R.id.recycler)RecyclerView recyclerView;
	@Bind(R.id.ballview)BallView ballView;

	@Bind(R.id.spinner_price) Spinner spinner_price;
	@Bind(R.id.spinner_stock) Spinner spinner_stock;

	@Bind(R.id.et_pricerite)EditText et_price;
	@Bind(R.id.et_stock)EditText et_stock;

	@Bind(R.id.bannerContainer)
	ViewGroup bannerContainer;
	BannerView bv;

	private GoodsRepo goodsRepo;
	private GoodsNet goodsNet;
	private VdianItemListGetRequest vdianItemListGetRequest;
	private GoodsAdapter addTextAdapter;
	private List<VdianItemListGetResponse.ListItem> list;

	private String price_op="+";
	private String stock_op="+";
	private int currentpage=1;
	private  final String op[]={"+","-","*","/"};
	private boolean isSucceed=false;
	private boolean isSelectAll=false;
	private int currentOpNum;
	private int allOpNum;
	@Override
	public int bindLayout() {
		return R.layout.activity_modifyinfo;
	}

	@Override
	public void initView(View view) {
		initActionBar();
		initBanner();
		bv.loadAD();
		initSpinner();
		list=new ArrayList<>();
		recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
		addTextAdapter=new GoodsAdapter(ModifyInfoActivity.this,list,recyclerView);
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
	private void initBanner() {
		this.bv = new BannerView(this, ADSize.BANNER, GdtConstants.APPID, GdtConstants.MotifyActivityBannerPosID);
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
	private void initSpinner() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, op);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_price.setAdapter(adapter);
		spinner_price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				price_op=op[position];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		spinner_stock.setAdapter(adapter);
		spinner_stock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				stock_op=op[position];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	@Override
	public void doBusiness(Context mContext) {
		goodsRepo=new GoodsRepo();
		goodsNet=new GoodsNet(goodsRepo);
		if(MyApplication.getInstance().getAccessToken()==null){
			Intent intent =  new Intent(ModifyInfoActivity.this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
			return;
		}
		vdianItemListGetRequest=new VdianItemListGetRequest(MyApplication.getInstance().getAccessToken());
		getGoods();
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
	private void initActionBar() {
		if (toolbar != null) {
			toolbar.setTitle(getString(R.string.modify_price));
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
	@OnClick({R.id.btn_op,R.id.btn_look})
	public void OnClick(View view){
		switch (view.getId()){
			case R.id.btn_op:
				opItems();
				break;
			case R.id.btn_look:
				opLook();
				break;
		}
	}

	private void opLook() {
		if (isEmpty()){
			return;
		}
		List<VdianItemListGetResponse.ListItem> selectedItems = addTextAdapter.getSelectedItems();
		for (VdianItemListGetResponse.ListItem listItem:selectedItems){
			opFinalNum(listItem);
		}
		addTextAdapter.notifyDataChanged();
	}

	private void opItems() {
		if (isEmpty()){
			return;
		}
		List<VdianItemListGetResponse.ListItem> selectedItems = addTextAdapter.getSelectedItems();
		allOpNum=selectedItems.size();
		currentOpNum=0;
		loadingDialog.setMsg("修改进度0/"+allOpNum);
		loadingDialog.show();
		Observable.from(selectedItems)
				.map(new Func1<VdianItemListGetResponse.ListItem, CommonItemResponse>() {
					@Override
					public CommonItemResponse call(VdianItemListGetResponse.ListItem listItem) {
						opNum(listItem);
						CommonItemResponse profile = null;
						VdianItemUpdateRequest vdianItemUpdateRequest =new VdianItemUpdateRequest(MyApplication.getInstance().getAccessToken(),listItem);
						try {
							profile = goodsNet.updateGoods(vdianItemUpdateRequest);
						} catch (OpenException e) {
							e.printStackTrace();
						}
						return profile;
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(this.<CommonItemResponse>bindUntilEvent(ActivityEvent.DESTROY))
				.subscribe(new Subscriber<CommonItemResponse>() {
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
					public void onNext(CommonItemResponse commonResponse) {
						if(commonResponse!=null) {
							if(commonResponse.getStatus().getStatusCode()==0){
								currentOpNum++;
								loadingDialog.setMsg("修改进度"+currentOpNum+"/"+allOpNum);
							}
							addTextAdapter.notifyDataChanged();
						}
					}
				});
	}

	private void opNum(VdianItemListGetResponse.ListItem listItem) {
		if(!TextUtils.isEmpty(et_price.getText().toString())) {
			switch (price_op) {
				case "+":
					listItem.setPrice((Double.parseDouble(listItem.getPrice()) + Double.parseDouble(et_price.getText().toString())) + "");
					break;
				case "-":
					listItem.setPrice((Double.parseDouble(listItem.getPrice()) - Double.parseDouble(et_price.getText().toString())) + "");
					break;
				case "*":
					listItem.setPrice((Double.parseDouble(listItem.getPrice()) * Double.parseDouble(et_price.getText().toString())) + "");
					break;
				case "/":
					listItem.setPrice((Double.parseDouble(listItem.getPrice()) / Double.parseDouble(et_price.getText().toString())) + "");
					break;
			}
		}
		if(!TextUtils.isEmpty(et_stock.getText().toString())) {
			switch (stock_op) {
				case "+":
					listItem.setStock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "-":
					listItem.setStock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "*":
					listItem.setStock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "/":
					listItem.setStock(listItem.getStock() / Integer.parseInt(et_stock.getText().toString()));
					break;
			}
		}
	}


	private void opFinalNum(VdianItemListGetResponse.ListItem listItem) {
		if(!TextUtils.isEmpty(et_price.getText().toString())){
			switch (price_op){
				case "+":
					listItem.setFinalprice((Double.parseDouble(listItem.getPrice())+Double.parseDouble(et_price.getText().toString()))+"");
					break;
				case "-":
					listItem.setFinalprice((Double.parseDouble(listItem.getPrice())-Double.parseDouble(et_price.getText().toString()))+"");
					break;
				case "*":
					listItem.setFinalprice((Double.parseDouble(listItem.getPrice())*Double.parseDouble(et_price.getText().toString()))+"");
					break;
				case "/":
					listItem.setFinalprice((Double.parseDouble(listItem.getPrice())/Double.parseDouble(et_price.getText().toString()))+"");
					break;
			}
		}
		if(!TextUtils.isEmpty(et_stock.getText().toString())) {
			switch (stock_op) {
				case "+":
					listItem.setFinalstock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "-":
					listItem.setFinalstock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "*":
					listItem.setFinalstock(listItem.getStock() + Integer.parseInt(et_stock.getText().toString()));
					break;
				case "/":
					listItem.setFinalstock(listItem.getStock() / Integer.parseInt(et_stock.getText().toString()));
					break;
			}
		}
	}
	private boolean isEmpty() {
		if(TextUtils.isEmpty(et_price.getText().toString())&&TextUtils.isEmpty(et_stock.getText().toString())){
			return true;
		}else {
			return false;
		}
	}
}
