package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataToken {
    @SerializedName("token")
    @Expose
    private String token;

    public void SetToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "DataToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
