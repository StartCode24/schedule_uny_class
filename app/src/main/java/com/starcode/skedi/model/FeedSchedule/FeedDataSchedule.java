package com.starcode.skedi.model.FeedSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedDataSchedule {
    @SerializedName("schedule")
    @Expose
    private List<FeedSchedule> schedule;

    public FeedDataSchedule(List<FeedSchedule> schedule) {
        this.schedule = schedule;
    }

    public List<FeedSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<FeedSchedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "FeedDataSchedule{" +
                "schedule=" + schedule +
                '}';
    }
}
