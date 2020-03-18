package com.nela.common.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.nela.common.constants.ServiceConstants;
import com.nela.module.service.IAliveService;
import com.nela.module.service.INelaService;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private final static String TAG = "ServiceManager";

    public final static String ALIVE = "alive";
    public final static String Nela = "nela";
    //应用包名
    private static String packageName = "com.nela.apkservice";
    private final static String aliveClassName = "com.nela.module.service.AliveService";
    private final static String nelaClassName = "com.nela.module.service.NelaService";
    private static Object sInterfaceLock = new Object();

    private static IAliveService sIAliveService;
    private static INelaService sINelaService;
    private static Context sContext;

    public static class IServiceManagerCallback {

        public void onConnectedChange(boolean connected, String name) {

        }

        //others
        public void onOthersChange() {

        }
    }

    private static List<IServiceManagerCallback> sListIServiceManagerCallbacks = new ArrayList<IServiceManagerCallback>();

    public static Object getIntefaceLockObj() {
        return sInterfaceLock;
    }

    public static IAliveService getAliveService() {
        synchronized (sInterfaceLock) {
            return sIAliveService;
        }
    }

    public static INelaService getNelaService() {
        synchronized (sInterfaceLock) {
            return sINelaService;
        }
    }


    public static void addCallBack(final IServiceManagerCallback callback) {
        new Handler(sContext.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {
                if (!sListIServiceManagerCallbacks.contains(callback)) {
                    Log.d(TAG, "addCallBack");
                    sListIServiceManagerCallbacks.add(callback);

                    if (isServiceConnected(ALIVE)) {
                        callback.onConnectedChange(true, ALIVE);
                    }
                }
            }
        });
    }

    public static void removeCallBack(final IServiceManagerCallback callback) {
        new Handler(sContext.getMainLooper()).post(new Runnable() {

            @Override
            public void run() {
                if (sListIServiceManagerCallbacks.contains(callback)) {
                    sListIServiceManagerCallbacks.remove(callback);
                }
            }
        });
    }

    private static void notifyConnectStateChange(boolean connected, String name) {
        for (IServiceManagerCallback callback : sListIServiceManagerCallbacks) {
            callback.onConnectedChange(connected, name);
        }
    }


    public static void connectService() {
        Log.d(TAG, "connectService");
        synchronized (sInterfaceLock) {
            if (sIAliveService == null) {
                Intent intent = new Intent(ServiceConstants.ACTION_NELA_SERVICE_ALIVE);
                ComponentName component = new ComponentName(packageName, aliveClassName);
                intent.setComponent(component);
                sContext.bindService(intent, sConnection, Context.BIND_AUTO_CREATE);
            }
            if (sINelaService == null) {
                Intent intent = new Intent(ServiceConstants.ACTION_NELA_SERVICE_NELA);
                ComponentName component = new ComponentName(packageName, nelaClassName);
                intent.setComponent(component);
                sContext.bindService(intent, sConnection, Context.BIND_AUTO_CREATE);
            }
        }
    }

    private final static ServiceConnection sConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            String name = "";
            String c = className.getClassName();
            synchronized (sInterfaceLock) {
                if (c.equals(aliveClassName)) {
                    sIAliveService = IAliveService.Stub.asInterface(service);
                } else if (c.equals(nelaClassName)) {
                    sINelaService = INelaService.Stub.asInterface(service);
                }
            }
            Log.d(TAG, c + " onServiceConnected");
            notifyConnectStateChange(true, name);
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            String name = "";
            String c = className.getClassName();
            synchronized (sInterfaceLock) {
                if (c.equals(aliveClassName)) {
                    name = ALIVE;
                    sIAliveService = null;
                } else if (c.equals(nelaClassName)) {
                    name = Nela;
                    sINelaService = null;
                }

            }
            Log.d(TAG, c + " onServiceDisconnected");
            notifyConnectStateChange(false, name);
            connectService();
        }
    };

    public static void init(Context context) {
        Log.d(TAG, "init");
        if (sContext == null) {
            sContext = context.getApplicationContext();
            connectService();
        }
    }

    public static void uninit() {
        if (sContext != null) {
            try {
                sContext.unbindService(sConnection);
            } catch (Exception e) {

            }
            sContext = null;
            sListIServiceManagerCallbacks.clear();
        }
    }

    public static boolean isServiceConnected(String name) {
        synchronized (sInterfaceLock) {
            if (TextUtils.equals(name, ALIVE)) {
                return sIAliveService != null;
            }
        }
        return false;
    }
}
