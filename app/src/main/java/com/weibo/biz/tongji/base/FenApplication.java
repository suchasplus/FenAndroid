package com.weibo.biz.tongji.base;

import android.app.Application;
import android.util.Log;
import com.weibo.biz.tongji.util.Brightness;
import com.weibo.biz.tongji.util.helper;

import java.util.Properties;

/**
 * Powered by suchasplus @15/3/1 00:13
 */
public class FenApplication extends Application {
    public static FenApplication mApp;

    private long mLaunchTime = -1; //标记启动时间
    private String mCurrentActivityName = null;
    private static boolean IS_APP_RUNNING = false;

    @Override
    public void onCreate() {

        mApp = this;
        Log.e(FenApplication.class.getCanonicalName(), getSysInfo());
        Brightness.init(this);
        String b = Brightness.getInstance().isAutoBrightness() ? "Sys is AutoBrightness" : "Sys !NOT! AutoBrightnewss";
        Log.e(FenApplication.class.getCanonicalName(), b + ", level: " + Brightness.getInstance().getScreenBrightness() + " , mode: " + Brightness.getInstance().getBrightnessMode());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static void setApp(FenApplication application) {
        mApp = application;
    }

    public static FenApplication getApp() {
        return mApp;
    }

    public void setCurrentActivityName( String name ) {
        mCurrentActivityName = name;
    }

    public String getCurrentActivityName() {
        return mCurrentActivityName;
    }

    public static boolean getIsAppRunning(){
        return IS_APP_RUNNING;
    }

    public static void setIsAppRunning(boolean isAppRunning) {
        IS_APP_RUNNING = isAppRunning;
    }

    public static String getBasicProperties(String key) {
        String strVal = null;
        Properties properties = new Properties();
        try {
            properties.load(mApp.getAssets().open("basic.properties"));
            strVal = properties.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strVal;
    }

    public void setActivityName(String activityName) {
    }

    public void markLauchTime() {
        mLaunchTime = System.currentTimeMillis();
    }
    public void delLauchTime() {
        mLaunchTime = -1;
    }
    public void sendLaunchTime() {
        if (mLaunchTime > 0) {
            mLaunchTime = System.currentTimeMillis() - mLaunchTime;
            if (mLaunchTime > 0) {
                //new PvThread("startup_time", String.valueOf(mLaunchTime));
            }
            mLaunchTime = -1;
        }
    }

    public String getSysInfo() {
        int width = helper.getEquipmentWidth(this);
        int height = helper.getEquipmentHeight(this);
        return "screen[width]: " + width + " [height]: " + height;
    }
}
