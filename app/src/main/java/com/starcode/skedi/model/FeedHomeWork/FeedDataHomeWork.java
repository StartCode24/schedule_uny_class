package com.starcode.schedule_uny.model.FeedHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedDataHomeWork {
    @SerializedName("homework")
    @Expose
    private List<FeedHomeWork> homework;

    public FeedDataHomeWork(List<FeedHomeWork> homework) {
        this.homework = homework;
    }

    public List<FeedHomeWork> getHomework() {
        return homework;
    }

    public void setHomework(List<FeedHomeWork> homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "FeedDataHomeWork{" +
                "homework=" + homework +
                '}';
    }
}

