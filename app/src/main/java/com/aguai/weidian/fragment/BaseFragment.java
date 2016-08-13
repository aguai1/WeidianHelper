package com.aguai.weidian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aguai.weidian.utils.ToastUtils;
import com.google.gson.JsonSyntaxException;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.io.IOException;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends RxFragment implements IBaseFragment{
    private String TAG = "fragment";
    private CompositeSubscription mCompositeSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        ButterKnife.bind(this, view);
        initView();
        doBusiness();
        return view;
    }
    //处理异常报错
    protected void handleThrowable(Throwable t) {
        Log.e(TAG, "handle throwable " + t.getMessage());
        if (t instanceof IOException ||
                t instanceof JsonSyntaxException) {
            ToastUtils.showShort("网络连接异常");
        }
        t.printStackTrace();
    }

    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

}
