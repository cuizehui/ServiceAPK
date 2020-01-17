package com.nela.module;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.nela.module.receiver.ReceiverService;

public class JApplication extends Application {

    private final static String TAG = "JApplication";

    public static Context sContext;
    public static JApplication sApplication;
    private volatile boolean mInited = false;

    private final static BroadcastReceiver sRcsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            intent.setClass(context, ReceiverService.class);
            context.startService(intent);
        }
    };

    public static void dealFirstNotify(Intent intent) {
        sRcsReceiver.onReceive(sContext, intent);
    }

    public JApplication() {
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
        //创建日志目录
        return true;
    }


}
