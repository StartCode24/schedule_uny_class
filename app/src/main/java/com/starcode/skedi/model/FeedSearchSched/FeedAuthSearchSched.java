package com.starcode.skedi.model.FeedSearchSched;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedAuthSearchSched {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataSearchSched data;
    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthSearchSched(String status, FeedDataSearchSched data, String message) {
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

    public FeedDataSearchSched getData() {
        return data;
    }

    public void setData(FeedDataSearchSched data) {
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
        return "FeedAuthSearchSched{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
