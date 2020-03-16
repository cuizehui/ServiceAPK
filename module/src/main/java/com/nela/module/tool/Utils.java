package com.nela.module.tool;

import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.nela.module.BuildConfig;
import com.nela.module.NApplication;

public class Utils {

    public final static int SIM_TYPE_COMMON = 0;
    public final static int SIM_TYPE_XIAOMI = 1;

    public static int SIM_TYPE = SIM_TYPE_COMMON;
    public final static String RETRY_TIMES = "retry_times";
    public final static String LOGIN_TIMES = "login_times";
    public final static String LOGIN_FORBIDDEN_TIMES = "login_forbidden_times";
    public final static String LOGIN_TIMES_ONE_HOURE = "login_times_one_hour";
    public final static String MAX_RETRY_TIMES = "max_retry_times";
    public final static String MAX_LOGIN_TIMES = "max_login_times";
    // 一天中不允许尝试的登录错误 cp 和 login 都包含
    public final static String MAX_LOGIN_FORBIDDEN_TIMES = "max_login_forbidden_times";
    public final static String MAX_LOGIN_TIMES_ONE_HOUR = "max_login_times_one_hour";




    public static int getRetryTimesToday() {
        long nowDay = System.currentTimeMillis() / (24 * 3600 * 1000);
        String times = PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getString(RETRY_TIMES, "");
        if (!TextUtils.isEmpty(times)) {
            String[] values = times.split("-");
            if (values.length == 2) {
                if (TextUtils.equals(String.valueOf(nowDay), values[0])) {
                    return Integer.valueOf(values[1]);
                }
            }
        }
        return 0;
    }

    public static void setRetryTimesToday(int times) {
        PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).edit().putString(RETRY_TIMES, String.format("%d-%d", System.currentTimeMillis() / (24 * 3600 * 1000), times)).commit();
    }

    public static int getLoginTimesOneHour() {
        long nowHour = System.currentTimeMillis() / (3600 * 1000);
        String times = PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getString(LOGIN_TIMES_ONE_HOURE, "");
        if (!TextUtils.isEmpty(times)) {
            String[] values = times.split("-");
            if (values.length == 2) {
                if (TextUtils.equals(String.valueOf(nowHour), values[0])) {
                    return Integer.valueOf(values[1]);
                }
            }
        }
        return 0;
    }

    public static int getLoginForbiddenTimesToday() {
        long nowDay = System.currentTimeMillis() / (24 * 3600 * 1000);
        String times = PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getString(LOGIN_FORBIDDEN_TIMES, "");
        if (!TextUtils.isEmpty(times)) {
            String[] values = times.split("-");
            if (values.length == 2) {
                if (TextUtils.equals(String.valueOf(nowDay), values[0])) {
                    return Integer.valueOf(values[1]);
                }
            }
        }
        return 0;
    }

    public static void setLoginForbiddenTimesToday(int times) {
        PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).edit().putString(LOGIN_FORBIDDEN_TIMES, String.format("%d-%d", System.currentTimeMillis() / (24 * 3600 * 1000), times)).commit();
    }

    public static void setLoginTimesOneHour(int times) {
        PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).edit().putString(LOGIN_TIMES_ONE_HOURE, String.format("%d-%d", System.currentTimeMillis() / (3600 * 1000), times)).commit();
    }

    public static int getMaxRetryOneToday() {
        return PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getInt(MAX_RETRY_TIMES, 4);
    }

    public static void setMaxRetryTimesToday(int times) {
        PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).edit().putInt(MAX_RETRY_TIMES, times).commit();
    }

    public static int getMaxLoginOneHour() {
        return PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getInt(MAX_LOGIN_TIMES_ONE_HOUR, 20);
    }

    public static void setMaxLoginTimesHour(int times) {
        PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).edit().putInt(MAX_LOGIN_TIMES_ONE_HOUR, times).commit();
    }

    public static int getMaxLoginForbiddenOneday() {
        return PreferenceManager.getDefaultSharedPreferences(NApplication.sContext).getInt(MAX_LOGIN_FORBIDDEN_TIMES, 3);
    }

    public static boolean IscooperatorA() {
        return TextUtils.equals(BuildConfig.FLAVOR_apptype, "cooperatorA");
    }
}
