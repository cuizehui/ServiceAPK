package com.nela.module.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.nela.module.app.Utils;

public class BusinessAReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if (TextUtils.equals(action, Utils.ACTION_SCHEDULE_LOGIN)) {

        } else if (TextUtils.equals(action, Utils.ACTION_SIM_STATE_CHANGED)) {

        } else if (TextUtils.equals(action, Utils.ACTION_NET_STATE_CHANGED)) {

        } else if (TextUtils.equals(action, Utils.ACTION_SHUTDOWN)) {

            //上报事件
           // NApplication.dealFirstNotify();
        }
    }
}
