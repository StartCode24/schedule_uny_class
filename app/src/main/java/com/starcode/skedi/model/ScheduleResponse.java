package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.FeedSchedule.FeedAuthSchedule;

public class ScheduleResponse {
    @SerializedName("auth_Schedule")
    @Expose
    private FeedAuthSchedule auth_Schedule;

    public ScheduleResponse(FeedAuthSchedule auth_Schedule) {
        this.auth_Schedule = auth_Schedule;
    }

    public FeedAuthSchedule getAuth_Schedule() {
        return auth_Schedule;
    }

    public void setAuth_Schedule(FeedAuthSchedule auth_Schedule) {
        this.auth_Schedule = auth_Schedule;
    }

    @Override
    public String toString() {
        return "ScheduleResponse{" +
                "auth_Schedule=" + auth_Schedule +
                '}';
    }
}
