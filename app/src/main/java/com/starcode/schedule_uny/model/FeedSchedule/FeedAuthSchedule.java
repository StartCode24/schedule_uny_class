package com.starcode.schedule_uny.model.FeedSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedAuthSchedule {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataSchedule data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FeedDataSchedule getData() {
        return data;
    }

    public void setData(FeedDataSchedule data) {
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
        return "FeedAuthSchedule{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
