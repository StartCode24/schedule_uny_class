package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedAuthSchedule;
import com.starcode.skedi.model.FeedSearchSchedForDay.FeedAuthSearchSchedForDay;

public class SearchSchedForDayResponse {
    @SerializedName("auth_SearchSchedForDay")
    @Expose
    private FeedAuthSearchSchedForDay auth_SearchSchedForDay;

    public SearchSchedForDayResponse(FeedAuthSearchSchedForDay auth_SearchSchedForDay) {
        this.auth_SearchSchedForDay = auth_SearchSchedForDay;
    }

    public FeedAuthSearchSchedForDay getAuth_SearchSchedForDay() {
        return auth_SearchSchedForDay;
    }

    public void setAuth_SearchSchedForDay(FeedAuthSearchSchedForDay auth_SearchSchedForDay) {
        this.auth_SearchSchedForDay = auth_SearchSchedForDay;
    }

    @Override
    public String toString() {
        return "SearchSchedForDayResponse{" +
                "auth_SearchSchedForDay=" + auth_SearchSchedForDay +
                '}';
    }
}
