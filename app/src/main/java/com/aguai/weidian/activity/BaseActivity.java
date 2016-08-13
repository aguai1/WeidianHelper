package com.aguai.weidian.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.aguai.weidian.db.SharedPreferencesHelper;
import com.aguai.weidian.umdata.AnalysisService;
import com.aguai.weidian.utils.MarketUtils;
import com.aguai.weidian.utils.SharedPreferencesUtils;
import com.aguai.weidian.utils.ToastUtils;
import com.aguai.weidian.views.dialog.AlertDialog;
import com.aguai.weidian.views.dialog.LoadingDialog;
import com.example.testzjut.R;
import com.google.gson.JsonSyntaxException;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 *Activity基类
 * */
public abstract class BaseActivity extends RxAppCompatActivity implements
		IBaseActivity {

	protected AlertDialog alertDialog;
	protected LoadingDialog loadingDialog;
	private CompositeSubscription mCompositeSubscription;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置渲染视图View
		View mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
		setContentView(mContextView);
		loadingDialog=new LoadingDialog(this);
		alertDialog=new AlertDialog(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ButterKnife.bind(this);
		initPrame(getIntent().getExtras());
		// 初始化控件
		initView(mContextView);

		Context mContext = this.getBaseContext();
		doBusiness(mContext);
	}
	protected void addSubscription(Subscription s) {
		if (this.mCompositeSubscription == null) {
			this.mCompositeSubscription = new CompositeSubscription();
		}

		this.mCompositeSubscription.add(s);
	}
	//处理异常报错
	protected void handleThrowable(Throwable t) {
		if (t instanceof IOException ||
				t instanceof JsonSyntaxException) {
			ToastUtils.showShort("网络连接异常");
		}
		t.printStackTrace();
	}
	/**
	 * 隐藏软键盘 hideSoftInputView
	 */
	public void hideSoftInputView() {
		InputMethodManager manager = ((InputMethodManager) this
				.getSystemService(Activity.INPUT_METHOD_SERVICE));
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				manager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public void onBack(View v) {
		finish();
	}

	public void onResume() {
		super.onResume();
		AnalysisService.onResume(this);
	}
	public void onPause() {
		super.onPause();
		AnalysisService.onPause(this);
	}


	public void showCommentActivity() {
		if(!SharedPreferencesHelper.getInstance().getIsComment(getBaseContext())){
			ArrayList<String> strings = MarketUtils.queryInstalledMarketPkgs(this);
			ArrayList<String> strings1 = MarketUtils.filterInstalledPkgs(getBaseContext(), strings);
			if(strings1.size()>0) {
				MarketUtils.launchAppDetail(this, this.getPackageName(), strings1.get(0));
				SharedPreferencesHelper.getInstance().setIsComment(getBaseContext(),true);
			}
		}
	}

	@Override
	protected void onDestroy() {
		System.gc();
		if (this.mCompositeSubscription != null) {
			this.mCompositeSubscription.unsubscribe();
		}
		ButterKnife.unbind(this);
		super.onDestroy();
	}

	/**
	 * 计算坐标
	 *
	 * @param src
	 * @param target
	 * @return
	 */
	public Point getLocationInView(View src, View target) {
		final int[] l0 = new int[2];
		src.getLocationOnScreen(l0);

		final int[] l1 = new int[2];
		target.getLocationOnScreen(l1);

		l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
		l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

		return new Point(l1[0], l1[1]);
	}
	public int getActionBarSize() {
		TypedValue typedValue = new TypedValue();
		int[] textSizeAttr = new int[]{R.attr.actionBarSize};
		int indexOfAttrTextSize = 0;
		TypedArray a = getBaseContext().obtainStyledAttributes(typedValue.data, textSizeAttr);
		int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
		a.recycle();
		return actionBarSize;
	}
}
