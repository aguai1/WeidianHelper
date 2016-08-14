package com.aguai.weidian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.aguai.weidian.adpter.CardPlayerAdapter;
import com.aguai.weidian.utils.GdtConstants;
import com.aguai.weidian.utils.ToastUtils;
import com.aguai.weidian.views.PlayerView;
import com.example.testzjut.R;
import com.qq.e.ads.appwall.APPWall;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class UserMain extends BaseActivity implements NativeAD.NativeAdListener{
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.gdt_main)RelativeLayout gdt_main;
    @Bind(R.id.player_view)PlayerView playerView;
    private NativeAD nativeAD;

    @Override
    public int bindLayout() {
        return R.layout.activity_usermain;
    }

    @Override
    public void initView(View view) {
        initActionBar();
    }

    @Override
    public void doBusiness(Context mContext) {
        loadAD();
    }

    @Override
    public void initPrame(Bundle bundle) {

    }
    public void loadAD() {
        if (nativeAD == null) {
            this.nativeAD = new NativeAD(this, GdtConstants.APPID, GdtConstants.TuiGuangPosID, this);
        }
        int count = 25; // 一次拉取的广告条数：范围1-30
        nativeAD.loadAD(count);
    }
    private void initActionBar() {
        if (toolbar != null) {
            toolbar.setTitle("推广");
            toolbar.setNavigationIcon(R.mipmap.ic_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   finish();
                }
            });
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        loadAD();
        if ( playerView != null
                && !playerView.isPlaying()) {
            playerView.startPlayer();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if (playerView != null) {
            playerView.stopPlayer();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (playerView != null) {
            playerView.destroyPlayer();
        }
    }

    @Override
    public void onADLoaded(List<NativeADDataRef> list) {
        if(playerView!=null){
            playerView.setPlayerAdapter(new CardPlayerAdapter(getBaseContext(), list, new CardPlayerAdapter.OnClickListener() {
                @Override
                public void onClick(View view, NativeADDataRef nativeADDataRef) {
                    nativeADDataRef.onClicked(view); // 点击接口
                }
            }));
            playerView.notifyPlayerDataChange(true);
            playerView.startPlayer();
        }

    }
    @Override
    public void onNoAD(int i) {

    }

    @Override
    public void onADStatusChanged(NativeADDataRef nativeADDataRef) {

    }

    @Override
    public void onADError(NativeADDataRef nativeADDataRef, int i) {

    }
}
