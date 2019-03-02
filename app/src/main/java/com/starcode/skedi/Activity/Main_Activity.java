package com.starcode.skedi.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.LoginUserResponse;
import com.starcode.skedi.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main_Activity extends AppCompatActivity {


    @BindView(R.id.rellay1)
    RelativeLayout rellay1;
    @BindView(R.id.rellay2)
    RelativeLayout rellay2;

    @BindView(R.id.ed_nik)
    EditText edNik;
    @BindView(R.id.ed_password)
    EditText edPassword;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    private baseApiService baseApiService;
    private SessionManager sessionManager;

    private static String auth_token;
    private static String status;
    private static String ContentType = "application/json";
    private static final String TAG = "Main_Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(Main_Activity.this);
        if (sessionManager.getSpSesionlogin()) {
            startActivity(new Intent(Main_Activity.this, Home_activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }

    @OnClick(R.id.btn_login)
    void btnLogin() {
        loginRequest();
    }

    @OnClick(R.id.btn_SignUp)
    void btnSignUp() {
//        Toast.makeText(Main_Activity.this,"Daftar",Toast.LENGTH_SHORT).show();;
        startActivity(new Intent(Main_Activity.this, SignUpUser_Activity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    public void loginRequest() {
        String nik = edNik.getText().toString();
        String password = edPassword.getText().toString();

        if (nik.isEmpty() || password.isEmpty()) {
            Toast.makeText(Main_Activity.this, "Nik atau password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            ;
        } else {

            Call<LoginUserResponse> call = baseApiService.loginRequest(nik, password);

            call.enqueue(new Callback<LoginUserResponse>() {
                @Override
                public void onResponse(Call<LoginUserResponse> call, Response<LoginUserResponse> response) {
                    if (response.isSuccessful()) {
                        status = response.body().getAuth_login().getStatus();

                        try {
                            if (status.equals("200")) {
                                auth_token = response.body().getAuth_login().getData().getAuth_token();
                                sessionManager.saveSPString(sessionManager.SP_CONTENTTYPE, ContentType);
                                sessionManager.saveSPString(sessionManager.SP_AUTHORIZATION, auth_token);
                                sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, true);
                                Toast.makeText(Main_Activity.this, "Login Berhasil ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Main_Activity.this, Home_activity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
                            } else {
                                Toast.makeText(Main_Activity.this, "Nik atau password tidak terdaftar ", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Log.d(TAG, "error : " + e);

                        }
                    } else {
                        Toast.makeText(Main_Activity.this, "Nik atau password tidak terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                    Log.w("error : ", t);
                    Toast.makeText(Main_Activity.this, "Cek Koneksi", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
