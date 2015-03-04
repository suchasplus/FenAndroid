package com.weibo.biz.tongji.util;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.Arrays;

/**
 * Powered by suchasplus @15/2/28 21:51
 */
public class helper {

    private static String TAG="util.helper";

    private static String SYS_ID = null;

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getEquipmentWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getEquipmentHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    public static String getUniqDeviceId(Context context) {
        String id = getUniqueID(context);
        if (id == null)
            id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }



    public static String getStringIntegerHexBlocks(int value) {
        String result = "";
        String string = Integer.toHexString(value);

        int remain = 8 - string.length();
        char[] chars = new char[remain];
        Arrays.fill(chars, '0');
        string = new String(chars) + string;

        int count = 0;
        for (int i = string.length() - 1; i >= 0; i--) {
            count++;
            result = string.substring(i, i + 1) + result;
            if (count == 4) {
                result = "-" + result;
                count = 0;
            }
        }

        if (result.startsWith("-")) {
            result = result.substring(1, result.length());
        }

        return result;
    }

    private static String getUniqueID(Context context) {

        if ( SYS_ID != null ) {
            return SYS_ID;
        }

        String telephonyDeviceId;
        String androidDeviceId;

        String error = "0000-0000-0000-0000";

        // get telephony id
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            telephonyDeviceId = tm.getDeviceId();
            if (telephonyDeviceId == null) {
                Log.e(TAG, "Get TelephonyManager DeviceId(IMEI) Error");
                return error;
            }
            Log.e(TAG, "TelephonyManager getDeviceId: " + telephonyDeviceId);
        } catch (Exception e) {
            Log.e(TAG, "Get TelephonyManager DeviceId(IMEI) Error" + e.getMessage());
            return error;
        }

        // get internal android device id
        try {
            androidDeviceId = android.provider.Settings.Secure.getString(context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
            if (androidDeviceId == null) {
                Log.e(TAG, "Get androidDeviceId Error");
                return error;
            }
            Log.e(TAG, "android.provider.Settings.Secure.ANDROID_ID : " + androidDeviceId );
        } catch (Exception e) {
            Log.e(TAG, "Get androidDeviceId Error"+e.getMessage());
            return error;
        }

        // build up the uuid
        try {
            String id = getStringIntegerHexBlocks(androidDeviceId.hashCode())
                    + "-"
                    + getStringIntegerHexBlocks(telephonyDeviceId.hashCode());

            SYS_ID = id;
            Log.e(TAG, SYS_ID);
            return SYS_ID;
        } catch (Exception e) {
            return error;
        }
    }

}
