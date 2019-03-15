package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAddHomeWork.AuthAddHomeWork;
import com.starcode.skedi.model.FeedDeleteHomeWork.FeedAuthDeleteHomeWork;

public class DeleteHomeWorkResponse {
    @SerializedName("auth_DeleteHomework")
    @Expose
    private FeedAuthDeleteHomeWork auth_DeleteHomework;

    public DeleteHomeWorkResponse(FeedAuthDeleteHomeWork auth_DeleteHomework) {
        this.auth_DeleteHomework = auth_DeleteHomework;
    }

    public FeedAuthDeleteHomeWork getAuth_DeleteHomework() {
        return auth_DeleteHomework;
    }

    public void setAuth_DeleteHomework(FeedAuthDeleteHomeWork auth_DeleteHomework) {
        this.auth_DeleteHomework = auth_DeleteHomework;
    }

    @Override
    public String toString() {
        return "DeleteHomeWorkResponse{" +
                "auth_DeleteHomework=" + auth_DeleteHomework +
                '}';
    }
}
