package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.FeedLogin.FeedauthLogin;

public class LoginUserResponse {
    @SerializedName("auth_login")
    @Expose
    private FeedauthLogin auth_login;

    public FeedauthLogin getAuth_login() {
        return auth_login;
    }

    public void setAuth_login(FeedauthLogin auth_login) {
        this.auth_login = auth_login;
    }

    @Override
    public String toString() {
        return "LoginUserResponse{" +
                "auth_login=" + auth_login +
                '}';
    }
}
