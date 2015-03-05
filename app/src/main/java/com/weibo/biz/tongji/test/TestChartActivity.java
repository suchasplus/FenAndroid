package com.weibo.biz.tongji.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.weibo.biz.tongji.R;

/**
 * Powered by suchasplus @15/2/25 00:36
 */
public class TestChartActivity extends Activity {
    public static String TAG = "TestChartActivity";

    public static String EXTRA_CHART_CAT = "com.weibo.biz.show.chart.id";
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.test_chart);

        Button showChartBtn = (Button)this.findViewById(R.id.show_chart_button);
        showChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestChartActivity.this, ChartDemo1Acivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
