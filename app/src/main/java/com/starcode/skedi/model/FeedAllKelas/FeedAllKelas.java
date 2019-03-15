package com.starcode.skedi.model.FeedAllKelas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedAllKelas {
    @SerializedName("kelas_id")
    @Expose
    private String kelas_id;

    @SerializedName("kelas_name")
    @Expose
    private String kelas_name;

    @SerializedName("kelas_jurusan")
    @Expose
    private String kelas_jurusan;

    @SerializedName("kelas_notasi")
    @Expose
    private String kelas_notasi;

    public FeedAllKelas(String kelas_id, String kelas_name, String kelas_jurusan, String kelas_notasi) {
        this.kelas_id = kelas_id;
        this.kelas_name = kelas_name;
        this.kelas_jurusan = kelas_jurusan;
        this.kelas_notasi = kelas_notasi;
    }

    public String getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(String kelas_id) {
        this.kelas_id = kelas_id;
    }

    public String getKelas_name() {
        return kelas_name;
    }

    public void setKelas_name(String kelas_name) {
        this.kelas_name = kelas_name;
    }

    public String getKelas_jurusan() {
        return kelas_jurusan;
    }

    public void setKelas_jurusan(String kelas_jurusan) {
        this.kelas_jurusan = kelas_jurusan;
    }

    public String getKelas_notasi() {
        return kelas_notasi;
    }

    public void setKelas_notasi(String kelas_notasi) {
        this.kelas_notasi = kelas_notasi;
    }

    @Override
    public String toString() {
        return "FeedAllKelas{" +
                "kelas_id='" + kelas_id + '\'' +
                ", kelas_name='" + kelas_name + '\'' +
                ", kelas_jurusan='" + kelas_jurusan + '\'' +
                ", kelas_notasi='" + kelas_notasi + '\'' +
                '}';
    }
}
