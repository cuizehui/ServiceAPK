package com.nela.module.receiver;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.nela.common.constants.Define;
import com.nela.common.constants.JsonParamConstants;
import com.nela.module.NApplication;

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
        String json = intent.getStringExtra(JsonParamConstants.JSON_KEY);
        if (json == null) {
            return;
        }
        if (TextUtils.equals(action, JsonParamConstants.ACTION_A)) {
            dealFirstBroadCast(json);
        }
    }

    private void dealFirstBroadCast(String json) {
        Log.d(TAG, "dealFirstBroadCast " + json);
        dealProviderOperate();
        sendSecondNotify(json);
    }

    //进行数据库操作
    private void dealProviderOperate() {
        //todo 处理provider
        ContentValues values = new ContentValues();
        values.put(Define.TABLE_A.BODY, "net workChange");
        values.put(Define.TABLE_A.BEGIN_TIME, System.currentTimeMillis());
        Uri retUri = NApplication.sContext.getContentResolver().insert(Define.TABLE_A.CONTENT_URI_TABLE, values);
    }

    private void sendSecondNotify(String json) {
        Log.d(TAG, "sendSecondNotify " + json);
        {
            Intent intent = new Intent(JsonParamConstants.ACTION_APK_NOTIFY);
            intent.putExtra(JsonParamConstants.JSON_KEY, json);
            //发送广播到apk层Receiver
            intent.setComponent(new ComponentName("com.nela.businessapk", "com.juphoon.helper.mms.RcsWakeupReceiver"));
            //sendBroadcast(intent, JsonParamConstants.RCS_NOTIFY_PERMISSION);
            sendBroadcast(intent);
        }
    }
}
