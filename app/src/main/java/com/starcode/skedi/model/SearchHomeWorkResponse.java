package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSearchHomeWork.AuthSearchHomeWork;
import com.starcode.skedi.model.FeedSearchSched.FeedAuthSearchSched;

public class SearchHomeWorkResponse {
    @SerializedName("auth_SearchHomeWork")
    @Expose
    private AuthSearchHomeWork auth_SearchHomeWork;

    public SearchHomeWorkResponse(AuthSearchHomeWork auth_SearchHomeWork) {
        this.auth_SearchHomeWork = auth_SearchHomeWork;
    }

    public AuthSearchHomeWork getAuth_SearchHomeWork() {
        return auth_SearchHomeWork;
    }

    public void setAuth_SearchHomeWork(AuthSearchHomeWork auth_SearchHomeWork) {
        this.auth_SearchHomeWork = auth_SearchHomeWork;
    }

    @Override
    public String toString() {
        return "SearchHomeWorkResponse{" +
                "auth_SearchHomeWork=" + auth_SearchHomeWork +
                '}';
    }
}
