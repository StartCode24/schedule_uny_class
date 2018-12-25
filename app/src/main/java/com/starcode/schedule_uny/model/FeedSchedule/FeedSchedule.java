package com.starcode.schedule_uny.model.FeedSchedule;

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
    private String schedule_id;
    @SerializedName("schedule_date")
    @Expose
    private  String schedule_date;
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
    private String guru_id;
    @SerializedName("guru_name")
    @Expose
    private String guru_name;
    @SerializedName("mapel_id")
    @Expose
    private String mapel_id;
    @SerializedName("mapel_name")
    @Expose
    private String mapel_name;
    @SerializedName("kelas_id")
    @Expose
    private String kelas_id;
    @SerializedName("kelas_name")
    @Expose
    private String kelas_name;
    @SerializedName("jurusan_id")
    @Expose
    private String jurusan_id;
    @SerializedName("jurusan_name")
    @Expose
    private String jurusan_name;
    @SerializedName("room_id")
    @Expose
    private String room_id;
    @SerializedName("room_name")
    @Expose
    private String room_name;

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
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

    public String getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(String guru_id) {
        this.guru_id = guru_id;
    }

    public String getGuru_name() {
        return guru_name;
    }

    public void setGuru_name(String guru_name) {
        this.guru_name = guru_name;
    }

    public String getMapel_id() {
        return mapel_id;
    }

    public void setMapel_id(String mapel_id) {
        this.mapel_id = mapel_id;
    }

    public String getMapel_name() {
        return mapel_name;
    }

    public void setMapel_name(String mapel_name) {
        this.mapel_name = mapel_name;
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

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    @Override
    public String toString() {
        return "FeedSchedule{" +
                "schedule_id='" + schedule_id + '\'' +
                ", schedule_date='" + schedule_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", finish_time='" + finish_time + '\'' +
                ", day=" + day +
                ", note='" + note + '\'' +
                ", guru_id='" + guru_id + '\'' +
                ", guru_name='" + guru_name + '\'' +
                ", mapel_id='" + mapel_id + '\'' +
                ", mapel_name='" + mapel_name + '\'' +
                ", kelas_id='" + kelas_id + '\'' +
                ", kelas_name='" + kelas_name + '\'' +
                ", jurusan_id='" + jurusan_id + '\'' +
                ", jurusan_name='" + jurusan_name + '\'' +
                ", room_id='" + room_id + '\'' +
                ", room_name='" + room_name + '\'' +
                '}';
    }

    @SuppressLint("SimpleDateFormat")
    public WeekViewEvent toWeekViewEvent(){

        // Parse time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = new Date();
        Date end = new Date();
        try {
            start = sdf.parse(getStart_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end = sdf.parse(getFinish_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Initialize start and end time.
        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(start.getTime());
        startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
        startTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
        startTime.set(Calendar.DAY_OF_MONTH, getDay());
        Calendar endTime = (Calendar) startTime.clone();
        endTime.setTimeInMillis(end.getTime());
        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(getMapel_name());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        //weekViewEvent.setColor(Color.parseColor(getColor()));

        return weekViewEvent;
    }
}
