package com.starcode.skedi.model.FeedSearchHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSearchSched.FeedDataSearchSched;

public class AuthSearchHomeWork {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private FeedDataSearchHomeWork data;
    @SerializedName("message")
    @Expose
    private String message;

    public AuthSearchHomeWork(String status, FeedDataSearchHomeWork data, String message) {
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

    public FeedDataSearchHomeWork getData() {
        return data;
    }

    public void setData(FeedDataSearchHomeWork data) {
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
        return "AuthSearchHomeWork{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
