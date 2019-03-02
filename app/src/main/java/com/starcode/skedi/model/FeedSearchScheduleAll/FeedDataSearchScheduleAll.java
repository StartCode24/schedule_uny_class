package com.starcode.skedi.model.FeedSearchScheduleAll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSchedule.FeedSchedule;

import java.util.List;

public class FeedDataSearchScheduleAll {
    @SerializedName("schedule")
    @Expose
    private List<FeedSearchScheduleAll> schedule;

    public FeedDataSearchScheduleAll(List<FeedSearchScheduleAll> schedule) {
        this.schedule = schedule;
    }

    public List<FeedSearchScheduleAll> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<FeedSearchScheduleAll> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "FeedDataSearchScheduleAll{" +
                "schedule=" + schedule +
                '}';
    }
}
