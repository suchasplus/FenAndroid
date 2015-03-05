package com.weibo.biz.tongji.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;

/**
 * Powered by suchasplus @15/3/5 23:20
 */
public class FentiaoCtrlActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.fentiao_ctrl);

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
