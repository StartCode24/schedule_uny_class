package com.starcode.skedi.model.FeedAllKelas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedDataSchedule;

public class FeedAuthKelas {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataAllKelas data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthKelas(String status, FeedDataAllKelas data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FeedDataAllKelas getData() {
        return data;
    }

    public void setData(FeedDataAllKelas data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FeedAuthKelas{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
