package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedAuthSchedule;
import com.starcode.skedi.model.FeedSearchScheduleAll.FeedAuthSearchScheduleAll;

public class SearchScheduleAllResponse {
    @SerializedName("auth_SearchScheduleAll")
    @Expose
    private FeedAuthSearchScheduleAll auth_SearchScheduleAll;

    public SearchScheduleAllResponse(FeedAuthSearchScheduleAll auth_SearchScheduleAll) {
        this.auth_SearchScheduleAll = auth_SearchScheduleAll;
    }

    public FeedAuthSearchScheduleAll getAuth_SearchScheduleAll() {
        return auth_SearchScheduleAll;
    }

    public void setAuth_SearchScheduleAll(FeedAuthSearchScheduleAll auth_SearchScheduleAll) {
        this.auth_SearchScheduleAll = auth_SearchScheduleAll;
    }

    @Override
    public String toString() {
        return "SearchScheduleAllResponse{" +
                "auth_SearchScheduleAll=" + auth_SearchScheduleAll +
                '}';
    }
}
