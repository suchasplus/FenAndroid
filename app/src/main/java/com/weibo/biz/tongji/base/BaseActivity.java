package com.weibo.biz.tongji.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.util.Connectivity;

/**
 * Powered by suchasplus @15/3/5 00:15
 */
public abstract class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        FenApplication.setIsAppRunning(true);
        FenApplication.getApp().setActivityName(this.getClass().getName());

        checkConn();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
    protected void closeActivity() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return resolveCreateOptionMenu(menu);
    }

    abstract protected boolean resolveCreateOptionMenu(Menu menu);
    abstract protected boolean resolveOptionsItemSelected(MenuItem item);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return resolveOptionsItemSelected(item);

    }

    protected void checkConn() {
        if( Connectivity.isConnected(this) && Connectivity.isConnectedWifi(this) ) {
            return;
        }

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.network_type_error)
                .setMessage(R.string.please_confirm_network_is_internal_wifi)
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
