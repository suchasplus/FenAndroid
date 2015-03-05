package com.weibo.biz.tongji.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.weibo.biz.tongji.R;

public abstract class SingleFragmentActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getFragmentManager();

        Fragment f = fm.findFragmentById(R.id.fragmentContainer);

        if( f == null ) {
            f = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragmentContainer, f)
                    .commit();
        }


    }

    protected abstract Fragment createFragment();
}
