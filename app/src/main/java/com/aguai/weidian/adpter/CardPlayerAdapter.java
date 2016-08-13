package com.aguai.weidian.adpter;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aguai.weidian.views.PlayerView;
import com.bumptech.glide.Glide;
import com.qq.e.ads.nativ.NativeADDataRef;

import java.util.List;

public class CardPlayerAdapter extends PlayerView.PlayerAdapter {

    private List<NativeADDataRef> mCards;

    private OnClickListener mImageClickListener;

    private Context mContext;

    public CardPlayerAdapter(Context context, List<NativeADDataRef> list) {
        mContext = context;
        mCards = list;
    }

    public CardPlayerAdapter(Context context, List<NativeADDataRef> list, OnClickListener listener) {
        mContext = context;
        mCards = list;
        mImageClickListener = listener;
    }

    private boolean isAClick(float startX,float startY,float endX,float endY){
        float threshold = 30;
        float differenceX = Math.abs(endX-startX);
        float differenceY = Math.abs(endY-startY);
        if(differenceX>threshold||differenceY>threshold)
            return false;
        return true;
    }


    @Override
    public void onBindPlayerView(RelativeLayout container, ImageView image, TextView title, TextView recommend_content, int position) {
        final NativeADDataRef item = mCards.get(position);
        recommend_content.setText(item.getDesc());
        title.setText(item.getTitle());
        Glide.with(mContext).load(item.getImgUrl()).into(image);
        item.onExposured(container); // 需要先调用曝光接口
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mImageClickListener!=null){
                    mImageClickListener.onClick(v,item);
                }
            }
        });
    }

    @Override
    public int getPlayerViewCount() {
        return mCards.size();
    }



   public  interface OnClickListener{
        void onClick(View view,NativeADDataRef nativeADDataRef);
   }
}
