package com.weibo.biz.tongji.ui;

import android.app.ProgressDialog;
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

import org.json.JSONObject;

public class TongJiChartActivity extends BaseActivity {
    final public static String TAG = TongJiChartActivity.class.getSimpleName();
    final public static String VIEW_CATEGORY = "TYPE";
    protected String mCurrentViewCat = "";
    protected ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
    }

    protected void init() {
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


    private void getJSONByVolley() {
        RequestQueue reqQueue = Volley.newRequestQueue(this);
        String url_raw = FenApplication.getBasicProperties("chart_data_url");
        String url = String.format(url_raw, mCurrentViewCat);
        Log.e(TAG, "url is :" + url);
        progressDialog = ProgressDialog.show(this, "This is title", "...Loading...");
        progressDialog.setCancelable(true);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(  progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e(TAG, "result:" + response.toString());
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "VolleyError: "+error.toString());
                        if(  progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getApplicationContext(), "NetWorkError: " + error.toString(), Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
        );
        int socketTimeout = 5000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        reqQueue.add(jsonObjectRequest);


    }
}
