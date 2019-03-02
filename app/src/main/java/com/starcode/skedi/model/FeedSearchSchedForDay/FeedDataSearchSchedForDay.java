package com.starcode.skedi.model.FeedSearchSchedForDay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedSchedule;

import java.util.List;

public class FeedDataSearchSchedForDay {
    @SerializedName("schedule")
    @Expose
    private List<FeedSearchSchedForDay> schedule;

    public FeedDataSearchSchedForDay(List<FeedSearchSchedForDay> schedule) {
        this.schedule = schedule;
    }

    public List<FeedSearchSchedForDay> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<FeedSearchSchedForDay> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "FeedDataSearchSchedForDay{" +
                "schedule=" + schedule +
                '}';
    }
}
