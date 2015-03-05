package com.weibo.biz.tongji.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weibo.biz.tongji.R;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;


/**
 * Powered by suchasplus @15/2/24 23:07
 */
public class TestActivity extends Activity {
    public static String TAG = "TestActivity";

    private List<Ttype> tTypes;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.test);

        MainListItemFetcher fetcher = new MainListItemFetcher();
        fetcher.execute();

//        String json_raw = "[{\"type\":\"feed\",\"lastupdate\":\"1\",\"consume\":\"9999.99\"},{\"type\":\"aim\",\"lastupdate\":\"2\",\"consume\":\"888.88\"},{\"type\":\"pa\",\"lastupdate\":\"3\",\"consume\":\"66.66\"}]";
//        try {
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
//            Gson gson = gsonBuilder.create();
//            List<Ttype> tTypes;
//            tTypes = Arrays.asList(gson.fromJson(json_raw, Ttype[].class));
//            for(int i = 0; i < tTypes.size(); i++) {
//                Log.e(TAG, tTypes.get(i).toString());
//            }
//            Toast.makeText(this, tTypes.get(0).toString(), Toast.LENGTH_SHORT).show();
//        } catch(Exception e) {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//        }

    }
    public void handleShow(List<Ttype> tTypes) {
        this.tTypes = tTypes;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(Ttype  t : TestActivity.this.tTypes) {
                    Toast.makeText(TestActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    TestActivity.this.setTitle(t.type);
                }
            }
        });
    }

    private class MainListItemFetcher extends AsyncTask<Void, Void, String> {
        private static final String TAG = "MainListItemFetcher";
        public static final String SERVER_URL = "http://suchasplus.com/i.php";

        @Override
        protected String doInBackground(Void... params) {

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(SERVER_URL);

                HttpResponse response = client.execute(httpget);
                StatusLine statusLine = response.getStatusLine();

                if(statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    try {
                        Reader reader = new InputStreamReader(content);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                        Gson gson = gsonBuilder.create();
                        List<Ttype> tTypes;
                        tTypes = Arrays.asList(gson.fromJson(reader, Ttype[].class));
                        content.close();
                        for(int i = 0; i < tTypes.size(); i++) {
                            Log.e(TAG, tTypes.get(i).toString());
                        }
                        handleShow(tTypes);

                    }catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);
                    }
                } else {

                }
            } catch (Exception e) {

            }
            return null;
        }
    }

    public class Ttype {

        public String type;
        public String lastupdate;
        public String consume;

        @Override
        public String toString() {
            return "[type] " + type + " [LastUpdate] " + lastupdate + " [consume] " + consume;
        }
    }
}
