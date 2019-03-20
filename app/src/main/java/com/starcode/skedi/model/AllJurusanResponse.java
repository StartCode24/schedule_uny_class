package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllJurusan.FeedAuthJurusan;
import com.starcode.skedi.model.FeedSchedule.FeedAuthSchedule;

public class AllJurusanResponse {

    @SerializedName("auth_Jurusan")
    @Expose
    private FeedAuthJurusan auth_Jurusan;

    public AllJurusanResponse(FeedAuthJurusan auth_Jurusan) {
        this.auth_Jurusan = auth_Jurusan;
    }

    public FeedAuthJurusan getAuth_Jurusan() {
        return auth_Jurusan;
    }

    public void setAuth_Jurusan(FeedAuthJurusan auth_Jurusan) {
        this.auth_Jurusan = auth_Jurusan;
    }

    @Override
    public String toString() {
        return "AllJurusanResponse{" +
                "auth_Jurusan=" + auth_Jurusan +
                '}';
    }
}
