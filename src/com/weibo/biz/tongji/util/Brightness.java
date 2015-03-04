package com.weibo.biz.tongji.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;

/**
 * Powered by suchasplus @15/3/5 01:17
 */
public class Brightness {

    private Context mContext;
    private static Brightness sInstance;

    private Brightness(final Context context) {
        mContext = context;
    }

    public static Brightness init(final Context context) {
        if (null == sInstance) {
            sInstance = new Brightness(context);
        }
        return sInstance;
    }

    public static Brightness getInstance() {
        return sInstance;
    }

    // 判断是否是自动调光模式
    public boolean isAutoBrightness() {
        boolean automicBrightness = false;
        try {
            ContentResolver resolver = mContext.getContentResolver();
            automicBrightness = Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return automicBrightness;
    }

    // 设置屏幕亮度
    public void setBrightness(Activity activity, int brightness) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = brightness * (1f / 255f);
        activity.getWindow().setAttributes(lp);
    }

    // 保存屏幕亮度
    public void saveBrightness(int brightness) {
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = android.provider.Settings.System
                .getUriFor("screen_brightness");
        android.provider.Settings.System.putInt(resolver, "screen_brightness",
                brightness);
        resolver.notifyChange(uri, null);
    }

    // 开启自动调光模式
    public void startAutoBrightness() {
        ContentResolver resolver = mContext.getContentResolver();
        Settings.System.putInt(resolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
        Uri uri = android.provider.Settings.System
                .getUriFor("screen_brightness");
        resolver.notifyChange(uri, null);
    }

    // 关闭自动调光模式
    public void stopAutoBrightness() {
        ContentResolver resolver = mContext.getContentResolver();
        Settings.System.putInt(resolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Uri uri = android.provider.Settings.System
                .getUriFor("screen_brightness");
        resolver.notifyChange(uri, null);
    }

    // 获得当前屏幕亮度
    public int getScreenBrightness() {
        int nowBrightnessValue = 0;
        try {
            ContentResolver resolver = mContext.getContentResolver();
            nowBrightnessValue = android.provider.Settings.System.getInt(
                    resolver, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }

    // 设置光亮模式
    public void setBrightnessMode(int mode) {
        Settings.System.putInt(mContext.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, mode);
    }

    // 获得亮度模式
    public int getBrightnessMode() {
        try {
            return Settings.System.getInt(mContext.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            return Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        }
    }

}
