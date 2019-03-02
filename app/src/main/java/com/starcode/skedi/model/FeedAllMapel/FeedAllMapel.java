package com.starcode.skedi.model.FeedAllMapel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedAllMapel {
    @SerializedName("mapelId")
    @Expose
    private String mapelId;

    @SerializedName("mapelName")
    @Expose
    private String mapelName;

    @SerializedName("mapelNote")
    @Expose
    private String mapelNote;

    public FeedAllMapel(String mapelId, String mapelName, String mapelNote) {
        this.mapelId = mapelId;
        this.mapelName = mapelName;
        this.mapelNote = mapelNote;
    }

    public String getMapelId() {
        return mapelId;
    }

    public void setMapelId(String mapelId) {
        this.mapelId = mapelId;
    }

    public String getMapelName() {
        return mapelName;
    }

    public void setMapelName(String mapelName) {
        this.mapelName = mapelName;
    }

    public String getMapelNote() {
        return mapelNote;
    }

    public void setMapelNote(String mapelNote) {
        this.mapelNote = mapelNote;
    }

    @Override
    public String toString() {
        return "FeedAllMapel{" +
                "mapelId='" + mapelId + '\'' +
                ", mapelName='" + mapelName + '\'' +
                ", mapelNote='" + mapelNote + '\'' +
                '}';
    }
}
