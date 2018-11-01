package com.starcode.schedule_uny.model.FeedDataProfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProfilResponse {
    @SerializedName("siswa_nik")
    @Expose
    private String siswa_nik;

    @SerializedName("siswa_name")
    @Expose
    private String siswa_name;

    @SerializedName("siswa_kelas")
    @Expose
    private String siswa_kelas;

    @SerializedName("siswa_jurusan")
    @Expose
    private String siswa_jurusan;

    @SerializedName("siswa_alamat")
    @Expose
    private String siswa_alamat;

    public String getSiswa_nik() {
        return siswa_nik;
    }

    public void setSiswa_nik(String siswa_nik) {
        this.siswa_nik = siswa_nik;
    }

    public String getSiswa_name() {
        return siswa_name;
    }

    public void setSiswa_name(String siswa_name) {
        this.siswa_name = siswa_name;
    }

    public String getSiswa_kelas() {
        return siswa_kelas;
    }

    public void setSiswa_kelas(String siswa_kelas) {
        this.siswa_kelas = siswa_kelas;
    }

    public String getSiswa_jurusan() {
        return siswa_jurusan;
    }

    public void setSiswa_jurusan(String siswa_jurusan) {
        this.siswa_jurusan = siswa_jurusan;
    }

    public String getSiswa_alamat() {
        return siswa_alamat;
    }

    public void setSiswa_alamat(String siswa_alamat) {
        this.siswa_alamat = siswa_alamat;
    }

    @Override
    public String toString() {
        return "DataProfilResponse{" +
                "siswa_nik='" + siswa_nik + '\'' +
                ", siswa_name='" + siswa_name + '\'' +
                ", siswa_kelas='" + siswa_kelas + '\'' +
                ", siswa_jurusan='" + siswa_jurusan + '\'' +
                ", siswa_alamat='" + siswa_alamat + '\'' +
                '}';
    }
}
