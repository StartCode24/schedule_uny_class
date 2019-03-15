package com.starcode.skedi.Adapter.List;

public class ScheduleList {
    private int schedule_id;
    private String start_time;
    private String finish_time;
    private String day_name;
    private int day_date;
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
    private String room_name;

    public ScheduleList(int schedule_id, String start_time, String finish_time, String day_name, int day_date, String note, int guru_id, String guru_name, int mapel_id, String mapel_name, int kelas_id, String kelas_name, int jurusan_id, String jurusan_name, int room_id, String room_name) {
        this.schedule_id = schedule_id;
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
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public String getDay_name() {
        return day_name;
    }

    public int getDay_date() {
        return day_date;
    }

    public String getNote() {
        return note;
    }

    public int getGuru_id() {
        return guru_id;
    }

    public String getGuru_name() {
        return guru_name;
    }

    public int getMapel_id() {
        return mapel_id;
    }

    public String getMapel_name() {
        return mapel_name;
    }

    public int getKelas_id() {
        return kelas_id;
    }

    public String getKelas_name() {
        return kelas_name;
    }

    public int getJurusan_id() {
        return jurusan_id;
    }

    public String getJurusan_name() {
        return jurusan_name;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_name() {
        return room_name;
    }
}
