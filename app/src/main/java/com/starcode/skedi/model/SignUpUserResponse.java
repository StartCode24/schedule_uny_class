package com.starcode.skedi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.starcode.skedi.model.FeedSignUpUser.FeedSignUpUser;

public class SignUpUserResponse {
    @SerializedName("auth_SignUp")
    @Expose
    private FeedSignUpUser auth_SignUp;

    public SignUpUserResponse(FeedSignUpUser auth_SignUp) {
        this.auth_SignUp = auth_SignUp;
    }

    public FeedSignUpUser getAuth_SignUp() {
        return auth_SignUp;
    }

    public void setAuth_SignUp(FeedSignUpUser auth_SignUp) {
        this.auth_SignUp = auth_SignUp;
    }

    @Override
    public String toString() {
        return "SignUpUserResponse{" +
                "auth_SignUp=" + auth_SignUp +
                '}';
    }
}
