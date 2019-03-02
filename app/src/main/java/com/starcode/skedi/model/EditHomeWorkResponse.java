package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedEditHomeWork.AuthEditHomeWork;

public class EditHomeWorkResponse {
    @SerializedName("auth_UpdateHomework")
    @Expose
    private AuthEditHomeWork auth_UpdateHomework;

    public EditHomeWorkResponse(AuthEditHomeWork auth_UpdateHomework) {
        this.auth_UpdateHomework = auth_UpdateHomework;
    }

    public AuthEditHomeWork getAuth_UpdateHomework() {
        return auth_UpdateHomework;
    }

    public void setAuth_UpdateHomework(AuthEditHomeWork auth_UpdateHomework) {
        this.auth_UpdateHomework = auth_UpdateHomework;
    }

    @Override
    public String toString() {
        return "EditHomeWorkResponse{" +
                "auth_UpdateHomework=" + auth_UpdateHomework +
                '}';
    }
}
