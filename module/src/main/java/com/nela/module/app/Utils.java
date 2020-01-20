package com.nela.module.app;

public class Utils {
    // 系统的sim卡切换广播
    public final static String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";
    // 系统网络变化广播
    public final static String ACTION_NET_STATE_CHANGED = "android.net.conn.CONNECTIVITY_CHANGE";
    // 系统收到短信
    public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    // 系统接收数据短信
    public static final String ACTION_DATA_SMS_RECEIVED = "android.intent.action.DATA_SMS_RECEIVED";
    // 关机广播
    public final static String ACTION_SHUTDOWN = "android.intent.action.ACTION_SHUTDOWN";
    // 数据卡切换
    public final static String ACTION_DATA_CARD_CHAGE = "android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED";

}
