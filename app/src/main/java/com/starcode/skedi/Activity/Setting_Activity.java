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
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starcode.skedi.Adapter.List.AlarmSchedPerminute;
import com.starcode.skedi.Adapter.List.DataSchedForDay;
import com.starcode.skedi.Adapter.List.ScheduleList;
import com.starcode.skedi.R;
import com.starcode.skedi.Receiver.AlertReceiverDayBefore;
import com.starcode.skedi.Receiver.AlertReceiverMinuteBefore;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.FeedSchedule.FeedSchedule;
import com.starcode.skedi.model.FeedSearchScheduleAll.FeedSearchScheduleAll;
import com.starcode.skedi.model.ScheduleResponse;
import com.starcode.skedi.model.SearchScheduleAllResponse;
import com.starcode.skedi.session.SessionManager;
import com.starcode.skedi.session.SessionMinuteBefore;

import java.lang.reflect.Type;
import java.sql.Time;
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

public class Setting_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SessionManager sessionManager;
    private SessionMinuteBefore sessionMinuteBefore;

    @BindView(R.id.nav_view)NavigationView navigationView;
    @BindView(R.id.TvMinuteBefore)TextView TvMinutebefore;

    private com.starcode.skedi.apiHolder.baseApiService baseApiService;
    private static String status,status2;
    private static String name;
    private static String jurusan;
    Dialog mDialog;
    private int hours=0;
    private int minute=0;
    private int notifId=0;
    int ActivePosition=0;
    private static String kelas_id="";
    private static String jurusan_id="";

    private ImageView imageViewProfil;
    String mapelName="";
    int mapelId=0;
    String TimeStr = "";
    String startHours = "";
    String startMinute = "";
    int alrmHours=0;
    int alrmMinut=0;
    int dayNumber=1;
    private TextView tvName;
    private TextView tvJurusan;
    ArrayList<AlarmSchedPerminute> mAlarmSchedPerminutes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(Setting_Activity.this);
        sessionMinuteBefore=new SessionMinuteBefore(Setting_Activity.this);
        getProfil();
        loadData();
        mDialog = new Dialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TvMinutebefore.setText(sessionMinuteBefore.getSpAlarmTime());

    }
    @OnClick(R.id.Ln_DayBefore)
    void btn_DayBefore() {
        startActivity(new Intent(Setting_Activity.this, DayBefore_Activity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        finish();
    }
    @OnClick(R.id.Ln_MinuteBferoe)
    void btn_MinuteBferoe(){
        mDialog.setContentView(R.layout.pop_up_minute_before);
        final NumberPicker hoursPicker=(NumberPicker)mDialog.findViewById(R.id.numberHours);
        final NumberPicker minutePicker=(NumberPicker)mDialog.findViewById(R.id.numberMinute);
        hoursPicker.setMinValue(0);
        hoursPicker.setMaxValue(23);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        if(ActivePosition==0){
            hoursPicker.setEnabled(false);
            minutePicker.setEnabled(false);
        }

        hoursPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int hour) {
                hours=hour;

            }
        });
        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int minut) {
                minute=minut;
            }
        });

        Button saveBtn=(Button)mDialog.findViewById(R.id.btn_save);
        Button noBtn=(Button)mDialog.findViewById(R.id.btn_no);
        ToggleSwitch toggleSwitch = (ToggleSwitch) mDialog.findViewById(R.id.Tg_Active);
        ArrayList<String> labels = new ArrayList<>();
        labels.add("OFF");
        labels.add("ON");
        toggleSwitch.setLabels(labels);

//        senin=sessionDayBefore.getSpIdSenin();
        toggleSwitch.setCheckedTogglePosition(sessionMinuteBefore.getSpAlarmActive());
        scheduleData();
        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {
                ActivePosition=position;
                if(ActivePosition==0){
                    hoursPicker.setEnabled(false);
                    minutePicker.setEnabled(false);
                }
                else{
                    hoursPicker.setEnabled(true);
                    minutePicker.setEnabled(true);
                }

            }
        });
        if(mAlarmSchedPerminutes.size()==0){
            ActivePosition=0;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivePosition==0){
                    sessionMinuteBefore.saveSPString(SessionMinuteBefore.SP_ALARM_TIME,"OFF");
                    sessionMinuteBefore.saveSPInt(SessionMinuteBefore.SP_ALARM_Active,ActivePosition);
                    AlarmSchedCancel();
                    Intent intent=new Intent(Setting_Activity.this,Setting_Activity.class);
                    startActivity(intent);
                    mAlarmSchedPerminutes.clear();
                    clearDataSession();

                }else {
                    sessionMinuteBefore.saveSPString(sessionMinuteBefore.SP_ALARM_TIME, hours + ":" + minute + " Jam");
                    sessionMinuteBefore.saveSPInt(SessionMinuteBefore.SP_ALARM_Active,ActivePosition);
                    AlarmSchedBefMinut(hours,minute);
                    Toast.makeText(Setting_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Setting_Activity.this,Setting_Activity.class);
                    startActivity(intent);
                    mAlarmSchedPerminutes.clear();
                    clearDataSession();



                }
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlarmSchedPerminutes.clear();
                clearDataSession();
                mDialog.dismiss();
            }
        });

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    public void AlarmSchedCancel(){
        for (int i=0;i<mAlarmSchedPerminutes.size();i++){
            notifId=1000020+i;
            cancelAlarm(notifId);
        }
        mAlarmSchedPerminutes.clear();
        clearDataSession();
    }

    public void AlarmSchedBefMinut(int hours,int minutes){

//        Toast.makeText(this, ""+alrmHours+":"+alrmMinut, Toast.LENGTH_SHORT).show();
        for (int i=0;i<mAlarmSchedPerminutes.size();i++){
            notifId=1000020+i;
            TimeStr=mAlarmSchedPerminutes.get(i).getStart_time();
            dayNumber=mAlarmSchedPerminutes.get(i).getDay_number();
            mapelName=mAlarmSchedPerminutes.get(i).getMapel_name();
            mapelId=mAlarmSchedPerminutes.get(i).getSchedule_id();
            startHours=TimeStr.substring(0,2);
            startMinute=TimeStr.substring(3,5);
            alrmHours=Math.abs(Integer.parseInt(startHours)-hours);
            alrmMinut=Math.abs(Integer.parseInt(startMinute)-minutes);
            saveNotification(dayNumber,alrmHours,alrmMinut,notifId,mapelId);

        }
        mAlarmSchedPerminutes.clear();
        clearDataSession();
    }

    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("minutebeforesched",  Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        mAlarmSchedPerminutes.clear();

    }
    public void saveNotification(int week,int hour,int minut,int idNotif,int idSched){
        Calendar calSet = Calendar.getInstance();
//        calSet.setTimeInMillis(System.currentTimeMillis());
        calSet.set(Calendar.DAY_OF_WEEK, week);
        calSet.set(Calendar.HOUR_OF_DAY, hour);
        calSet.set(Calendar.MINUTE, minut);
        calSet.set(Calendar.SECOND, 0);
        startAlarm(calSet,idNotif,idSched);


    }

    private void startAlarm(Calendar c,int notifId1,int idSched) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverMinuteBefore.class);
        intent.putExtra("NOTIFID",""+ notifId1);
        intent.putExtra("SchedlID", ""+idSched);
        intent.putExtra("MapelName", ""+mapelName);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                c.getTimeInMillis(), 1 * 60 * 60 * 1000, pendingIntent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                    c.getTimeInMillis(), 1 * 60 * 60 * 1000, pendingIntent);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                    c.getTimeInMillis(), 1 * 60 * 60 * 1000, pendingIntent);
//        } else {
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                    c.getTimeInMillis(), 1 * 60 * 60 * 1000, pendingIntent);
//        }

    }

    private void cancelAlarm(int notifId1) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiverMinuteBefore.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(Setting_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();
        clearDataSession();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("minutebeforesched",  Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("DataScheduleAlarm", null);
        Type type = new TypeToken<ArrayList<AlarmSchedPerminute>>() {
        }.getType();
        mAlarmSchedPerminutes = gson.fromJson(json, type);

        if (mAlarmSchedPerminutes == null) {
            mAlarmSchedPerminutes = new ArrayList<>();
        }
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("minutebeforesched",  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mAlarmSchedPerminutes);
        editor.putString("DataScheduleAlarm", json);
        editor.apply();
    }




    //    get Profil
    private void getProfil() {
        Call<DataProfilResponse> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()) {
                    status = response.body().getAuth_user().getStatus();
                    if (status.equals("200")) {
                        name = response.body().getAuth_user().getData().getSiswa_name();
                        jurusan = response.body().getAuth_user().getData().getSiswa_jurusan();
                        kelas_id = response.body().getAuth_user().getData().getKelas_id();
                        jurusan_id = response.body().getAuth_user().getData().getJurusan_id();

                        initComponentNavHeader();
                    } else {
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(Setting_Activity.this, Main_Activity.class).
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

    private void scheduleData() {
        Call<SearchScheduleAllResponse> call = baseApiService.SearchScheduleAll(kelas_id, jurusan_id);
        call.enqueue(new Callback<SearchScheduleAllResponse>() {
            @Override
            public void onResponse(Call<SearchScheduleAllResponse> call, Response<SearchScheduleAllResponse> response) {
                if (response.isSuccessful()) {
                    status2 = response.body().getAuth_SearchScheduleAll().getStatus();
                    if (status2.equals("200")) {
                        if(mAlarmSchedPerminutes.size()==0) {
                            List<FeedSearchScheduleAll> schedule = response.body().getAuth_SearchScheduleAll().getData().getSchedule();
                            for (int i = 0; i < schedule.size(); i++) {
                                mAlarmSchedPerminutes.add(new AlarmSchedPerminute(schedule.get(i).getSchedule_id(),
                                        schedule.get(i).getStart_time(), schedule.get(i).getFinish_time(), schedule.get(i).getDay_number(),
                                        schedule.get(i).getNote(), schedule.get(i).getGuru_id(), schedule.get(i).getGuru_name(),
                                        schedule.get(i).getMapel_id(), schedule.get(i).getMapel_name(), schedule.get(i).getKelas_id(),
                                        schedule.get(i).getKelas_name(), schedule.get(i).getJurusan_id(), schedule.get(i).getJurusan_name(),
                                        schedule.get(i).getRoom_id(), schedule.get(i).getRoom_name()));
                            }
                            saveData();
                        }


                    }
                } else {
                    Toast.makeText(Setting_Activity.this, "Data Tidak ditemukan",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchScheduleAllResponse> call, Throwable t) {

            }
        });


    }


    @Override
    public void onBackPressed() {


            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_today) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            startActivity(new Intent(Setting_Activity.this, Home_activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {
            Toast.makeText(Setting_Activity.this, "Setting", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_homework) {
            startActivity(new Intent(Setting_Activity.this, HomeWork_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            Toast.makeText(Setting_Activity.this,"PR",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(Setting_Activity.this, Main_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(Setting_Activity.this, About_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_Profile) {
            startActivity(new Intent(Setting_Activity.this, Profile_activity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initComponentNavHeader() {
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

//        imageViewProfil=headerView.findViewById(R.id.imageViewProfile);
        tvName = headerView.findViewById(R.id.tvName);
        tvJurusan = headerView.findViewById(R.id.tvJurusan);

        tvName.setText(name);
        tvJurusan.setText(jurusan);

//        imageViewProfil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Foto Profile",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
