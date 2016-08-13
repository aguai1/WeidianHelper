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

public class MainActivity extends BaseActivity implements NativeAD.NativeAdListener{
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.gdt_main)RelativeLayout gdt_main;
    @Bind(R.id.player_view)PlayerView playerView;
    private NativeAD nativeAD;
    private long mExitTime;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
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
            this.nativeAD = new NativeAD(this, GdtConstants.APPID, GdtConstants.MainActivityBannerPosID, this);
        }
        int count = 25; // 一次拉取的广告条数：范围1-30
        nativeAD.loadAD(count);
    }
    private void initActionBar() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
            toolbar.inflateMenu(R.menu.menu_homepage);
            toolbar.setNavigationIcon(R.mipmap.icon_account);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    APPWall wall = new APPWall(MainActivity.this, GdtConstants.APPID, GdtConstants.APPWallPosID);
                    wall.setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    wall.doShowAppWall();
                }
            });
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent=new Intent();
                    switch (item.getItemId()){
                        case R.id.action_about:
                            intent.setClass(MainActivity.this,AboutActivity.class);
                            break;
                        case R.id.action_exit:
                            finish();
                            return true;
                        case R.id.action_help:
                            intent.setClass(MainActivity.this,HelpActivity.class);
                            break;
                    }
                    startActivity(intent);
                    return false;
                }
            });
        }
    }

    @Override
    public void onADLoaded(List<NativeADDataRef> list) {
        playerView.setPlayerAdapter(new CardPlayerAdapter(getBaseContext(), list, new CardPlayerAdapter.OnClickListener() {
            @Override
            public void onClick(View view, NativeADDataRef nativeADDataRef) {
                nativeADDataRef.onClicked(view); // 点击接口
            }
        }));
        playerView.notifyPlayerDataChange(true);
        playerView.startPlayer();

    }
    @Override
    public void onResume() {
        super.onResume();
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

    @OnClick({R.id.addtext,R.id.modifyinfo,R.id.delete,R.id.discount})
    public void onClick(View view){
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.addtext:
                intent.setClass(MainActivity.this,AddTextActivity.class);
                break;
            case R.id.modifyinfo:
                intent.setClass(MainActivity.this,ModifyInfoActivity.class);
                break;
            case R.id.delete:
                intent.setClass(MainActivity.this,DeleteActivity.class);
                break;
            case R.id.discount:
                intent.setClass(MainActivity.this,DisCountActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort(getString(R.string.zaycexit));
            mExitTime = System.currentTimeMillis();
            return;
        } else {
            finish();
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
    public void onNoAD(int i) {

    }

    @Override
    public void onADStatusChanged(NativeADDataRef nativeADDataRef) {

    }

    @Override
    public void onADError(NativeADDataRef nativeADDataRef, int i) {

    }
}
