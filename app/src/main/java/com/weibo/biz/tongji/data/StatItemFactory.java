package com.weibo.biz.tongji.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.FenApplication;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Powered by suchasplus @15/2/24 16:29
 */
public class StatItemFactory {

    private ArrayList<StatItem> mItems = null;
    private static StatItemFactory sFactory;
    private Context mAppContext;


    private final static String TAG = "StatItemFactory";
    public static final String SERVER_URL = "http://suchasplus.com/reader.php";


    private StatItemFactory(Context appContext) {
        mAppContext = appContext;
        mItems = new ArrayList<StatItem>();


//        for(int i = 0; i< 4; i++) {
//            StatItem si = new StatItem();
//            si.setTitle("Title " + i);
//            si.setAbbreviation("title" + i);
//            si.setLastUpdateTime(new Date());
//            si.setTodayConsume(0.0);
//            mItems.add(si);
//        }
    }

    public static StatItemFactory get(Context appContext) {
        if(sFactory == null) {
            sFactory = new StatItemFactory(appContext);
        }

        return sFactory;
    }

    public ArrayList<StatItem> getStatItems() {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(FenApplication.getBasicProperties("main_list_url"));

            HttpResponse response = client.execute(httpget);
            StatusLine statusLine = response.getStatusLine();

            if(statusLine.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                try {
                    //Reader reader = new InputStreamReader(content);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
                    Gson gson = gsonBuilder.create();
                    mItems.clear();
                    String raw_content = CharStreams.toString(new InputStreamReader(content, "UTF-8"));
                    content.close();
                    mItems = new ArrayList<StatItem>(Arrays.asList(gson.fromJson(raw_content, StatItem[].class)));
                    //Log.e(TAG, raw_content);
                    if(mItems.size() == 0) {
                        StatItem si = new StatItem();
                        si.setTodayConsume(-1.0);
                        si.setLastUpdateTime(new Date());
                        si.setTitle("服务器返回为空!");
                        si.setClickable(false);
                        mItems.add(si);
                    }

                }catch (Exception ex) {
                    Log.e(TAG, "Failed to parse JSON due to: " + ex);

                    //JSON Parse Error, add json_error_item
                    StatItem si = new StatItem();
                    si.setTodayConsume(-1.0);
                    si.setLastUpdateTime(new Date());
                    si.setTitle("JSON数据解析失败");
                    si.setClickable(false);
                    mItems.clear();
                    mItems.add(si);
                }
            } else {

                StatItem si = new StatItem();
                si.setTodayConsume(-1.0);
                si.setLastUpdateTime(new Date());
                si.setTitle("HTTPHeader != 200");
                si.setClickable(false);
                mItems.clear();
                mItems.add(si);
            }
        } catch (Exception e) {
            // network error, add network_error_item
            StatItem si = new StatItem();
            si.setTodayConsume(-1.0);
            si.setLastUpdateTime(new Date());
            si.setTitle("内网连接失败");
            si.setClickable(false);
            mItems.clear();
            mItems.add(si);
        }

        return mItems;
    }


}
