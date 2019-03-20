package com.starcode.skedi.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.SearchSchedResponse;
import com.starcode.skedi.session.SessionDetailHomeWork;
import com.starcode.skedi.session.SessionDetailSchedule;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSchedule_Activity extends AppCompatActivity {

    private SessionDetailSchedule sessionDetailSchedule;
    private com.starcode.skedi.apiHolder.baseApiService baseApiService;
    private int idSched;
    private int SchedlID=0;
    private String status, message;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.Tv_mapelName)
    TextView tvMapelName;



    @BindView(R.id.Tv_day)
    TextView tvDay;

    @BindView(R.id.Tv_teacherName)
    TextView tvTeacherName;

    @BindView(R.id.Tv_startTime)
    TextView tvStarTime;

    @BindView(R.id.Tv_finishTime)
    TextView tvFinishTime;

    @BindView(R.id.Tv_roomName)
    TextView tvRoomName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule_);
        ButterKnife.bind(this);
        sessionDetailSchedule = new SessionDetailSchedule(DetailSchedule_Activity.this);
        baseApiService = utilsApi.getApiServices();
        cekSession();

        getDetailSched();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailSchedule_Activity.this, AddHomeWork_Activity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    public void getDetailSched() {
        Call<SearchSchedResponse> call = baseApiService.SearchSched(idSched);
        call.enqueue(new Callback<SearchSchedResponse>() {
            @Override
            public void onResponse(Call<SearchSchedResponse> call, Response<SearchSchedResponse> response) {
                if (response.isSuccessful()) {
                    status = response.body().getAuth_SearchSched().getStatus();
                    message = response.body().getAuth_SearchSched().getMessage();
                    if (status.equals("200")) {
                        tvMapelName.setText(response.body().getAuth_SearchSched().getData().getMapel_name());
//                        tvMonth.setText(response.body().getAuth_SearchSched().getData().getMonth());
                        tvDay.setText(response.body().getAuth_SearchSched().getData().getDay_name());
                        tvTeacherName.setText(response.body().getAuth_SearchSched().getData().getGuru_name());
                        tvStarTime.setText(response.body().getAuth_SearchSched().getData().getStart_time());
                        tvFinishTime.setText(response.body().getAuth_SearchSched().getData().getFinish_time());
                        tvRoomName.setText(response.body().getAuth_SearchSched().getData().getRoom_name());
//                        tvClassName.setText(response.body().getAuth_SearchSched().getData().getKelas_name());
                    } else {
                        Toast.makeText(DetailSchedule_Activity.this, "" + message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchSchedResponse> call, Throwable t) {

            }
        });
    }
    public void cekSession(){
        if(getIntent().hasExtra("SchedlID")){
            SchedlID=Integer.parseInt(getIntent().getStringExtra("SchedlID"));
            sessionDetailSchedule.saveSPLong(SessionDetailSchedule.SP_IDSCHEDULE, SchedlID);
        }
        idSched=(int) sessionDetailSchedule.getSpIdSchedule();
//        Toast.makeText(this, ""+SchedlID, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(DetailSchedule_Activity.this,Home_activity.class);
        startActivity(a);
        finish();

    }
}
