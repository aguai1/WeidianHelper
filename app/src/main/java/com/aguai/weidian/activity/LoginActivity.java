package com.aguai.weidian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.aguai.weidian.MyApplication;
import com.aguai.weidian.db.SharedPreferencesHelper;
import com.aguai.weidian.lifelogic.repositories.TokenRepo;
import com.aguai.weidian.lifelogic.repositories.entities.TokenInfo;
import com.aguai.weidian.utils.ToastUtils;
import com.example.testzjut.R;
import com.trello.rxlifecycle.ActivityEvent;
import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 *登录界面
 * */
public  class LoginActivity extends BaseActivity{
    @Bind(R.id.username)EditText username;
    @Bind(R.id.userpasswd)EditText userpass;
    private  TokenRepo tokenRepo;
    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        username.setText( SharedPreferencesHelper.getInstance().getAppKey(getBaseContext()));
        userpass.setText( SharedPreferencesHelper.getInstance().getSecret(getBaseContext()));
        loadingDialog.setMsg("正在登陆");
    }

    @Override
    public void doBusiness(Context mContext) {
        tokenRepo=new TokenRepo();
    }

    @Override
    public void initPrame(Bundle bundle) {

    }
    @OnClick({R.id.btn_login,R.id.tv_help})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                loadingDialog.show();
                getToken();
                break;
            case R.id.tv_help:
                startActivity(new Intent(LoginActivity.this,HelpActivity.class));
        }
    }
    private void getToken() {
        final String name = username.getText().toString();
        final String pass = userpass.getText().toString();
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pass)) {
            loadingDialog.dismiss();
            ToastUtils.showLong("请填写appkey或者secret，如不知道该数据请看帮助，谢谢！");
            return;
        }

        Subscription s =  tokenRepo.getToken(name,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<TokenInfo>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Action1<TokenInfo>() {
                               @Override
                               public void call(TokenInfo tokenInfo) {
                                   loadingDialog.dismiss();
                                    if(tokenInfo.getStatus().getStatus_code()==0){
                                        SharedPreferencesHelper.getInstance().setAppKey(getBaseContext(),name);
                                        SharedPreferencesHelper.getInstance().setSecret(getBaseContext(),pass);
                                        MyApplication.getInstance().setAccessToken(tokenInfo.getResult().getAccess_token());
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();
                                    }else{
                                        ToastUtils.showShort(tokenInfo.getStatus().getStatus_reason());
                                    }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                loadingDialog.dismiss();
                                handleThrowable(throwable);
                            }
                        });
    }
}
