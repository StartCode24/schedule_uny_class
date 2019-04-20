package com.starcode.skedi.model.FeedSchedule;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FeedSchedule {

    @SerializedName("schedule_id")
    @Expose
    private int schedule_id;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("start_time")
    @Expose
    private String start_time;

    @SerializedName("finish_time")
    @Expose
    private String finish_time;

    @SerializedName("day_name")
    @Expose
    private String day_name;

    @SerializedName("day_date")
    @Expose
    private int day_date;

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
    @SerializedName("color_mapel")
    @Expose
    private String color_mapel;

    public FeedSchedule(int schedule_id, String month, String start_time, String finish_time, String day_name, int day_date, String note, int guru_id, String guru_name, int mapel_id, String mapel_name, int kelas_id, String kelas_name, int jurusan_id, String jurusan_name, int room_id, String room_name, String color_mapel) {
        this.schedule_id = schedule_id;
        this.month = month;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day_name = day_name;
        this.day_date = day_date;
        this.note = note;
        this.guru_id = guru_id;
        this.guru_name = guru_name;
        this.mapel_id = mapel_id;
        this.mapel_name = mapel_name;
        this.kelas_id = kelas_id;
        this.kelas_name = kelas_name;
        this.jurusan_id = jurusan_id;
        this.jurusan_name = jurusan_name;
        this.room_id = room_id;
        this.room_name = room_name;
        this.color_mapel = color_mapel;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public int getDay_date() {
        return day_date;
    }

    public void setDay_date(int day_date) {
        this.day_date = day_date;
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

    public String getColor_mapel() {
        return color_mapel;
    }

    public void setColor_mapel(String color_mapel) {
        this.color_mapel = color_mapel;
    }

    @Override
    public String toString() {
        return "FeedSchedule{" +
                "schedule_id=" + schedule_id +
                ", month='" + month + '\'' +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                ", day_name='" + day_name + '\'' +
                ", day_date=" + day_date +
                ", note='" + note + '\'' +
                ", guru_id=" + guru_id +
                ", guru_name='" + guru_name + '\'' +
                ", mapel_id=" + mapel_id +
                ", mapel_name='" + mapel_name + '\'' +
                ", kelas_id=" + kelas_id +
                ", kelas_name='" + kelas_name + '\'' +
                ", jurusan_id=" + jurusan_id +
                ", jurusan_name='" + jurusan_name + '\'' +
                ", room_id=" + room_id +
                ", room_name='" + room_name + '\'' +
                ", color_mapel='" + color_mapel + '\'' +
                '}';
    }
}
