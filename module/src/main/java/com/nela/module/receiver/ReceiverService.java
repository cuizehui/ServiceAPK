package com.nela.module.receiver;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.nela.common.tools.JsonParamConstants;

/**
 * 内部处理上报广播
 * 或者交给APk层处理广播
 */

public class ReceiverService extends IntentService {

    private static final String TAG = "ReceiverService";

    public ReceiverService() {
        super("ReceiverService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            dealRcsBroadcast(intent);
        }
    }

    private void dealRcsBroadcast(Intent intent) {
        String action = intent.getAction();
        String json = intent.getStringExtra(JsonParamConstants.RCS_JSON_KEY);
        if (json == null) {
            return;
        }
        if (TextUtils.equals(action, JsonParamConstants.RCS_ACTION_IM)) {
            dealFirstBroadCast(json);
        }
    }

    private void dealFirstBroadCast(String json) {
        sendSecondNotify(json);
    }

    private void sendSecondNotify(String json) {
        {
            Intent intent = new Intent(JsonParamConstants.RCS_ACTION_CLI_NOTIFY);
            intent.putExtra(JsonParamConstants.RCS_JSON_KEY, json);
            //apk层Receiver
            intent.setComponent(new ComponentName("com.android.messaging", "com.juphoon.helper.mms.RcsWakeupReceiver"));
            sendBroadcast(intent, JsonParamConstants.RCS_NOTIFY_PERMISSION);
        }
        //  notifyCallback(JsonParamConstants.RCS_ACTION_CLI_NOTIFY, json);
    }
}
