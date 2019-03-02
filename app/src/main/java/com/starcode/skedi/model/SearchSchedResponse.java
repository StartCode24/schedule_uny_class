package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedAuthSchedule;
import com.starcode.skedi.model.FeedSearchSched.FeedAuthSearchSched;

public class SearchSchedResponse {
    @SerializedName("auth_SearchSchedule")
    @Expose
    private FeedAuthSearchSched auth_SearchSched;

    public SearchSchedResponse(FeedAuthSearchSched auth_SearchSched) {
        this.auth_SearchSched = auth_SearchSched;
    }

    public FeedAuthSearchSched getAuth_SearchSched() {
        return auth_SearchSched;
    }

    public void setAuth_SearchSched(FeedAuthSearchSched auth_SearchSched) {
        this.auth_SearchSched = auth_SearchSched;
    }

    @Override
    public String toString() {
        return "SearchSchedResponse{" +
                "auth_SearchSched=" + auth_SearchSched +
                '}';
    }
}
