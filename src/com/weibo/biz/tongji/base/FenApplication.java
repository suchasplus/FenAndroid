package com.weibo.biz.tongji.base;

import android.app.Application;
import android.content.Context;

import java.util.Properties;

/**
 * Powered by suchasplus @15/3/1 00:13
 */
public class FenApplication extends Application {
    public static FenApplication mApp;

    private String mCurrentActivityName = null;

    private static boolean IS_APP_RUNNING = false;

    @Override
    public void onCreate() {

        mApp = this;

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
}
