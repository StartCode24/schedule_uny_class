package com.starcode.schedule_uny.model.FeedDataProfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProfilResponse {
    @SerializedName("NIK")
    @Expose
    private String NIK;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("kelas")
    @Expose
    private String kelas;

    @SerializedName("jurusan")
    @Expose
    private String jurusan;

    @SerializedName("alamat")
    @Expose
    private String alamat;

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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return "DataProfilResponse{" +
                "NIK='" + NIK + '\'' +
                ", nama='" + nama + '\'' +
                ", kelas='" + kelas + '\'' +
                ", jurusan='" + jurusan + '\'' +
                ", alamat='" + alamat + '\'' +
                '}';
    }
}
