package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllKelas.FeedAuthKelas;
import com.starcode.skedi.model.FeedIDHomeWork.FeedAuthIDHomeWork;

public class IDHomeWorkResponse {
    @SerializedName("auth_idHomeWork")
    @Expose
    private FeedAuthIDHomeWork auth_idHomeWork;

    public IDHomeWorkResponse(FeedAuthIDHomeWork auth_idHomeWork) {
        this.auth_idHomeWork = auth_idHomeWork;
    }

    public FeedAuthIDHomeWork getAuth_idHomeWork() {
        return auth_idHomeWork;
    }

    public void setAuth_idHomeWork(FeedAuthIDHomeWork auth_idHomeWork) {
        this.auth_idHomeWork = auth_idHomeWork;
    }

    @Override
    public String toString() {
        return "IDHomeWorkResponse{" +
                "auth_idHomeWork=" + auth_idHomeWork +
                '}';
    }
}
