package com.starcode.skedi.model.FeedHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedHomeWork {
    @SerializedName("homework_id")
    @Expose
    private int homework_id;
    @SerializedName("homework_date")
    @Expose
    private  String homework_date;
    @SerializedName("start_time")
    @Expose
    private String start_time;
    @SerializedName("finish_time")
    @Expose
    private String finish_time;
    @SerializedName("day")
    @Expose
    private int day;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("guru_id")
    @Expose
    private int guru_id;
    @SerializedName("guru_name")
    @Expose
    private String guru_name;
    @SerializedName("mapel_id")
    @Expose
    private int mapel_id;
    @SerializedName("mapel_name")
    @Expose
    private String mapel_name;
    @SerializedName("kelas_id")
    @Expose
    private int kelas_id;
    @SerializedName("month")
    @Expose
    private int month;
    @SerializedName("kelas_name")
    @Expose
    private String kelas_name;
    @SerializedName("jurusan_id")
    @Expose
    private int jurusan_id;
    @SerializedName("jurusan_name")
    @Expose
    private String jurusan_name;
    @SerializedName("room_id")
    @Expose
    private int room_id;
    @SerializedName("room_name")
    @Expose
    private String room_name;
    @SerializedName("siswa_nis")
    @Expose
    private String siswa_nis;
    @SerializedName("alarm_time")
    @Expose
    private String alarm_time;
    @SerializedName("homework_detail")
    @Expose
    private int homework_detail;
    @SerializedName("minut_before")
    @Expose
    private int minut_before;

    public FeedHomeWork(int homework_id, String homework_date, String start_time, String finish_time, int day, String note, int guru_id, String guru_name, int mapel_id, String mapel_name, int kelas_id, int month, String kelas_name, int jurusan_id, String jurusan_name, int room_id, String room_name, String siswa_nis, String alarm_time, int homework_detail, int minut_before) {
        this.homework_id = homework_id;
        this.homework_date = homework_date;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.note = note;
        this.guru_id = guru_id;
        this.guru_name = guru_name;
        this.mapel_id = mapel_id;
        this.mapel_name = mapel_name;
        this.kelas_id = kelas_id;
        this.month = month;
        this.kelas_name = kelas_name;
        this.jurusan_id = jurusan_id;
        this.jurusan_name = jurusan_name;
        this.room_id = room_id;
        this.room_name = room_name;
        this.siswa_nis = siswa_nis;
        this.alarm_time = alarm_time;
        this.homework_detail = homework_detail;
        this.minut_before = minut_before;
    }

    public int getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(int homework_id) {
        this.homework_id = homework_id;
    }

    public String getHomework_date() {
        return homework_date;
    }

    public void setHomework_date(String homework_date) {
        this.homework_date = homework_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(int guru_id) {
        this.guru_id = guru_id;
    }

    public String getGuru_name() {
        return guru_name;
    }

    public void setGuru_name(String guru_name) {
        this.guru_name = guru_name;
    }

    public int getMapel_id() {
        return mapel_id;
    }

    public void setMapel_id(int mapel_id) {
        this.mapel_id = mapel_id;
    }

    public String getMapel_name() {
        return mapel_name;
    }

    public void setMapel_name(String mapel_name) {
        this.mapel_name = mapel_name;
    }

    public int getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(int kelas_id) {
        this.kelas_id = kelas_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getKelas_name() {
        return kelas_name;
    }

    public void setKelas_name(String kelas_name) {
        this.kelas_name = kelas_name;
    }

    public int getJurusan_id() {
        return jurusan_id;
    }

    public void setJurusan_id(int jurusan_id) {
        this.jurusan_id = jurusan_id;
    }

    public String getJurusan_name() {
        return jurusan_name;
    }

    public void setJurusan_name(String jurusan_name) {
        this.jurusan_name = jurusan_name;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getSiswa_nis() {
        return siswa_nis;
    }

    public void setSiswa_nis(String siswa_nis) {
        this.siswa_nis = siswa_nis;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public int getHomework_detail() {
        return homework_detail;
    }

    public void setHomework_detail(int homework_detail) {
        this.homework_detail = homework_detail;
    }

    public int getMinut_before() {
        return minut_before;
    }

    public void setMinut_before(int minut_before) {
        this.minut_before = minut_before;
    }

    @Override
    public String toString() {
        return "FeedHomeWork{" +
                "homework_id=" + homework_id +
                ", homework_date='" + homework_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                ", day=" + day +
                ", note='" + note + '\'' +
                ", guru_id=" + guru_id +
                ", guru_name='" + guru_name + '\'' +
                ", mapel_id=" + mapel_id +
                ", mapel_name='" + mapel_name + '\'' +
                ", kelas_id=" + kelas_id +
                ", month=" + month +
                ", kelas_name='" + kelas_name + '\'' +
                ", jurusan_id=" + jurusan_id +
                ", jurusan_name='" + jurusan_name + '\'' +
                ", room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                ", siswa_nis='" + siswa_nis + '\'' +
                ", alarm_time='" + alarm_time + '\'' +
                ", homework_detail=" + homework_detail +
                ", minut_before=" + minut_before +
                '}';
    }
}
