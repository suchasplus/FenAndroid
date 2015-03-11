package com.weibo.biz.tongji.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.weibo.biz.tongji.R;
import com.weibo.biz.tongji.base.BaseActivity;
import com.weibo.biz.tongji.base.FenApplication;
import com.weibo.biz.tongji.util.GsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TongJiChartActivity extends BaseActivity {
    final public static String TAG = TongJiChartActivity.class.getSimpleName();
    final public static String VIEW_CATEGORY = "TYPE";
    protected String mCurrentViewCat = "";
    protected ProgressDialog progressDialog;
    protected Map<String, List<String>> serverRet;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    protected void init() {
        setContentView(R.layout.basic_line_chart);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        String type = bundle.getString(VIEW_CATEGORY);
        mCurrentViewCat = (type == null ? "feed" : type);


        getJSONByVolley();
    }




    @Override
    protected boolean resolveCreateOptionMenu(Menu menu) {
        return false;
    }

    @Override
    protected boolean resolveOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed");
        if(progressDialog != null && progressDialog.isShowing()) {
            Log.e(TAG, progressDialog.toString());
            progressDialog.dismiss();
            return;
        }

        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }


    protected void getJSONByVolley() {
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        String url_raw = FenApplication.getBasicProperties("chart_data_url");
        String url = String.format(url_raw, mCurrentViewCat);
        Log.e(TAG, "url is :" + url);
        progressDialog = ProgressDialog.show(this, "Loading...", "数据载入中, 请稍后");
        progressDialog.setCancelable(true);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public void onResponse(JSONObject response) {
                        if(  progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Log.e(TAG, "result:" + response.toString());

                        try {
                            JSONArray x_axis = (JSONArray) response.get("x_axis");
                            Log.e(TAG, "x_axis length: "+x_axis.length());
                            int x_axis_size = x_axis.length();

                            StringBuffer sb = new StringBuffer();
                            for(int i= 0; i < x_axis_size; i++) {
                                sb.append(" ").append(x_axis.getString(i));
                            }
                            Log.e(TAG, sb.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "VolleyError: "+error.toString());
                        if(  progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        AlertDialog.Builder adb = new AlertDialog.Builder(TongJiChartActivity.this);
                        adb.setTitle(R.string.error)
                                .setMessage(R.string.network_return_data_error)
                                .setIcon(R.drawable.alert_dialog_icon)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        finish();
                                    }
                                });
                        AlertDialog ad = adb.create();
                        ad.show();
                        Toast.makeText(getApplicationContext(), "网络错误: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        int socketTimeout = 5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        reqQueue.add(jsonObjectRequest);
    }


}
