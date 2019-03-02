package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllKelas.FeedAuthKelas;
import com.starcode.skedi.model.FeedSchedule.FeedAuthSchedule;

public class AllKelasResponse {
    @SerializedName("auth_Kelas")
    @Expose
    private FeedAuthKelas auth_Kelas;

    public AllKelasResponse(FeedAuthKelas auth_Kelas) {
        this.auth_Kelas = auth_Kelas;
    }

    public FeedAuthKelas getAuth_Kelas() {
        return auth_Kelas;
    }

    public void setAuth_Kelas(FeedAuthKelas auth_Kelas) {
        this.auth_Kelas = auth_Kelas;
    }

    @Override
    public String toString() {
        return "AllKelasResponse{" +
                "auth_Kelas=" + auth_Kelas +
                '}';
    }
}
