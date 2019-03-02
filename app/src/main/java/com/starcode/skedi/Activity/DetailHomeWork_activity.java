package com.starcode.skedi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.DeleteHomeWorkResponse;
import com.starcode.skedi.model.SearchHomeWorkResponse;
import com.starcode.skedi.session.SessionDetailHomeWork;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHomeWork_activity extends AppCompatActivity {
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.Tv_mapelName)TextView TvMapelName;
    @BindView(R.id.Tv_noteDetail)TextView TvNoteDetail;
    @BindView(R.id.Tv_date)TextView TvDate;

    Dialog myDialog;
    private int idNotif=0;
    private String status,message,error,message2;
    private int idHomeWork;
    private SessionDetailHomeWork sessionDetailHomeWork;
    private com.starcode.skedi.apiHolder.baseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home_work_activity);
        ButterKnife.bind(this);
        sessionDetailHomeWork =new SessionDetailHomeWork(DetailHomeWork_activity.this);
        baseApiService = utilsApi.getApiServices();
        myDialog=new Dialog(this);
        cekSession();
        DetailHomeWork();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailHomeWork_activity.this,HomeWork_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @OnClick(R.id.Tvb_hapus)
    public void btnHapus(View view){
        TextView btnClose;
        Button btnYes,btnNo;
        myDialog.setContentView(R.layout.popup_delete_homework);
        btnClose =(TextView) myDialog.findViewById(R.id.Tvb_close);
        btnClose.setText("X");
        btnYes = (Button) myDialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                clearDataSession();
                DeleteHomeWork();
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @OnClick(R.id.Tvb_edit)
    public void btnEdit(View view){
        Intent intent=new Intent(DetailHomeWork_activity.this,EditHomework_Activity.class);
        startActivity(intent);
    }
    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public void DeleteHomeWork(){

        retrofit2.Call<DeleteHomeWorkResponse> call=baseApiService.DeleteHomeWork(idHomeWork);
        call.enqueue(new Callback<DeleteHomeWorkResponse>() {
            @Override
            public void onResponse(retrofit2.Call<DeleteHomeWorkResponse> call, Response<DeleteHomeWorkResponse> response) {
                if(response.isSuccessful()){
                    error=response.body().getAuth_DeleteHomework().getError();
                    message2=response.body().getAuth_DeleteHomework().getMessage();
                    if(error.equals("200")){
                        Intent intent=new Intent(DetailHomeWork_activity
                        .this,HomeWork_Activity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(DetailHomeWork_activity.this,"Data Berhasil Di Hapus",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailHomeWork_activity.this,""+message2,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<DeleteHomeWorkResponse> call, Throwable t) {

            }
        });
    }

    public void DetailHomeWork(){

        retrofit2.Call<SearchHomeWorkResponse> call=baseApiService.SearchHomeWork(idHomeWork);
        call.enqueue(new Callback<SearchHomeWorkResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SearchHomeWorkResponse> call, Response<SearchHomeWorkResponse> response) {
                if(response.isSuccessful()){
                    status=response.body().getAuth_SearchHomeWork().getStatus();
                    message=response.body().getAuth_SearchHomeWork().getMessage();
                    if(status.equals("200")){
                        TvMapelName.setText(response.body().getAuth_SearchHomeWork().getData().getMapel_name());
                        TvDate.setText(response.body().getAuth_SearchHomeWork().getData().getHomework_date());
                        TvNoteDetail.setText(response.body().getAuth_SearchHomeWork().getData().getNote());
                    }else {
                        Toast.makeText(DetailHomeWork_activity.this,""+message,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<SearchHomeWorkResponse> call, Throwable t) {

            }
        });
    }

    public void cekSession(){
        if(getIntent().hasExtra("NOTIFID")){
            idNotif=Integer.parseInt(getIntent().getStringExtra("NOTIFID"));
            sessionDetailHomeWork.saveSPLong(SessionDetailHomeWork.SP_IDHOMEWORK, idNotif);
        }
        idHomeWork=(int) sessionDetailHomeWork.getSpIdHomeWork();
    }
}
