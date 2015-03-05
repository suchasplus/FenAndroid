package com.weibo.biz.tongji.data;

import java.util.Date;
import java.util.UUID;
import com.google.gson.annotations.SerializedName;

/**
 * Powered by suchasplus @15/2/24 16:35
 */
public class StatItem {

    private UUID mId;

    @SerializedName("desc")
    private String mTitle;

    @SerializedName("type")
    private String mAbbreviation;

    @SerializedName("lastupdatetime")
    private Date mLastUpdateTime;

    @SerializedName("consume")
    private Double mTodayConsume;

    private boolean bClickable = true;

    public String toString() {
        return String.format(
                "[Title]%s [Type]%s [LastUpdateTime]%s [Consume]%s [ClickAble] %s",
                mTitle,
                mAbbreviation,
                mLastUpdateTime,
                mTodayConsume,
                bClickable);
    }

    public StatItem() {
        mId = UUID.randomUUID();
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        mLastUpdateTime = lastUpdateTime;
    }

    public void setTodayConsume(Double todayConsume) {
        mTodayConsume = todayConsume;
    }

    public void setAbbreviation(String abbreviation) {
        mAbbreviation = abbreviation;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAbbreviation() {
        return mAbbreviation;
    }

    public Date getLastUpdateTime() {
        return mLastUpdateTime;
    }

    public Double getTodayConsume() {
        return mTodayConsume;
    }

    public void setClickable(boolean clickable) {
        this.bClickable = clickable;
    }

    public boolean isClickable() {
        return bClickable;
    }
}
