package com.nela.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class AliveService extends Service {

    private final IAliveService.Stub mBinder = new IAliveService.Stub() {

        @Override
        public void basicTypes(int i, long l, boolean b, float v, double v1, String s) throws RemoteException {
        }

        @Override
        public void setMessagingUse(boolean b) throws RemoteException {
            Log.d("nela", "setMessagingUse" + b);

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
