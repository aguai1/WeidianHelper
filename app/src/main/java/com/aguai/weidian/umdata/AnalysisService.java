package com.aguai.weidian.umdata;


import android.content.Context;

import com.umeng.analytics.MobclickAgent;

public class AnalysisService {
    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    ////统计页面，"pageName"为页面名称，可自定义
    public static void onPageStart(String pageName) {
        MobclickAgent.onPageStart(pageName);
    }

    public static void onPageEnd(String pageName) {
        MobclickAgent.onPageEnd(pageName);
    }

    /**
     * 发送标签分类Card点击事件
     *
     * @param tid 标签id
     */
    public static void sendClassCardClickEvent(Context context, int tid) {
        String eventId = AnalysisConstants.getClassEventIdByTid(tid);
        if (eventId != null) {
            MobclickAgent.onEvent(context, eventId);
        }
    }

    /**
     * 发送第三方登录失败事件
     */

    public static void sendThirdLoginFail(Context context){
        MobclickAgent.onEvent(context,AnalysisConstants.THIRD_PARTY_FAIL);
    }

    /**
     * 发送轮播图点击事件
     */
    public static void sendBannerClickEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.BANNER_CLICKED);
    }

    /**
     * 发送言集名点击事件
     */
    public static void sendAlbumNameClickEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.DETAIL_PAGE_CLICK_ALBUM_NAME);
    }

    /**
     * 发送精选内容点击事件
     */
    public static void sendDailySelectionClickEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.CLICK_DAILY_SELECTION);
    }

    /**
     * 发送网络连接失败事件
     */
    public static void sendConnectionFailedEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.CONNECTION_FAILED);
    }

    /**
     * 发送内容页点击关注言集事件
     */
    public static void sendCardViewSubscribeEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.SUBSCRIBE_ALBUM_IN_CARD);
    }

    /**
     * 发送言集页点击关注言集事件
     */
    public static void sendAlbumViewSubscribeEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.SUBSCRIBE_ALBUM_IN_ALBUM);
    }

    /**
     * 发送评论事件
     */
    public static void sendCommentEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.COMMENT);
    }

    /**
     * 发送创建内容事件
     */
    public static void sendCreateCardEvent(Context context) {
        MobclickAgent.onEvent(context, AnalysisConstants.CREATE_CARD);
    }

    /**
     * 发送主题模块内容点击事件
     *
     * @param tsid 主题模块id
     */
    public static void sendTopicClickEvent(Context context, int tsid) {
        String eventId = AnalysisConstants.getTopicEventId(tsid);
        if (eventId != null) {
            MobclickAgent.onEvent(context, eventId);
        }
    }

    /**
     * 绑定登录用户
     *
     * @param uid 用户id
     */
    public static void login(int uid) {
        MobclickAgent.onProfileSignIn("" + uid);
    }

    /**
     * 解绑登录用户
     */
    public static void logout() {
        MobclickAgent.onProfileSignOff();
    }
}
