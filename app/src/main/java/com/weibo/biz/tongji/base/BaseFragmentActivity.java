package com.weibo.biz.tongji.base;

import android.support.v4.app.FragmentActivity;

import com.weibo.biz.tongji.R;

/**
 * Powered by suchasplus@gmail.com 15/3/12 00:31
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
