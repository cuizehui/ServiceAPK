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

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private final static String TAG = "ServiceManager";

    public final static String ALIVE = "alive";

    private static String rcsPackageName = "com.nela.module.service";
    private final static String aliveClassName = "com.nela.module.service.AliveService";

    private static Object sInterfaceLock = new Object();

    private static IAliveService sIAliveService;


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

//    private static boolean isNeedToReconnect() {
////        return getIRcsService() == null || getIRcsImService() == null;
//    }

    public static void connectService() {
        Log.d(TAG, "connectService");
        synchronized (sInterfaceLock) {

            if (sIAliveService == null) {
                Intent intent = new Intent(ServiceConstants.ACTION_JUPHOON_SERVICE_ALIVE);
                ComponentName component = new ComponentName(rcsPackageName, aliveClassName);
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
                //service 处理逻辑较多采用广播上报方式
//                if (c.equals(className)) {
//                    name = RCS;
//                    sIRcsService = IRcsService.Stub.asInterface(service);
//                  //  notifyLoginStateChange();
//                    try {
//                        sIRcsService.registerCallback(new IRcsCallback.Stub() {
//
//                            @Override
//                            public void onJsonCallback(final String action, final String json) throws RemoteException {
//                                new Handler(sContext.getMainLooper()).post(new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        if (TextUtils.equals(RcsJsonParamConstants.RCS_ACTION_CLI_NOTIFY, action)) {
//                                            try {
//                                                JSONObject jsonObj = new JSONObject(json);
//                                                String jsonAction = jsonObj.optString(RcsJsonParamConstants.RCS_JSON_ACTION);
//                                                if (jsonAction.equals(RcsJsonParamConstants.RCS_JSON_ACTION_CLI_SERV_LOGIN_OK)) {
//                                                    notifyLoginStateChange();
//                                                } else if (jsonAction.equals(RcsJsonParamConstants.RCS_JSON_ACTION_CLI_SERV_LOGOUT)
//                                                        || jsonAction.equals(RcsJsonParamConstants.RCS_JSON_ACTION_CLI_SERV_LOGIN_FAILED)) {
//                                                    notifyLoginStateChange();
//                                                } else if (jsonAction.equals(RcsJsonParamConstants.RCS_JSON_ACTION_CLI_CMCSS_TOKEN)) {
//                                                    notifyCmccTokenGet(jsonObj.optString(RcsJsonParamConstants.RCS_JSON_COOKIE),
//                                                            jsonObj.optBoolean(RcsJsonParamConstants.RCS_JSON_RESULT),
//                                                            jsonObj.optString(RcsJsonParamConstants.RCS_JSON_TOKEN));
//                                                } else if (jsonAction.equals(RcsJsonParamConstants.RCS_JSON_ACTION_CLI_USE_RCS)) {
//                                                    notifyUseRcsChange();
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    }
//                                });
//                            }
//                        });
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                }  else
                if (c.equals(aliveClassName)) {
                    sIAliveService = IAliveService.Stub.asInterface(service);
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

//    /**
//     * 针对 aar 的形式
//     *
//     * @param context
//     * @param servicePackageName
//     */
//    public static void init(Context context, String servicePackageName) {
//        Log.d(TAG, "init");
//        if (sContext == null) {
//            sContext = context.getApplicationContext();
//            rcsPackageName = servicePackageName;
//            connectService();
//        }
//    }

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
