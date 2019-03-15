package com.starcode.skedi.model.FeedSearchHomeWork;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedDataSearchHomeWork {
    @SerializedName("homework_id")
    @Expose
    private int homework_id;
    @SerializedName("start_time")
    @Expose
    private String start_time;
    @SerializedName("finish_time")
    @Expose
    private String finish_time;
    @SerializedName("day_name")
    @Expose
    private String day_name;
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
    @SerializedName("kelas_name")
    @Expose
    private String kelas_name;

    @SerializedName("jurusan_name")
    @Expose
    private String jurusan_name;

    @SerializedName("room_name")
    @Expose
    private String room_name;

    @SerializedName("alarm_time")
    @Expose
    private String alarm_time;

    @SerializedName("homework_date")
    @Expose
    private String homework_date;

    @SerializedName("dateName")
    @Expose
    private String dateName;

    @SerializedName("month")
    @Expose
    private int month;
    @SerializedName("date")
    @Expose
    private int date;
    @SerializedName("years")
    @Expose
    private int years;
    @SerializedName("hours")
    @Expose
    private int hours;
    @SerializedName("minute")
    @Expose
    private int minute;

    @SerializedName("homework_detail")
    @Expose
    private int homework_detail;
    @SerializedName("minut_before")
    @Expose
    private int minut_before;

    public FeedDataSearchHomeWork(int homework_id, String start_time, String finish_time, String day_name, String note, int guru_id, String guru_name, int mapel_id, String mapel_name, int kelas_id, String kelas_name, String jurusan_name, String room_name, String alarm_time, String homework_date, String dateName, int month, int date, int years, int hours, int minute, int homework_detail) {
        this.homework_id = homework_id;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day_name = day_name;
        this.note = note;
        this.guru_id = guru_id;
        this.guru_name = guru_name;
        this.mapel_id = mapel_id;
        this.mapel_name = mapel_name;
        this.kelas_id = kelas_id;
        this.kelas_name = kelas_name;
        this.jurusan_name = jurusan_name;
        this.room_name = room_name;
        this.alarm_time = alarm_time;
        this.homework_date = homework_date;
        this.dateName = dateName;
        this.month = month;
        this.date = date;
        this.years = years;
        this.hours = hours;
        this.minute = minute;
        this.homework_detail = homework_detail;
    }

    public int getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(int homework_id) {
        this.homework_id = homework_id;
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

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
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

    public String getKelas_name() {
        return kelas_name;
    }

    public void setKelas_name(String kelas_name) {
        this.kelas_name = kelas_name;
    }

    public String getJurusan_name() {
        return jurusan_name;
    }

    public void setJurusan_name(String jurusan_name) {
        this.jurusan_name = jurusan_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getHomework_date() {
        return homework_date;
    }

    public void setHomework_date(String homework_date) {
        this.homework_date = homework_date;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHomework_detail() {
        return homework_detail;
    }

    public void setHomework_detail(int homework_detail) {
        this.homework_detail = homework_detail;
    }

    @Override
    public String toString() {
        return "FeedDataSearchHomeWork{" +
                "homework_id=" + homework_id +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                ", day_name='" + day_name + '\'' +
                ", note='" + note + '\'' +
                ", guru_id=" + guru_id +
                ", guru_name='" + guru_name + '\'' +
                ", mapel_id=" + mapel_id +
                ", mapel_name='" + mapel_name + '\'' +
                ", kelas_id=" + kelas_id +
                ", kelas_name='" + kelas_name + '\'' +
                ", jurusan_name='" + jurusan_name + '\'' +
                ", room_name='" + room_name + '\'' +
                ", alarm_time='" + alarm_time + '\'' +
                ", homework_date='" + homework_date + '\'' +
                ", dateName='" + dateName + '\'' +
                ", month=" + month +
                ", date=" + date +
                ", years=" + years +
                ", hours=" + hours +
                ", minute=" + minute +
                ", homework_detail=" + homework_detail +
                ", minut_before=" + minut_before +
                '}';
    }
}
