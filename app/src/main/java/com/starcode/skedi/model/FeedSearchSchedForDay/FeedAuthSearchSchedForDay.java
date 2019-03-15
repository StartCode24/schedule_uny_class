package com.starcode.skedi.model.FeedSearchSchedForDay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedDataSchedule;

public class FeedAuthSearchSchedForDay {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataSearchSchedForDay data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthSearchSchedForDay(String status, FeedDataSearchSchedForDay data, String message) {
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

    public FeedDataSearchSchedForDay getData() {
        return data;
    }

    public void setData(FeedDataSearchSchedForDay data) {
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
        return "FeedAuthSearchSchedForDay{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
