package com.starcode.schedule_uny.model.FeedLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataToken {
    @SerializedName("auth_token")
    @Expose
    private String auth_token;

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }

    @Override
    public String toString() {
        return "DataToken{" +
                "auth_token='" + auth_token + '\'' +
                '}';
    }
}
