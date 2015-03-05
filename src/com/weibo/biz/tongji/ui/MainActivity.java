package com.weibo.biz.tongji.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;
import com.weibo.biz.tongji.base.FenApplication;
import com.weibo.biz.tongji.util.helper;

/**
 * Powered by suchasplus @15/3/5 21:03
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
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

    public void init() {
        setContentView(R.layout.index);
        TextView tvDeviceId = (TextView)findViewById(R.id.main_DeviceIdTextView);
        tvDeviceId.setText("设备ID: " + helper.getUniqDeviceId(this));
    }
}
