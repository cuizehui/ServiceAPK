package com.nela.module;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.nela.module.app.Utils;
import com.nela.module.receiver.BusinessAReceiver;
import com.nela.module.receiver.ReceiverService;
import com.nela.module.tool.LoginManager;

public class NApplication extends Application {

    private final static String TAG = "NApplication";

    public static Context sContext;
    public static NApplication sApplication;
    private volatile boolean mInited = false;

    private final static BroadcastReceiver sReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.setClass(context, ReceiverService.class);
            context.startService(intent);
        }
    };

    public static void dealFirstNotify(Intent intent) {
        sReceiver.onReceive(sContext, intent);
    }

    public NApplication() {
        super();
        sApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        sApplication = this;
        init();
    }

    public boolean isInited() {
        return mInited;
    }

    public boolean init() {
        //加载so库
        System.loadLibrary("nela_jni");
        //创建日志目录
        Log.d(TAG, "init");
        registerReceiver();
        LoginManager.scheduleLogin();
        return true;
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Utils.ACTION_NET_STATE_CHANGED);
        filter.addAction(Utils.ACTION_SIM_STATE_CHANGED);
        filter.addAction(Utils.ACTION_DATA_CARD_CHAGE);
        filter.addAction(Utils.ACTION_SHUTDOWN);
        sContext.registerReceiver(new BusinessAReceiver(), filter);
    }
}
