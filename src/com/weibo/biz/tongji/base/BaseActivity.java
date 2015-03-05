package com.weibo.biz.tongji.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;

/**
 * Powered by suchasplus @15/3/5 00:15
 */
public class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        FenApplication.setIsAppRunning(true);
        FenApplication.getApp().setActivityName(this.getClass().getName());
    }

    //Just fix the bug:java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        try {
            return super.onKeyDown(keyCode, event);
        } catch (IllegalStateException e) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event){
        try {
            return super.onKeyUp(keyCode, event);
        } catch (IllegalStateException e) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
        }
        return true;
    }
    protected void closeActivity() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {

            case R.id.report:
                String mailto = FenApplication.getBasicProperties("report_mailto");
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", mailto, null));
                i.putExtra(Intent.EXTRA_SUBJECT, "粉统计Android Issue");
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
