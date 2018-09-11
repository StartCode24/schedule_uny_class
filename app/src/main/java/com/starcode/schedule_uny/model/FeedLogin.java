package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedLogin {
    @SerializedName("Data")
    @Expose
    private DataToken data;

    public DataToken getData() {
        return data;
    }

    public void setData(DataToken data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FeedLogin{" +
                "data=" + data +
                '}';
    }
}
