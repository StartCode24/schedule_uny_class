package com.starcode.skedi.model.FeedDataProfil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProfilResponse {
    @SerializedName("siswa_id")
    @Expose
    private String siswa_id;

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

    @SerializedName("siswa_password")
    @Expose
    private String siswa_password;

    @SerializedName("siswa_alamat")
    @Expose
    private String siswa_alamat;

    @SerializedName("siswa_note")
    @Expose
    private String siswa_note;

    @SerializedName("kelas_id")
    @Expose
    private String kelas_id;

    @SerializedName("jurusan_id")
    @Expose
    private String jurusan_id;

    public String getSiswa_id() {
        return siswa_id;
    }

    public void setSiswa_id(String siswa_id) {
        this.siswa_id = siswa_id;
    }

    public String getSiswa_note() {
        return siswa_note;
    }

    public void setSiswa_note(String siswa_note) {
        this.siswa_note = siswa_note;
    }

    public String getSiswa_nik() {
        return siswa_nik;
    }

    public void setSiswa_nik(String siswa_nik) {
        this.siswa_nik = siswa_nik;
    }

    public String getSiswa_name() {
        return siswa_name;
    }

    public String getSiswa_password() {
        return siswa_password;
    }

    public void setSiswa_password(String siswa_password) {
        this.siswa_password = siswa_password;
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

    public String getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(String kelas_id) {
        this.kelas_id = kelas_id;
    }


    public String getJurusan_id() {
        return jurusan_id;
    }

    public void setJurusan_id(String jurusan_id) {
        this.jurusan_id = jurusan_id;
    }

    @Override
    public String toString() {
        return "DataProfilResponse{" +
                "siswa_id='" + siswa_id + '\'' +
                ", siswa_nik='" + siswa_nik + '\'' +
                ", siswa_name='" + siswa_name + '\'' +
                ", siswa_kelas='" + siswa_kelas + '\'' +
                ", siswa_jurusan='" + siswa_jurusan + '\'' +
                ", siswa_password='" + siswa_password + '\'' +
                ", siswa_alamat='" + siswa_alamat + '\'' +
                ", siswa_note='" + siswa_note + '\'' +
                ", kelas_id='" + kelas_id + '\'' +
                ", jurusan_id='" + jurusan_id + '\'' +
                '}';
    }
}
