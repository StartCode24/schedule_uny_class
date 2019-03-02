package com.starcode.skedi.model.FeedAllKelas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllJurusan.FeedAllJurusan;

import java.util.List;

public class FeedDataAllKelas {

    @SerializedName("kelas")
    @Expose
    private List<FeedAllKelas> kelas;

    public FeedDataAllKelas(List<FeedAllKelas> kelas) {
        this.kelas = kelas;
    }

    public List<FeedAllKelas> getKelas() {
        return kelas;
    }

    public void setKelas(List<FeedAllKelas> kelas) {
        this.kelas = kelas;
    }

    @Override
    public String toString() {
        return "FeedDataAllKelas{" +
                "kelas=" + kelas +
                '}';
    }
}
