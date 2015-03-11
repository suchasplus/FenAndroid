package com.weibo.biz.tongji.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;
import com.weibo.biz.tongji.base.BaseFragmentActivity;

/**
 * Powered by suchasplus@gmail.com 15/3/11 23:47
 */
public class SwipeStatActivity extends BaseFragmentActivity {

    public static final String TAG = "SwipeStatActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_stat);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SwipeRefreshListFragment fragment = new SwipeRefreshListFragment();
            transaction.replace(R.id.swipe_stat_list_content_fragment, fragment);
            transaction.commit();
        }
    }
}
