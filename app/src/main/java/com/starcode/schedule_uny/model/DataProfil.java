package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProfil {
    @SerializedName("NIK")
    @Expose
    private String NIK;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("kelas")
    @Expose
    private String kelas;

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    @Override
    public String toString() {
        return "DataProfil{" +
                "NIK='" + NIK + '\'' +
                ", nama='" + nama + '\'' +
                ", kelas='" + kelas + '\'' +
                '}';
    }
}
