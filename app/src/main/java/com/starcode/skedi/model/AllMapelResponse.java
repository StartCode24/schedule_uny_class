package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedAllKelas.FeedAuthKelas;
import com.starcode.skedi.model.FeedAllMapel.FeedAuthMapel;

public class AllMapelResponse {
    @SerializedName("auth_Mapel")
    @Expose
    private FeedAuthMapel auth_Mapel;

    public AllMapelResponse(FeedAuthMapel auth_Mapel) {
        this.auth_Mapel = auth_Mapel;
    }

    public FeedAuthMapel getAuth_Mapel() {
        return auth_Mapel;
    }

    public void setAuth_Mapel(FeedAuthMapel auth_Mapel) {
        this.auth_Mapel = auth_Mapel;
    }

    @Override
    public String toString() {
        return "AllMapelResponse{" +
                "auth_Mapel=" + auth_Mapel +
                '}';
    }
}
