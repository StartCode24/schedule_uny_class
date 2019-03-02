package com.starcode.skedi.apiHolder;

import com.starcode.skedi.model.AddHomeWorkResponse;
import com.starcode.skedi.model.AllJurusanResponse;
import com.starcode.skedi.model.AllKelasResponse;
import com.starcode.skedi.model.AllMapelResponse;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.DeleteHomeWorkResponse;
import com.starcode.skedi.model.EditHomeWorkResponse;
import com.starcode.skedi.model.EditUserProfile;
import com.starcode.skedi.model.HomeWorkResponse;
import com.starcode.skedi.model.IDHomeWorkResponse;
import com.starcode.skedi.model.LoginUserResponse;
import com.starcode.skedi.model.ScheduleResponse;
import com.starcode.skedi.model.SearchHomeWorkResponse;
import com.starcode.skedi.model.SearchSchedForDayResponse;
import com.starcode.skedi.model.SearchSchedResponse;
import com.starcode.skedi.model.SearchScheduleAllResponse;
import com.starcode.skedi.model.SignUpUserResponse;

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

    @POST("Users/GetProfile")
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

    @FormUrlEncoded
    @POST("Users/SignUpUser")
    Call<SignUpUserResponse> SignUpUser(@Field("NIS") String siswa_nis,
                                        @Field("siswa_name") String siswa_name,
                                        @Field("siswa_alamat") String siswa_alamat,
                                        @Field("kelas_nama") String kelas_nama,
                                        @Field("jurusan_nama") String jurusan_nama,
                                        @Field("password_1") String siswa_password_1,
                                        @Field("password_2") String siswa_password_2
    );
    @FormUrlEncoded
    @POST("Content/AddHomeWork")
    Call<AddHomeWorkResponse> AddHomeWork(@Field("id_homework") String id_homework,
                                          @Field("mapel_name") String mapel_name,
                                          @Field("note") String note,
                                          @Field("homework_date") String homework_date,
                                          @Field("start_time") String start_time,
                                          @Field("finish_time") String finish_time,
                                          @Field("alarm_time") String alarm_time,
                                          @Field("kelas_id") String kelas_id,
                                          @Field("jurusan_id") String jurusan_id,
                                          @Field("siswa_nik") String siswa_nik
    );

    @FormUrlEncoded
    @POST("Content/UpdateHomeWork")
    Call<EditHomeWorkResponse> UpdateHomeWork(@Field("homework_id") int homework_id,
                                              @Field("mapel_name") String mapel_name,
                                              @Field("note") String note,
                                              @Field("homework_date") String homework_date,
                                              @Field("start_time") String start_time,
                                              @Field("finish_time") String finish_time,
                                              @Field("alarm_time") String alarm_time,
                                              @Field("kelas_id") String kelas_id,
                                              @Field("jurusan_id") String jurusan_id,
                                              @Field("siswa_nik") String siswa_nik
    );

    @FormUrlEncoded
    @POST("Content/SearchSched")
    Call<SearchSchedResponse> SearchSched(@Field("schedule_id") int schedule_id);

    @FormUrlEncoded
    @POST("Content/SearchHomeWork")
    Call<SearchHomeWorkResponse> SearchHomeWork(@Field("homework_id") int homework_id);

    @FormUrlEncoded
    @POST("Content/DeleteHomeWork")
    Call<DeleteHomeWorkResponse> DeleteHomeWork(@Field("homework_id") int homework_id);

    @FormUrlEncoded
    @POST("Content/GetSchedule")
    Call<ScheduleResponse> Schedule(@Field("kelas_id") String kelas_id,
                                    @Field("jurusan_id") String jurusan_id);

    @FormUrlEncoded
    @POST("Content/SearchScheduleAll")
    Call<SearchScheduleAllResponse> SearchScheduleAll(@Field("kelas_id") String kelas_id,
                                                      @Field("jurusan_id") String jurusan_id);

    @FormUrlEncoded
    @POST("Content/SearchSchedForDay")
    Call<SearchSchedForDayResponse> SearchSchedForDay(@Field("kelas_id") String kelas_id,
                                                      @Field("jurusan_id") String jurusan_id,
                                                      @Field("day_name") String day_name);

    @FormUrlEncoded
    @POST("Content/GetHomeWork")
    Call<HomeWorkResponse> HomeWork(@Field("kelas_id") String kelas_id,
                                    @Field("jurusan_id") String jurusan_id,
                                    @Field("siswa_nik") String siswa_nik);


    @POST("Content/GetJurusan")
    Call<AllJurusanResponse> getAllJurusan();

    @POST("Content/GetMapel")
    Call<AllMapelResponse> getAllMapel();

    @POST("Content/GetIdHomework")
    Call<IDHomeWorkResponse> getIdHomeWork();

    @POST("Content/GetKelas")
    Call<AllKelasResponse> getAllKelas();
}
