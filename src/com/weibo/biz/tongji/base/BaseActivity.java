package com.weibo.biz.tongji.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

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

}
