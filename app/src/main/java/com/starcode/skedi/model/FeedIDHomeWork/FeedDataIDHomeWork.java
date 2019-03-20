package com.starcode.skedi.model.FeedIDHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllKelas.FeedAllKelas;

import java.util.List;

public class FeedDataIDHomeWork {
    @SerializedName("idHomeWork")
    @Expose
    private String idHomeWork;

    public FeedDataIDHomeWork(String idHomeWork) {
        this.idHomeWork = idHomeWork;
    }

    public String getIdHomeWork() {
        return idHomeWork;
    }

    public void setIdHomeWork(String idHomeWork) {
        this.idHomeWork = idHomeWork;
    }

    @Override
    public String toString() {
        return "FeedDataIDHomeWork{" +
                "idHomeWork='" + idHomeWork + '\'' +
                '}';
    }
}
