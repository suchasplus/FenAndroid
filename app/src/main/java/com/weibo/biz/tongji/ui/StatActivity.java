package com.weibo.biz.tongji.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.FenApplication;
import com.weibo.biz.tongji.base.SingleFragmentActivity;

public class StatActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new FenListFragment();
    }


    @Override
    protected boolean resolveCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected boolean resolveOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {

            case R.id.report:
                String mailto = FenApplication.getBasicProperties("report_mailto");
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", mailto, null));
                i.putExtra(Intent.EXTRA_SUBJECT, "FenApplication Android Issue");
                i.putExtra(Intent.EXTRA_TEXT, "Your error report here...");
                startActivity(Intent.createChooser(i, "Report Problem"));
                break;

            case R.id.website:
                String pc_url = FenApplication.getBasicProperties("pc_stat_url");
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pc_url));
                startActivity(i);
                break;
        }
        return true;
    }
}
