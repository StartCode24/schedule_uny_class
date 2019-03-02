package com.starcode.skedi.model.FeedSearchScheduleAll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedDataSchedule;

public class FeedAuthSearchScheduleAll {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataSearchScheduleAll data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthSearchScheduleAll(String status, FeedDataSearchScheduleAll data, String message) {
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

    public FeedDataSearchScheduleAll getData() {
        return data;
    }

    public void setData(FeedDataSearchScheduleAll data) {
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
        return "FeedAuthSearchScheduleAll{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
