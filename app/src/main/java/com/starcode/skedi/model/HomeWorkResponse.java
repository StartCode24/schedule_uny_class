package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedHomeWork.FeedAuthHomeWork;


public class HomeWorkResponse {
    @SerializedName("auth_HomeWork")
    @Expose
    private FeedAuthHomeWork auth_HomeWork;

    public HomeWorkResponse(FeedAuthHomeWork auth_HomeWork) {
        this.auth_HomeWork = auth_HomeWork;
    }

    public FeedAuthHomeWork getAuth_HomeWork() {
        return auth_HomeWork;
    }

    public void setAuth_HomeWork(FeedAuthHomeWork auth_HomeWork) {
        this.auth_HomeWork = auth_HomeWork;
    }

    @Override
    public String toString() {
        return "HomeWorkResponse{" +
                "auth_HomeWork=" + auth_HomeWork +
                '}';
    }
}

