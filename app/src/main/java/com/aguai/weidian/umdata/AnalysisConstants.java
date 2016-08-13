package com.aguai.weidian.umdata;


public class AnalysisConstants {
    /**
     * 点击职场分类
     */
    public static final String CLASS_PROFESSION = "click_tid_1";
    /**
     * 点击校园分类
     */
    public static final String CLASS_SCHOOL = "click_tid_2";
    /**
     * 点击生活分类
     */
    public static final String CLASS_LIFE = "click_tid_3";
    /**
     * 点击家庭分类
     */
    public static final String CLASS_FAMILY = "click_tid_4";
    /**
     * 点击情感分类
     */
    public static final String CLASS_EMOTION = "click_tid_5";
    /**
     * 点击经历分类
     */
    public static final String CLASS_EXPERIENCE = "click_tid_6";
    /**
     * 点击体育分类
     */
    public static final String CLASS_SPORT = "click_tid_7";
    /**
     * 点击健身分类
     */
    public static final String CLASS_BODY_BUILDING = "click_tid_8";
    /**
     * 点击资讯分类
     */
    public static final String CLASS_INFORMATION = "click_tid_9";
    /**
     * 点击创业分类
     */
    public static final String CLASS_BUSINESS = "click_tid_10";
    /**
     * 点击世界分类
     */
    public static final String CLASS_WORLD = "click_tid_11";
    /**
     * 点击公益分类
     */
    public static final String CLASS_COMMONWEAL = "click_tid_12";
    /**
     * 点击文字分类
     */
    public static final String CLASS_WORD = "click_tid_13";
    /**
     * 点击影视娱乐分类
     */
    public static final String CLASS_ENTERTAINMENT = "click_tid_14";
    /**
     * 点击音乐分类
     */
    public static final String CLASS_MUSIC = "click_tid_15";
    /**
     * 点击美食分类
     */
    public static final String CLASS_FOOD = "click_tid_16";
    /**
     * 点击购物分类
     */
    public static final String CLASS_SHOPPING = "click_tid_17";
    /**
     * 点击旅行分类
     */
    public static final String CLASS_TRAVEL = "click_tid_18";
    /**
     * 点击游戏分类
     */
    public static final String CLASS_GAME = "click_tid_19";
    /**
     * 点击星座分类
     */
    public static final String CLASS_CONSTELLATION = "click_tid_20";
    /**
     * 点击时尚分类
     */
    public static final String CLASS_FASHION = "click_tid_21";
    /**
     * 点击玩乐分类
     */
    public static final String CLASS_PLAY = "click_tid_22";
    /**
     * 点击文化艺术分类
     */
    public static final String CLASS_ART = "click_tid_23";
    /**
     * 点击段子分类
     */
    public static final String CLASS_JOKE = "click_tid_24";
    /**
     * 点击动漫分类
     */
    public static final String CLASS_COMIC = "click_tid_25";
    /**
     * 点击理财分类
     */
    public static final String CLASS_FINANCING = "click_tid_26";
    /**
     * 点击宠物分类
     */
    public static final String CLASS_PET = "click_tid_27";
    /**
     * 点击知识分类
     */
    public static final String CLASS_KNOWLEDGE = "click_tid_28";
    /**
     * 点击观点分类
     */
    public static final String CLASS_OPINION = "click_tid_29";


    /**
     * 数据统计页选择关闭
     */
    public static final String DATA_PAGE_CLOSE = "data_click_close";
    /**
     * 数据统计页选择浏览
     */
    public static final String DATA_PAGE_VIEW = "data_click_read";


    /**
     * 内容详情页点击言集名
     */
    public static final String DETAIL_PAGE_CLICK_ALBUM_NAME = "click_content_albumname";

    /**
     * 点击轮播图
     */
    public static final String BANNER_CLICKED = "click_banner";

    /**
     * 第三方登录失败
     */
    public static final String THIRD_PARTY_FAIL = "third_party_fail";

    /**
     * 言集队列从扇形切换到列表型
     */
    public static final String ALBUM_FAN_TO_LIST = "album_fan_to_list";

    /**
     * 点击精选内容
     */
    public static final String CLICK_DAILY_SELECTION = "click_daily_content";

    /**
     * 网络连接失败
     */
    public static final String CONNECTION_FAILED = "connect_fail";

    /**
     * 内容页点关注
     */
    public static final String SUBSCRIBE_ALBUM_IN_CARD = "content_view_subscribe";
    /**
     * 言集页点关注
     */
    public static final String SUBSCRIBE_ALBUM_IN_ALBUM = "album_view_subscribe";
    /**
     * 发表评论
     */
    public static final String COMMENT = "send_comment";

    /**
     * 创建内容
     */
    public static final String CREATE_CARD = "publish_content";

    /**
     * 点击言论内容
     */
    public static final String TOPIC_YANLUN = "click_tsid_3_content";
    /**
     * 点击说文内容
     */
    public static final String TOPIC_SHUOWEN = "click_tsid_1_content";
    /**
     * 点击食味内容
     */
    public static final String TOPIC_SHIWEI = "click_tsid_4_content";


    public static String getClassEventIdByTid(int tid) {
        String res = null;
        switch (tid) {
            case 1:
                res = CLASS_PROFESSION;
                break;
            case 2:
                res = CLASS_SCHOOL;
                break;
            case 3:
                res = CLASS_LIFE;
                break;
            case 4:
                res = CLASS_FAMILY;
                break;
            case 5:
                res = CLASS_EMOTION;
                break;
            case 6:
                res = CLASS_EXPERIENCE;
                break;
            case 7:
                res = CLASS_SPORT;
                break;
            case 8:
                res = CLASS_BODY_BUILDING;
                break;
            case 9:
                res = CLASS_INFORMATION;
                break;
            case 10:
                res = CLASS_BUSINESS;
                break;
            case 11:
                res = CLASS_WORLD;
                break;
            case 12:
                res = CLASS_COMMONWEAL;
                break;
            case 13:
                res = CLASS_WORD;
                break;
            case 14:
                res = CLASS_ENTERTAINMENT;
                break;
            case 15:
                res = CLASS_MUSIC;
                break;
            case 16:
                res = CLASS_FOOD;
                break;
            case 17:
                res = CLASS_SHOPPING;
                break;
            case 18:
                res = CLASS_TRAVEL;
                break;
            case 19:
                res = CLASS_GAME;
                break;
            case 20:
                res = CLASS_CONSTELLATION;
                break;
            case 21:
                res = CLASS_FASHION;
                break;
            case 22:
                res = CLASS_PLAY;
                break;
            case 23:
                res = CLASS_ART;
                break;
            case 24:
                res = CLASS_JOKE;
                break;
            case 25:
                res = CLASS_COMIC;
                break;
            case 26:
                res = CLASS_FINANCING;
                break;
            case 27:
                res = CLASS_PET;
                break;
            case 28:
                res = CLASS_KNOWLEDGE;
                break;
            case 29:
                res = CLASS_OPINION;
                break;
        }
        return res;
    }

    public static String getTopicEventId(int tsid) {
        String str = null;
        switch (tsid) {
            case 10:
                str = TOPIC_SHUOWEN;
                break;
            case 6:
                str = TOPIC_YANLUN;
                break;
            case 11:
                str = TOPIC_SHIWEI;
                break;
        }
        return str;
    }
}
