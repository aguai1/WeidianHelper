package com.aguai.weidian.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.testzjut.R;

import java.util.ArrayList;
import java.util.List;


public class PlayerView extends FrameLayout {

    private static final String TAG = PlayerView.class.getSimpleName();

    protected final int FADE_DURATION = 1000;

    protected final int PLAY_DURATION = 5000;

    protected static final int POOL_SIZE = 2;

    /**
     * 轮播View对象池
     */
    protected List<ItemInfo> mItemsInfo = new ArrayList<>(POOL_SIZE);
    protected List<RelativeLayout> mContainers = new ArrayList<>();

    private boolean mIsPlaying = false;
    //当前轮播的位置
    private int mCurrentPlayerIndex = 0;
    //当前轮播图相对于pool的位置
    private int mCurrentPoolIndex = 0;

    private int mOldPoolIndex = 0;

    private Handler mLoopHandler = new Handler(Looper.getMainLooper());

    private PlayerAdapter mPlayerAdapter;

    private ViewPropertyAnimator mCurrentAnimation;

    private AnimatorListenerAdapter mAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            mCurrentPlayerIndex = (mCurrentPlayerIndex + 1) % mPlayerAdapter.getPlayerViewCount();
            mCurrentPoolIndex = (mCurrentPoolIndex + 1) % POOL_SIZE;
            recyclePlayerView(mOldPoolIndex, mCurrentPlayerIndex);
        }
    };


    private Runnable mChangePlayerViewRunnable = new Runnable() {
        @Override
        public void run() {
            onChangeImage();
        }
    };

    public PlayerView(Context context) {
        super(context);
        init(context);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        addImagesToPlayerPool(context);
    }

    /**
     * 初始化轮播池
     */
    public void addImagesToPlayerPool(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < POOL_SIZE; ++i) {
            ItemInfo item = new ItemInfo();
            item.itemView = (RelativeLayout) inflater.inflate(R.layout.player_view_item_view, null);
            item.imageView = (ImageView) item.itemView.findViewById(R.id.gdt_image);
            item.gdt_title = (TextView) item.itemView.findViewById(R.id.gdt_title);
            item.recommend_content = (TextView) item.itemView.findViewById(R.id.gdt_content);
            mContainers.add(item.itemView);
            mItemsInfo.add(item);
            addView(item.itemView, 0);

        }
    }

    /**
     * 开启轮播
     */
    public void startPlayer() {
        Log.i(TAG, "start player");
        mIsPlaying = true;
        sendChangePlayerViewEvent();
    }

    /**
     * 关闭轮播
     */
    public void stopPlayer() {
        Log.i(TAG, "stop player");
        mIsPlaying = false;
        if (mCurrentAnimation != null) {
            mCurrentAnimation.cancel();
        }
        mLoopHandler.removeCallbacks(mChangePlayerViewRunnable);
    }

    /**
     * 重置轮播
     */
    public void resetPlayer() {
        Log.i(TAG, "reset player");
        reset();
    }

    public void destroyPlayer() {
        Log.i(TAG, "destroy player");
        mContainers.clear();
        mLoopHandler.removeCallbacks(mChangePlayerViewRunnable);
        mLoopHandler = null;
    }

    public void notifyPlayerDataChange(boolean shouldReset) {
        Log.i(TAG, "notifyPlayerDataChange shouldReset " + shouldReset);
        boolean shouldRestart = isPlaying();
        stopPlayer();
        if (shouldReset) {
            resetPlayer();
        }
        if (shouldRestart) {
            startPlayer();
        }
    }

    private void reset() {
        mCurrentPlayerIndex = 0;
        mCurrentPoolIndex = 0;
        rebindPlayerView();
    }

    private void rebindPlayerView() {
        if (mPlayerAdapter != null && mPlayerAdapter.getPlayerViewCount() > 0) {
            for (int i = 0; i < POOL_SIZE; ++i) {
                RelativeLayout holder = mContainers.get(i);
                holder.setAlpha(1.0f);
                ItemInfo item = getItemInfoByContainer(holder);
                if (i >= mPlayerAdapter.getPlayerViewCount()) {
                    mPlayerAdapter.onBindPlayerView(item.itemView, item.imageView,item.gdt_title,item.recommend_content ,mPlayerAdapter.getPlayerViewCount() - 1);
                } else {
                    mPlayerAdapter.onBindPlayerView(item.itemView, item.imageView,item.gdt_title,item.recommend_content, i);
                }
            }
        }
    }

    private ItemInfo getItemInfoByContainer(RelativeLayout container) {
        ItemInfo item = null;
        for (int i = 0; i < mItemsInfo.size(); ++i) {
            item = mItemsInfo.get(i);
            if (item.itemView == container) {
                break;
            }
        }
        return item;
    }

    private void sendChangePlayerViewEvent() {
        mLoopHandler.postDelayed(mChangePlayerViewRunnable, PLAY_DURATION);
    }

    /**
     * 轮播是否已经开始
     */
    public boolean isPlaying() {
        return mIsPlaying;
    }


    /**
     * 切换图片
     */
    public void onChangeImage() {
        if (mIsPlaying && mPlayerAdapter != null && mPlayerAdapter.getPlayerViewCount() > 0) {
            mOldPoolIndex = mCurrentPlayerIndex;
            RelativeLayout holder = mContainers.get(mCurrentPlayerIndex % POOL_SIZE);
            mCurrentAnimation = holder.animate();
            mCurrentAnimation.alpha(0)
                    .setDuration(FADE_DURATION)
                    .setInterpolator(new AccelerateInterpolator())
                    .setListener(mAnimatorListener)
                    .start();
            sendChangePlayerViewEvent();
        }
    }

    private void recyclePlayerView(int oldPoolIndex, int curPlayerIndex) {
        RelativeLayout holder = mContainers.get(oldPoolIndex % POOL_SIZE);
        removeView(holder);
        ItemInfo item = getItemInfoByContainer(holder);
        mPlayerAdapter.onBindPlayerView(item.itemView, item.imageView,item.gdt_title,item.recommend_content,calBindIndex(curPlayerIndex));
        addView(holder, 0);
        holder.setAlpha(1.0f);
    }

    private int calBindIndex(int oldIndex) {
        return (oldIndex + 1) % mPlayerAdapter.getPlayerViewCount();
    }

    public void setPlayerAdapter(PlayerAdapter adapter) {
        mPlayerAdapter = adapter;
        boolean shouldRestart = isPlaying();
        stopPlayer();
        reset();
        if (shouldRestart) {
            startPlayer();
        }
    }

    public static abstract class PlayerAdapter {
        /**
         * 绑定轮播View
         *
         * @param image    图像
         * @param position 位置
         */
        public abstract void onBindPlayerView(RelativeLayout container, ImageView image,TextView title,TextView recommend_content, int position);

        /**
         * 轮播View个数
         *
         * @return 轮播一轮个数
         */
        public abstract int getPlayerViewCount();
    }

    public static class ItemInfo {
        public RelativeLayout itemView;
        public ImageView imageView;
        public TextView recommend_content;
        public  TextView gdt_title;
    }

}
