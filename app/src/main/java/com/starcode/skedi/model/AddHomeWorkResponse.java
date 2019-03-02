package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAddHomeWork.AuthAddHomeWork;

public class AddHomeWorkResponse {
    @SerializedName("auth_AddHomework")
    @Expose
    private AuthAddHomeWork auth_AddHomework;

    public AddHomeWorkResponse(AuthAddHomeWork auth_AddHomework) {
        this.auth_AddHomework = auth_AddHomework;
    }

    public AuthAddHomeWork getAuth_AddHomework() {
        return auth_AddHomework;
    }

    public void setAuth_AddHomework(AuthAddHomeWork auth_AddHomework) {
        this.auth_AddHomework = auth_AddHomework;
    }

    @Override
    public String toString() {
        return "AddHomeWorkResponse{" +
                "auth_AddHomework=" + auth_AddHomework +
                '}';
    }
}
