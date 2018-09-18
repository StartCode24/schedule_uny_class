package com.starcode.schedule_uny.apiHolder;

import com.starcode.schedule_uny.model.FeedLogin;
import com.starcode.schedule_uny.model.FeedUser;
import com.starcode.schedule_uny.model.LoginUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface baseApiService {
    @POST("Users/LoginPost")
    Call<FeedLogin> loginRequest(@Body LoginUser login);

    @GET("Users/GetProfile")
    Call<FeedUser> getAllProfile(@Header("Content-Type") String contentType,
                                 @Header("Accept") String Accept,
                                 @Header("Authorization") String authToken);
}
