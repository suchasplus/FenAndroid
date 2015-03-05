package com.weibo.biz.tongji.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.SingleFragmentActivity;

public class StatActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new FenListFragment();
    }



}
