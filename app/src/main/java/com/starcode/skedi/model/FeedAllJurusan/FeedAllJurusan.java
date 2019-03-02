package com.starcode.schedule_uny.model.FeedAllJurusan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedAllJurusan {
    @SerializedName("jurusan_id")
    @Expose
    private String jurusan_id;

    @SerializedName("jurusan_name")
    @Expose
    private String jurusan_name;

    @SerializedName("jurusan_kepala")
    @Expose
    private String jurusan_kepala;

    @SerializedName("jurusan_note")
    @Expose
    private String jurusan_note;

    public FeedAllJurusan(String jurusan_id, String jurusan_name, String jurusan_kepala, String jurusan_note) {
        this.jurusan_id = jurusan_id;
        this.jurusan_name = jurusan_name;
        this.jurusan_kepala = jurusan_kepala;
        this.jurusan_note = jurusan_note;
    }

    public String getJurusan_id() {
        return jurusan_id;
    }

    public void setJurusan_id(String jurusan_id) {
        this.jurusan_id = jurusan_id;
    }

    public String getJurusan_name() {
        return jurusan_name;
    }

    public void setJurusan_name(String jurusan_name) {
        this.jurusan_name = jurusan_name;
    }

    public String getJurusan_kepala() {
        return jurusan_kepala;
    }

    public void setJurusan_kepala(String jurusan_kepala) {
        this.jurusan_kepala = jurusan_kepala;
    }

    public String getJurusan_note() {
        return jurusan_note;
    }

    public void setJurusan_note(String jurusan_note) {
        this.jurusan_note = jurusan_note;
    }

    @Override
    public String toString() {
        return "FeedAllJurusan{" +
                "jurusan_id='" + jurusan_id + '\'' +
                ", jurusan_name='" + jurusan_name + '\'' +
                ", jurusan_kepala='" + jurusan_kepala + '\'' +
                ", jurusan_note='" + jurusan_note + '\'' +
                '}';
    }
}
