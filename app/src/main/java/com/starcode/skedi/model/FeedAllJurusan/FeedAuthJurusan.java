package com.starcode.schedule_uny.model.FeedAllJurusan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.FeedSchedule.FeedDataSchedule;

public class FeedAuthJurusan {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataJurusan data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthJurusan(String status, FeedDataJurusan data, String message) {
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

    public FeedDataJurusan getData() {
        return data;
    }

    public void setData(FeedDataJurusan data) {
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
        return "FeedAuthJurusan{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
