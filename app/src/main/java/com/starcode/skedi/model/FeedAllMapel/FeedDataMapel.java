package com.starcode.skedi.model.FeedAllMapel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllJurusan.FeedAllJurusan;

import java.util.List;

public class FeedDataMapel {
    @SerializedName("Mapel")
    @Expose
    private List<FeedAllMapel> Mapel;

    public FeedDataMapel(List<FeedAllMapel> mapel) {
        Mapel = mapel;
    }

    public List<FeedAllMapel> getMapel() {
        return Mapel;
    }

    public void setMapel(List<FeedAllMapel> mapel) {
        Mapel = mapel;
    }

    @Override
    public String toString() {
        return "FeedDataMapel{" +
                "Mapel=" + Mapel +
                '}';
    }
}
