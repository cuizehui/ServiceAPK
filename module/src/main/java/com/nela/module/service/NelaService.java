package com.nela.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class NelaService extends Service {

    private static final NelaServiceBinder sBinder = new NelaServiceBinder();

    public NelaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            Log.d(TAG, "onBind " + intent.toString());
        }
        return sBinder;
    }
}
