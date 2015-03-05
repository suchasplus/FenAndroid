package com.weibo.biz.tongji.data;

import android.content.Context;

/**
 * Powered by suchasplus @15/3/5 00:07
 */
public class StatDetailForChartFactory {

    private static StatDetailForChartFactory sFactory;
    private Context mAppContext;

    private StatDetailForChartFactory(Context context) {
        mAppContext = context;
    }

    public static StatDetailForChartFactory get(Context appContext) {
        if(sFactory == null) {
            sFactory = new StatDetailForChartFactory(appContext);
        }

        return sFactory;
    }

    public void getChartData() {

    }

}
