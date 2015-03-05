package com.weibo.biz.tongji.ui;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.data.StatItem;
import com.weibo.biz.tongji.data.StatItemFactory;
import com.weibo.biz.tongji.util.Brightness;
import com.weibo.biz.tongji.util.helper;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Powered by suchasplus
 */
public class FenListFragment extends ListFragment {

    private final static String TAG = "FenListFragment";

    private boolean isNetWorkValid = false;

    public static ArrayList<StatItem> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.pending_update);

        Log.i(TAG, "StartFenListFragment");

//        Brightness.getInstance().stopAutoBrightness();
//        Log.e(TAG, "B: "+ Brightness.getInstance().getScreenBrightness() );
//        Brightness.getInstance().setBrightness(getActivity(), Brightness.getInstance().getScreenBrightness() * 100);
//        Log.e(TAG, "B: "+ Brightness.getInstance().getScreenBrightness() );
//        Log.e(TAG, "B_status: "+ Brightness.getInstance().isAutoBrightness());

        //mItems = StatItemFactory.get(getActivity()).getStatItems();

        MainListItemFetcher fetcher = new MainListItemFetcher();
        fetcher.execute();

    }

    private void HandleData(ArrayList<StatItem> items) {
        mItems = items;
        isNetWorkValid = true;

        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        StatItemAdapter adapter = new StatItemAdapter(FenListFragment.mItems);
                        setListAdapter(adapter);
                        getActivity().setTitle("微博广告统计");
                    }
                }
        );

    }

    private class MainListItemFetcher extends AsyncTask<Void, Void, ArrayList<StatItem> > {

        @Override
        protected ArrayList<StatItem> doInBackground(Void... params) {

            ArrayList<StatItem> items = StatItemFactory.get(getActivity()).getStatItems();
            Log.i(TAG, "MainListItemFetcher ArrayList<StatItem> size: " +items.size());
            HandleData(items);
            return items;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        if(!isNetWorkValid) {
            //Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_SHORT).show();
            return;
        }
        //Crime c = (Crime) (getListAdapter()).getItem(position);

        StatItem si = ((StatItemAdapter) getListAdapter()).getItem(pos);
        if(!si.isClickable()) {
            return;
        }

        Intent i = new Intent(getActivity(), DemoChartActivity.class);
        //Intent i = new Intent(getActivity(), TestChartActivity.class);
        //i.putExtra(TestChartActivity.EXTRA_CHART_CAT, si.getAbbreviation());
        i.putExtra(DemoChartActivity.EXTRA_CHART_CAT, si.getAbbreviation());
        i.putExtra(DemoChartActivity.EXTRA_CHART_TYPENAME, si.getTitle());
        startActivity(i);

        //String prompt = si.getTitle() + " was clicked";

        //Toast.makeText(getActivity(), prompt, Toast.LENGTH_SHORT).show();

//        Intent i = new Intent(getActivity(), CrimeActivity.class);
//        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
//        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatItemAdapter sia = (StatItemAdapter)getListAdapter();
        if(sia != null ) {
            sia.notifyDataSetChanged();
        }
    }


    private class StatItemAdapter extends ArrayAdapter<StatItem> {

        public StatItemAdapter(ArrayList<StatItem> items) {
            super(getActivity(), 0 , items);
        }

        public View getView(int pos, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_statitem, null);
                //Log.e(TAG, "Current ConvertView is NULL");
            }

            StatItem si = getItem(pos);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.stat_list_item_titleTextView);
            TextView consumeTextView = (TextView) convertView.findViewById(R.id.stat_list_item_consumeTextview);
            TextView lastupdatetimeText = (TextView) convertView.findViewById(R.id.stat_list_item_updatetimeTextView);

            titleTextView.setText(si.getTitle());
            consumeTextView.setText(String.format("%1$.2f", si.getTodayConsume()) + "元");
            lastupdatetimeText.setText("UpdateTime: "+ new SimpleDateFormat("MM/dd HH:mm:ss").format(si.getLastUpdateTime()));
            //Toast.makeText(getActivity(), titleTextView.getText(), Toast.LENGTH_SHORT).show();


            return convertView;
        }
    }

}
