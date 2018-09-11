package com.starcode.schedule_uny.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.starcode.schedule_uny.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rellay1)
    RelativeLayout rellay1;
    @BindView(R.id.rellay2)
    RelativeLayout rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash
    }

    @OnClick(R.id.btn_login)
    void btn_login(){
        Toast.makeText(MainActivity.this,"Login",Toast.LENGTH_SHORT).show();;
    }

    @OnClick(R.id.btn_fgtpassword)
    void btn_fgtpassword(){
        Toast.makeText(MainActivity.this,"Forget Password",Toast.LENGTH_SHORT).show();;
    }


}
