package com.starcode.schedule_uny.model.FeedSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedDataSchedule {
    @SerializedName("schedule")
    @Expose
    private FeedSchedule schedule;

    public FeedSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(FeedSchedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "FeedDataSchedule{" +
                "schedule=" + schedule +
                '}';
    }
}
