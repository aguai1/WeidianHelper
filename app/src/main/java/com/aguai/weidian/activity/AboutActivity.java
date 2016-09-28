package com.aguai.weidian.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.aguai.weidian.utils.GdtConstants;
import com.example.testzjut.R;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import butterknife.Bind;

/**
 *关于
 * */
public  class AboutActivity extends BaseActivity{

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    public int bindLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(View view) {
        initActionBar();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void initPrame(Bundle bundle) {

    }
    private void initActionBar() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.about));
            toolbar.setNavigationIcon(R.mipmap.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
