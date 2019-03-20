package com.starcode.skedi.model.FeedDataProfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedDataProfil.DataProfilResponse;


public class FeedAuthUser {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("data")
    @Expose
    private DataProfilResponse data;

    @SerializedName("message")
    @Expose
    private String message;

    public FeedAuthUser(String status, DataProfilResponse data, String message) {
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

    public DataProfilResponse getData() {
        return data;
    }

    public void setData(DataProfilResponse data) {
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
        return "FeedAuthUser{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
