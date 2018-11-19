package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.EditDataProfil.FeedEditDataProfile;

public class EditUserProfile {
    @SerializedName("auth_update_siswa")
    @Expose
    private FeedEditDataProfile auth_update_siswa;

    public FeedEditDataProfile getAuth_update_siswa() {
        return auth_update_siswa;
    }

    public void setAuth_update_siswa(FeedEditDataProfile auth_update_siswa) {
        this.auth_update_siswa = auth_update_siswa;
    }

    @Override
    public String toString() {
        return "EditUserProfile{" +
                "auth_update_siswa=" + auth_update_siswa +
                '}';
    }
}
