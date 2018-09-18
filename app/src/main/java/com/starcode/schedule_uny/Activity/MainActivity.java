package com.starcode.schedule_uny.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.starcode.schedule_uny.R;
import com.starcode.schedule_uny.apiHolder.baseApiService;
import com.starcode.schedule_uny.apiHolder.utilsApi;
import com.starcode.schedule_uny.model.FeedLogin;
import com.starcode.schedule_uny.model.LoginUser;
import com.starcode.schedule_uny.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


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
    private static String ContentType=" multipart/form-data";
    private static String Accept="application/json";
    private static final String TAG="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        baseApiService= utilsApi.getApiServices();
        sessionManager =new SessionManager(this);



        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }

    @OnClick(R.id.btn_login)
    void btnLogin(){
        Toast.makeText(MainActivity.this,"Login",Toast.LENGTH_SHORT).show();;
    }

    @OnClick(R.id.btn_fgtpassword)
    void btnFgtpassword(){
        Toast.makeText(MainActivity.this,"Forget Password",Toast.LENGTH_SHORT).show();;
    }

    public void loginRequest(){
        String nik=edNik.getText().toString();
        String password=edPassword.getText().toString();


        LoginUser loginUser=new LoginUser(nik,password);
        Call<FeedLogin> call= baseApiService.loginRequest(loginUser);

        call.enqueue(new Callback<FeedLogin>() {
            @Override
            public void onResponse(Call<FeedLogin> call, Response<FeedLogin> response) {
                if (response.isSuccessful()){
                    auth_token=response.body().getData().getToken();
                    try
                    {
                        sessionManager.saveSPString(sessionManager.SP_CONTENTTYPE,ContentType);
                        sessionManager.saveSPString(sessionManager.SP_ACCEPT,Accept);
                        sessionManager.saveSPString(sessionManager.SP_AUTHORIZATION,auth_token);
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN,true);
                        Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Home_activity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();

                    }catch (Exception e){
                        Log.d(TAG,"error : "+e);

                    }
                }else {
                    Toast.makeText(MainActivity.this, "Login not correct it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedLogin> call, Throwable t) {
                Log.w("error : ",t);
                Toast.makeText(MainActivity.this,"Cek Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
