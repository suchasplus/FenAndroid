package com.weibo.biz.tongji.base;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;

/**
 * Powered by suchasplus @15/2/24 22:39
 */
public class ChartBase extends BaseActivity {
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };
    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    protected boolean resolveCreateOptionMenu(Menu menu) {
        return false;
    }

    @Override
    protected boolean resolveOptionsItemSelected(MenuItem item) {
        return false;
    }
}
