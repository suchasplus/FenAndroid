package com.weibo.biz.tongji.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;
import com.weibo.biz.tongji.base.FenApplication;
import com.weibo.biz.tongji.test.TestSwipeActivity;
import com.weibo.biz.tongji.util.Connectivity;
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

        Button showStat = (Button)findViewById(R.id.main_showStatBtn);
        showStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, StatActivity.class);
                startActivity(i);
            }
        });

        Button fentiaoCtrl = (Button)findViewById(R.id.main_showFentiaoBtn);
        fentiaoCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FentiaoCtrlActivity.class);
                startActivity(i);
            }
        });

        Button testBtn = (Button)findViewById(R.id.main_showTestBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TongJiChartActivity.class);
                i.putExtra(TongJiChartActivity.VIEW_CATEGORY, "feed");
                startActivity(i);
            }
        });

        checkConn();


    }


    protected void checkConn() {
        if( Connectivity.isConnected(this) && Connectivity.isConnectedWifi(this) ) {
            return;
        }

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.app_name)
                .setMessage(R.string.fentiao_ctrl)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog ad = adb.create();
        ad.show();
    }
}
