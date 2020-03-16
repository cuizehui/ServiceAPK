package com.nela.module.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.Locale;

public class NetUtils {

    public static final int NET_TYPE_2G = 0;
    public static final int NET_TYPE_3G = NET_TYPE_2G + 1;
    public static final int NET_TYPE_4G = NET_TYPE_3G + 1;
    public static final int NET_TYPE_UNKNOWN = NET_TYPE_4G + 1;

    public static int getNetType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null) {
            return activeNetInfo.getType();
        }
        return -1;
    }

    public static boolean isMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static boolean isMobileEnabled(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }

    public static boolean setUseMobile(Context context, boolean mobileState) {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
        }
        if (version >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            TelephonyManager telephonyService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
                if (null != setMobileDataEnabledMethod) {
                    setMobileDataEnabledMethod.invoke(telephonyService, mobileState);
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                Class ownerClass = connectivityManager.getClass();
                Class[] argsClass = new Class[1];
                argsClass[0] = boolean.class;
                Method method = ownerClass.getMethod("setMobileDataEnabled", argsClass);
                method.invoke(connectivityManager, mobileState);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    public static boolean getUseMobile(Context context) {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK_INT);
        } catch (NumberFormatException e) {
        }
        if (version >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            TelephonyManager telephonyService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");
                if (null != getMobileDataEnabledMethod) {
                    return (Boolean)getMobileDataEnabledMethod.invoke(telephonyService);
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                Class ownerClass = connectivityManager.getClass();
                Method method = ownerClass.getMethod("getMobileDataEnabled");
                return (Boolean)method.invoke(connectivityManager);
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    public static boolean setUseWifi(Context context, boolean wifiState) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(wifiState);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean getUseWifi(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            return wifiManager.isWifiEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkNet(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    //get net type
    public static int getNetworkClass(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NET_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NET_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NET_TYPE_4G;
            default:
                return NET_TYPE_UNKNOWN;
        }
    }

    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     *
     * @param context
     * @param imsi
     * @param type cid, umts or unknown
     * @return
     */
    public static String getAccNetInfo(Context context, String imsi, String type) {
        String mcc = "", mnc = "";
        int lac = -1, umts = -1, cid = -1;
        if (!TextUtils.isEmpty(imsi)) {
            mcc = imsi.subSequence(0, 3).toString();
            if (mcc.equals("460")) {
                mcc = "460";
                mnc = imsi.substring(3, 5);
            } else {
                mnc = imsi.substring(3, 6);
            }
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CellLocation cell = tm.getCellLocation();
        if (cell != null && cell instanceof GsmCellLocation) {
            GsmCellLocation gsmCell = (GsmCellLocation) cell;
            lac = gsmCell.getLac();
            umts = gsmCell.getPsc();
            cid = gsmCell.getCid();
        }
        if (TextUtils.equals(type, "cid")) {
            return String.format(Locale.getDefault(), "%s,%s,%d,%d", mcc, mnc, lac, cid);
        } else if (TextUtils.equals(type, "umts")) {
            return String.format(Locale.getDefault(), "%s,%s,%d,%d", mcc, mnc, lac, umts);
        } else {
            return String.format(Locale.getDefault(), "%s,%s,%d,%d", mcc, mnc, -1, -1);
        }
    }
}
