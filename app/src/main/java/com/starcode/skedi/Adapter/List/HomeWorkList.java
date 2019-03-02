package com.starcode.skedi.Adapter.List;

public class HomeWorkList {
    private int homework_id;
    private String homework_date;
    private String start_time;
    private String finish_time;
    private int day;
    private String note;
    private int guru_id;
    private String guru_name;
    private int mapel_id;
    private String mapel_name;
    private int kelas_id;
    private String kelas_name;
    private int jurusan_id;
    private String jurusan_name;
    private int room_id;
    private int month;
    private String room_name;

    public HomeWorkList(int homework_id, String homework_date, String start_time, String finish_time, int day, String note, int guru_id, String guru_name, int mapel_id, String mapel_name, int kelas_id, String kelas_name, int jurusan_id, String jurusan_name, int room_id, int month, String room_name) {
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
        this.kelas_name = kelas_name;
        this.jurusan_id = jurusan_id;
        this.jurusan_name = jurusan_name;
        this.room_id = room_id;
        this.month = month;
        this.room_name = room_name;
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }
}
