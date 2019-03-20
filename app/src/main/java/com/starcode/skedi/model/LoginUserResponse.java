package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedLogin.FeedauthLogin;

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
