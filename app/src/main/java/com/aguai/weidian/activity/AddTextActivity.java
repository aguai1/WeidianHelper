package com.aguai.weidian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.aguai.weidian.MyApplication;
import com.aguai.weidian.adpter.GoodsAdapter;
import com.aguai.weidian.adpter.ProgressAdapter;
import com.aguai.weidian.lifelogic.repositories.GoodsRepo;
import com.aguai.weidian.lifelogic.repositories.net.GoodsNet;
import com.aguai.weidian.utils.ToastUtils;
import com.aguai.weidian.utils.UrlConstants;
import com.example.testzjut.R;
import com.github.glomadrian.loadingballs.BallView;
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
public  class AddTextActivity extends BaseActivity{
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.refreshlay)SwipeRefreshLayout swipeRefreshLayout;
	@Bind(R.id.recycler)RecyclerView recyclerView;
	@Bind(R.id.ballview)BallView ballView;
	@Bind(R.id.et_newtext)EditText et_newtext;
	@Bind(R.id.et_ortext)EditText et_ortext;
	@Bind(R.id.et_finaltext)EditText et_finaltext;


	private GoodsRepo goodsRepo;
	private GoodsNet goodsNet;
	private VdianItemListGetRequest vdianItemListGetRequest;
	private GoodsAdapter addTextAdapter;
	private List<VdianItemListGetResponse.ListItem> list;
	private int currentpage=1;
	private boolean isSucceed=false;
	private boolean isSelectAll=false;
	private int currentOpNum;
	private int allOpNum;

	@Override
	public int bindLayout() {
		return R.layout.activity_addtext;
	}

	@Override
	public void initView(View view) {
		initActionBar();
		list=new ArrayList<>();
		recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
		addTextAdapter=new GoodsAdapter(AddTextActivity.this,list,recyclerView);
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
		goodsNet=new GoodsNet(goodsRepo);
		if(MyApplication.getInstance().getAccessToken()==null){
			Intent intent =  new Intent(AddTextActivity.this, LoginActivity.class);
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
			toolbar.setTitle(getString(R.string.add_imp_text));
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
				getNewInfo();
				break;

		}
	}

	private void opItems() {
		List<VdianItemListGetResponse.ListItem> selectedItems = addTextAdapter.getSelectedItems();
		allOpNum=selectedItems.size();
		currentOpNum=0;
		loadingDialog.setMsg("修改进度0/"+allOpNum);
		loadingDialog.show();
		Observable.from(selectedItems)
				.map(new Func1<VdianItemListGetResponse.ListItem, CommonItemResponse>() {
					@Override
					public CommonItemResponse call(VdianItemListGetResponse.ListItem listItem) {
						listItem.setItemName(listItem.getItemName().replaceAll(et_ortext.getText().toString(),et_finaltext.getText().toString()));
						listItem.setItemName(et_newtext.getText().toString()+listItem.getItemName());
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

	private void getNewInfo() {
		List<VdianItemListGetResponse.ListItem> selectedItems = addTextAdapter.getSelectedItems();
		for (VdianItemListGetResponse.ListItem listItem:selectedItems){
			listItem.setFinalItemName(listItem.getItemName().replaceAll(et_ortext.getText().toString(),et_finaltext.getText().toString()));
			listItem.setFinalItemName(et_newtext.getText().toString()+listItem.getFinalItemName());
		}
		addTextAdapter.notifyDataChanged();
	}

}
