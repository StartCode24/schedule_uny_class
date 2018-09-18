package com.starcode.schedule_uny.model;

public class LoginUser {
    private String NIK;
    private String Password;

    public LoginUser(String NIK, String password) {
        this.NIK = NIK;
        Password = password;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
