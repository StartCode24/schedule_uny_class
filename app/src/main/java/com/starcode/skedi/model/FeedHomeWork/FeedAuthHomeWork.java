package com.starcode.skedi.model.FeedHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedDataSchedule;

public class FeedAuthHomeWork {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataHomeWork data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthHomeWork(String status, FeedDataHomeWork data, String message) {
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

    public FeedDataHomeWork getData() {
        return data;
    }

    public void setData(FeedDataHomeWork data) {
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
        return "FeedAuthHomeWork{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
