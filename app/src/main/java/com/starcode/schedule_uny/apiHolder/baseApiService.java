package com.starcode.schedule_uny.apiHolder;

import com.starcode.schedule_uny.model.DataProfilResponse;
import com.starcode.schedule_uny.model.EditUserProfile;
import com.starcode.schedule_uny.model.LoginUserResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface baseApiService {
    @FormUrlEncoded
    @POST("Users/LoginPost")
    Call<LoginUserResponse> loginRequest(@Field("NIK") String nik,
                                         @Field("Password") String password);

    @GET("Users/GetProfile")
    Call<DataProfilResponse> getAllProfile(@Header("Content-Type") String contentType,
                                           @Header("Authorization") String authToken);

    @FormUrlEncoded
    @POST("Users/EditProfile")
    Call<EditUserProfile> editUserProfile(@Field("siswa_id") String siswa_id,
                                          @Field("siswa_nik") String siswa_nik,
                                          @Field("siswa_name") String siswa_name,
                                          @Field("siswa_alamat") String siswa_alamat,
                                          @Field("kelas_id") String kelas_id,
                                          @Field("jurusan_id") String jurusan_id,
                                          @Field("siswa_password") String siswa_password,
                                          @Field("siswa_note") String siswa_note
                                          );
}
