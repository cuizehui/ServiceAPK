package com.nela.module.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.nela.common.constants.JsonParamConstants;
import com.nela.module.NApplication;
import com.nela.module.app.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class BusinessAReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String action = intent.getAction();
        if (TextUtils.equals(action, Utils.ACTION_SIM_STATE_CHANGED)) {

        } else if (TextUtils.equals(action, Utils.ACTION_NET_STATE_CHANGED)) {
            try {
                Intent intentStateChange = new Intent(JsonParamConstants.ACTION_A);
                JSONObject jsonObj = new JSONObject();
                jsonObj.put(JsonParamConstants.JSON_ACTION, JsonParamConstants.ACTION_A);
                intentStateChange.putExtra(JsonParamConstants.JSON_KEY, jsonObj.toString());
                NApplication.dealFirstNotify(intentStateChange);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (TextUtils.equals(action, Utils.ACTION_SHUTDOWN)) {
        }
    }
}
