package com.weibo.biz.tongji.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;

public class TestSwipeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.test_swipe);
        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        final TextView rndNum = (TextView) findViewById(R.id.rndNum);
        swipeView.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                Log.d("Swipe", "Refreshing Number");
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        double f = Math.random();
                        rndNum.setText(String.valueOf(f));
                    }
                }, 3000);
            }
        });
    }


    @Override
    protected boolean resolveCreateOptionMenu(Menu menu) {
        return false;
    }

    @Override
    protected boolean resolveOptionsItemSelected(MenuItem item) {
        return false;
    }
}
