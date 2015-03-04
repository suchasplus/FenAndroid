package com.weibo.biz.tongji.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Powered by suchasplus @15/3/4 23:54
 */
public class StatDetail {

    @Expose
    @SerializedName("sum")
    private Double sum;

    @Expose
    @SerializedName("time")
    private Date time;
}
