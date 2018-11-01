package com.starcode.schedule_uny.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.schedule_uny.model.FeedDataProfil.FeedAuthUser;


public class DataProfilResponse {
   @SerializedName("auth_user")
   @Expose
   private FeedAuthUser auth_user;

    public DataProfilResponse(FeedAuthUser auth_user) {
        this.auth_user = auth_user;
    }

    public FeedAuthUser getAuth_user() {
        return auth_user;
    }

    public void setAuth_user(FeedAuthUser auth_user) {
        this.auth_user = auth_user;
    }

    @Override
    public String toString() {
        return "DataProfilResponse{" +
                "auth_user=" + auth_user +
                '}';
    }
}
