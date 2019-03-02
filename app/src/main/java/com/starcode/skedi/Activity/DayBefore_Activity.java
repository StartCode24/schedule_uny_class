package com.starcode.skedi.Activity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starcode.skedi.Adapter.List.DataSchedForDay;
import com.starcode.skedi.Adapter.List.ScheduleList;
import com.starcode.skedi.R;
import com.starcode.skedi.Receiver.AlertReceiver;
import com.starcode.skedi.Receiver.AlertReceiverDayBefore;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.FeedSearchSchedForDay.FeedDataSearchSchedForDay;
import com.starcode.skedi.model.FeedSearchSchedForDay.FeedSearchSchedForDay;
import com.starcode.skedi.model.SearchSchedForDayResponse;
import com.starcode.skedi.session.SessionDayBefore;
import com.starcode.skedi.session.SessionManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DayBefore_Activity extends AppCompatActivity {

    @BindView(R.id.TvSenin)TextView TvSenin;
    @BindView(R.id.TvSelasa)TextView TvSelasa;
    @BindView(R.id.TvRabu)TextView TvRabu;
    @BindView(R.id.TvKamis)TextView TvKamis;
    @BindView(R.id.TvJumat)TextView TvJumat;
    @BindView(R.id.TvSabtu)TextView TvSabtu;
    @BindView(R.id.TvMinggu)TextView TvMinggu;
    @BindView(R.id.toolbar)Toolbar toolbar;

    private SessionDayBefore sessionDayBefore;
    private SessionManager sessionManager;
    Dialog mDialog;
    int senin=0,selasa=0,rabu=0,kamis=0,jumat=0,sabtu=0,minggu=0;
    private String Day;
    private String status,status2="";
    private  String kelas_id;
    private String jurusan_id;
    private int hours=0;
    private int minute=0;
    private int notifId=0;
    private String Timer="";
    ArrayList<String> nameMapel=new ArrayList<String>();
    ArrayList<DataSchedForDay> mDataSchedForDay;
    private com.starcode.skedi.apiHolder.baseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_before_);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(DayBefore_Activity.this);
        getProfil();
        sessionDayBefore=new SessionDayBefore(DayBefore_Activity.this);
        loadData();
        toolbar.setTitle("Setting Alarm");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DayBefore_Activity.this,Setting_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        TvSenin.setText(sessionDayBefore.getSpSeninTime());
        TvSelasa.setText(sessionDayBefore.getSpSelasaTime());
        TvRabu.setText(sessionDayBefore.getSpRabuTime());
        TvKamis.setText(sessionDayBefore.getSpKamisTime());
        TvJumat.setText(sessionDayBefore.getSpJumatTime());
        TvSabtu.setText(sessionDayBefore.getSpSabtuTime());
        TvMinggu.setText(sessionDayBefore.getSpMingguTime());

        mDialog = new Dialog(this);



    }

    @OnClick(R.id.Ln_Senin)
    void btnSenin(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        senin=sessionDayBefore.getSpIdSenin();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdSenin());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                senin=position;
                DataSchedForDay("Selasa");
            }
        });
        if(mDataSchedForDay.size()==0){
            senin=0;
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    notifId=100000001;
                    if (senin==0){
                        sessionDayBefore.saveSPInt(SessionDayBefore.SP_SENIN,senin);
                        sessionDayBefore.saveSPString(sessionDayBefore.SP_SENIN_TIME,"OFF");
                        cancelAlarm();
                        mDataSchedForDay.clear();
                        Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                        startActivity(intent);
                        mDialog.dismiss();
                    }else {
                        if(hours==0){
                            Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                        }else {
                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(2,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_SENIN,senin);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_SENIN_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                        }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Selasa)
    void btnSelasa(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        selasa=sessionDayBefore.getSpIdSlasa();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdSlasa());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                selasa=position;
                DataSchedForDay("Rabu");

            }
        });
        if(mDataSchedForDay.size()==0){
            selasa=0;
        }
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000002;
                if (selasa==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_SELASA,selasa);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_SELASA_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(3,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_SELASA,selasa);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_SELASA_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Rabu)
    void btnRabu(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        rabu=sessionDayBefore.getSpIdRabu();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdRabu());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                rabu=position;
                DataSchedForDay("Kamis");

            }
        });
        if(mDataSchedForDay.size()==0){
            rabu=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000003;
                if (rabu==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_RABU,rabu);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_RABU_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(4,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_RABU,rabu);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_RABU_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Kamis)
    void btnKamis(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        kamis=sessionDayBefore.getSpIdKamis();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdKamis());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                kamis=position;
                DataSchedForDay("Jumat");

            }
        });
        if(mDataSchedForDay.size()==0){
            kamis=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000004;
                if (kamis==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_KAMIS,kamis);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_KAMIS_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(5,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_KAMIS,kamis);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_KAMIS_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Jumat)
    void btnJumat(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        jumat=sessionDayBefore.getSpIdJumat();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdJumat());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                jumat=position;
                DataSchedForDay("Sabtu");

            }
        });
        if(mDataSchedForDay.size()==0){
            jumat=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000005;
                if (jumat==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_JUMAT,jumat);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_JUMAT_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(6,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_JUMAT,jumat);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_JUMAT_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Sabtu)
    void btnSabtu(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        sabtu=sessionDayBefore.getSpIdSabtu();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdSabtu());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                sabtu=position;
                DataSchedForDay("Senin");

            }
        });
        if(mDataSchedForDay.size()==0){
            sabtu=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000006;
                if (sabtu==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_SABTU,sabtu);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_SABTU_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(7,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_SABTU,sabtu);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_SABTU_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.Ln_Minggu)
    void btnMinggu(){
        mDialog.setContentView(R.layout.popup_day_before);
        TimePicker timePickerAlrm=(TimePicker)mDialog.findViewById(R.id.timePickerAlrm);
        timePickerAlrm.setIs24HourView(true);

        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                hours=hour;
                minute=minut;
                Timer =hour+":"+minut+" WIB";
            }
        });
        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

        minggu=sessionDayBefore.getSpIdminggu();
        toggleSwitch.setCheckedTogglePosition(sessionDayBefore.getSpIdminggu());


        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                minggu=position;
                DataSchedForDay("Senin");

            }
        });
        if(mDataSchedForDay.size()==0){
            minggu=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifId=100000007;
                if (minggu==0){
                    sessionDayBefore.saveSPInt(SessionDayBefore.SP_MINGGU,minggu);
                    sessionDayBefore.saveSPString(sessionDayBefore.SP_MINGGU_TIME,"OFF");
                    cancelAlarm();
                    mDataSchedForDay.clear();
                    Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                    startActivity(intent);
                    mDialog.dismiss();
                }else {
                    if(hours==0){
                        Toast.makeText(DayBefore_Activity.this, "Cek Kembali Waktu Alarm", Toast.LENGTH_SHORT).show();
                    }else {

                        if(mDataSchedForDay.size()!=0){
                            DataSched();
                            saveNotificationSenin(1,hours,minute);
                            sessionDayBefore.saveSPInt(SessionDayBefore.SP_MINGGU,minggu);
                            sessionDayBefore.saveSPString(sessionDayBefore.SP_MINGGU_TIME,Timer);
                            mDataSchedForDay.clear();
                            Toast.makeText(DayBefore_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DayBefore_Activity.this, DayBefore_Activity.class);
                            startActivity(intent);
                            mDialog.dismiss();
                        }else {
                            Toast.makeText(DayBefore_Activity.this, "Tidak Ada Jadwal", Toast.LENGTH_SHORT).show();
                            mDataSchedForDay.clear();
                            mDialog.dismiss();
                        }
                    }

                }

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataSchedForDay.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    private void getProfil() {
        Call<DataProfilResponse> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()) {
                    status = response.body().getAuth_user().getStatus();
                    if (status.equals("200")) {
                        kelas_id = response.body().getAuth_user().getData().getKelas_id();
                        jurusan_id = response.body().getAuth_user().getData().getJurusan_id();
                    } else {
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(DayBefore_Activity.this, Main_Activity.class).
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<DataProfilResponse> call, Throwable t) {

            }
        });
    }
    public void DataSched(){

        for(int i=0;i<mDataSchedForDay.size();i++){
            nameMapel.add(mDataSchedForDay.get(i).getMapel_name());
        }
        clearDataSession();
    }

    public void DataSchedForDay(String day){
//        Toast.makeText(this, ""+jurusan_id+" "+kelas_id+" "+day, Toast.LENGTH_SHORT).show();
        Call<SearchSchedForDayResponse> call=baseApiService.SearchSchedForDay(kelas_id,jurusan_id,day);
        call.enqueue(new Callback<SearchSchedForDayResponse>() {
            @Override
            public void onResponse(Call<SearchSchedForDayResponse> call, Response<SearchSchedForDayResponse> response) {
                if (response.isSuccessful()){
                    status2=response.body().getAuth_SearchSchedForDay().getStatus();
                    if(status2.equals("200")){
                        List<FeedSearchSchedForDay> searchSchedForDays=response.body().getAuth_SearchSchedForDay().getData().getSchedule();
                        for (int i=0;i<searchSchedForDays.size();i++){
                            mDataSchedForDay.add(new DataSchedForDay(searchSchedForDays.get(i).getSchedule_id(),
                                    searchSchedForDays.get(i).getStart_time(),searchSchedForDays.get(i).getFinish_time(),
                                    searchSchedForDays.get(i).getDay_name(),searchSchedForDays.get(i).getDay_date(),
                                    searchSchedForDays.get(i).getNote(),searchSchedForDays.get(i).getGuru_id(),
                                    searchSchedForDays.get(i).getGuru_name(),searchSchedForDays.get(i).getMapel_id(),
                                    searchSchedForDays.get(i).getMapel_name(),searchSchedForDays.get(i).getKelas_id(),
                                    searchSchedForDays.get(i).getKelas_name(),searchSchedForDays.get(i).getJurusan_id(),
                                    searchSchedForDays.get(i).getJurusan_name(),searchSchedForDays.get(i).getRoom_id(),
                                    searchSchedForDays.get(i).getRoom_name()
                                    ));

                        }
                        saveData();
                        Toast.makeText(DayBefore_Activity.this, "sukses", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DayBefore_Activity.this, "Jadwal Tidak Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchSchedForDayResponse> call, Throwable t) {
                Toast.makeText(DayBefore_Activity.this, "Jadwal kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("daybeforesched",  Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
//        this.getSharedPreferences("daybeforesched", MODE_PRIVATE).edit().clear().commit();
        mDataSchedForDay.clear();

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("daybeforesched",  Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("DataSchedule", null);
        Type type = new TypeToken<ArrayList<DataSchedForDay>>() {
        }.getType();
        mDataSchedForDay = gson.fromJson(json, type);

        if (mDataSchedForDay == null) {
            mDataSchedForDay = new ArrayList<>();
        }
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("daybeforesched",  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mDataSchedForDay);
        editor.putString("DataSchedule", json);
        editor.apply();
    }
    public void saveNotificationSenin(int week,int hour,int minut){
        Calendar calSet = Calendar.getInstance();
        calSet.setTimeInMillis(System.currentTimeMillis());
        calSet.set(Calendar.DAY_OF_WEEK, week);
        calSet.set(Calendar.HOUR_OF_DAY, hour);
        calSet.set(Calendar.MINUTE, minut);
        calSet.set(Calendar.SECOND, 00);
        startAlarm(calSet,week);


    }

    private void startAlarm(Calendar c,int week) {
        Toast.makeText(this, ""+nameMapel.size(), Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverDayBefore.class);
        intent.putExtra("NOTIFID",""+ notifId);
        intent.putExtra("MapelName", nameMapel);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId, intent, 0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(),  24*60*60*1000, pendingIntent);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(), 24*60*60*1000, pendingIntent);
        } else {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(),  24*60*60*1000, pendingIntent);
        }

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverDayBefore.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(DayBefore_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();
        clearDataSession();
    }
}
