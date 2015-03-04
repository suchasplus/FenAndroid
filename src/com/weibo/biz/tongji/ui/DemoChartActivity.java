package com.weibo.biz.tongji.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.ChartBase;


import java.util.ArrayList;

/**
 * Powered by suchasplus @15/2/28 16:12
 */
public class DemoChartActivity extends ChartBase implements OnChartValueSelectedListener {

    public static final String EXTRA_CHART_TYPENAME = "com.weibo.biz.tongji.ui.DemoChartActivity.EXTRA_CHART_TYPENAME";
    public static final String EXTRA_CHART_CAT = "com.weibo.biz.tongji.ui.DemoChartActivity.EXTRA_CHART_CAT";
    private Typeface mTypeFace;

    private LineChart mChart;
    private LineChart mChart2;
    private LineChart mChart3;

    private int[] mColors = new int[] {
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.single_chart);

        //this.setTitle("统计图表");

        mChart = (LineChart) findViewById(R.id.singleChart);
        if(mChart == null) {
            Log.e(EXTRA_CHART_CAT, "Chart is NULL! why!!");
            return;
        }

        mTypeFace = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");
        mChart.setOnChartValueSelectedListener(this);
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        mChart.setDrawGridBackground(true);
        mChart.setDescription("广告统计");
        mChart.setDescriptionTypeface(mTypeFace);
        mChart.setHighlightEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(false);  // if disabled , scaling can be done on x- and y-axis separately
        //Legend legend = mChart.getLegend();
        //if(legend != null) {
            //legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        //}

        mChart2 = (LineChart) findViewById(R.id.singleChart2);

        mChart2.setOnChartValueSelectedListener(this);
        mChart2.setNoDataTextDescription("You need to provide data for the chart.");
        mChart2.setDrawGridBackground(true);
        mChart2.setDescription("广告统计");
        mChart2.setDescriptionTypeface(mTypeFace);
        mChart2.setHighlightEnabled(true);
        mChart2.setTouchEnabled(true);
        mChart2.setDragEnabled(true);
        mChart2.setScaleEnabled(true);
        mChart2.setPinchZoom(false);

        mChart3 = (LineChart) findViewById(R.id.singleChart3);

        mChart3.setOnChartValueSelectedListener(this);
        mChart3.setNoDataTextDescription("You need to provide data for the chart.");
        mChart3.setDrawGridBackground(true);
        mChart3.setDescription("广告统计");
        mChart3.setDescriptionTypeface(mTypeFace);
        mChart3.setHighlightEnabled(true);
        mChart3.setTouchEnabled(true);
        mChart3.setDragEnabled(true);
        mChart3.setScaleEnabled(true);
        mChart3.setPinchZoom(false);

        handleData();
    }

    public void handleData() {
        ArrayList<String> xVals = new ArrayList<String>();
        for ( int i = 0 ; i < 20; i ++ ) {
            xVals.add(i+"");
        }

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        for( int z = 0 ; z < 3 ; z++) {
            ArrayList<Entry> values = new ArrayList<Entry>();
            for ( int i = 0; i < 20; i ++ ) {
                double val = (Math.random() * 10) + 3;
                values.add(new Entry((float) val , i));
            }
            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));

            d.setLineWidth(2.5f);
            d.setCircleSize(4f);

            int color = mColors[ z % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);

        }

        dataSets.get(0).enableDashedLine(10,10,0);
        dataSets.get(0).setColors(ColorTemplate.COLORFUL_COLORS);
        dataSets.get(0).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(xVals, dataSets);
        mChart.setData(data);
        mChart.invalidate();

        ArrayList<LineDataSet> dataSets2 = new ArrayList<LineDataSet>();
        for( int z = 0 ; z < 3 ; z++) {
            ArrayList<Entry> values = new ArrayList<Entry>();
            for ( int i = 0; i < 20; i ++ ) {
                double val = (Math.random() * 10) + 3;
                values.add(new Entry((float) val , i));
            }
            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));

            d.setLineWidth(1.5f);
            d.setCircleSize(3f);

            int color = mColors[ z % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets2.add(d);

        }


        dataSets.get(0).setCircleColors(ColorTemplate.LIBERTY_COLORS);
        LineData data2 = new LineData(xVals, dataSets2);

        mChart2.setData(data2);
        mChart2.invalidate();

        ArrayList<LineDataSet> dataSets3 = new ArrayList<LineDataSet>();
        for( int z = 0 ; z < 3 ; z++) {
            ArrayList<Entry> values = new ArrayList<Entry>();
            for ( int i = 0; i < 20; i ++ ) {
                double val = (Math.random() * 10) + 3;
                values.add(new Entry((float) val , i));
            }
            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));

            d.setLineWidth(1f);
            d.setCircleSize(2f);

            int color = mColors[ z % mColors.length];
            d.setColor(color);
            d.setCircleColor(color);
            dataSets3.add(d);

        }


        dataSets.get(0).setCircleColors(ColorTemplate.JOYFUL_COLORS);
        LineData data3 = new LineData(xVals, dataSets3);

        mChart3.setData(data3);
        mChart3.invalidate();


    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex) {

    }

    @Override
    public void onNothingSelected() {

    }


}
