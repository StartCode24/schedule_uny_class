package com.starcode.schedule_uny.model.FeedAllJurusan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.FeedSchedule.FeedSchedule;

import java.util.List;

public class FeedDataJurusan {

    @SerializedName("jurusan")
    @Expose
    private List<FeedAllJurusan> jurusan;

    public FeedDataJurusan(List<FeedAllJurusan> jurusan) {
        this.jurusan = jurusan;
    }

    public List<FeedAllJurusan> getJurusan() {
        return jurusan;
    }

    public void setJurusan(List<FeedAllJurusan> jurusan) {
        this.jurusan = jurusan;
    }

    @Override
    public String toString() {
        return "FeedDataJurusan{" +
                "jurusan=" + jurusan +
                '}';
    }
}
