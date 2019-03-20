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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.starcode.skedi.Receiver.AlertReceiverMinuteBefore2;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.FeedSchedule.FeedSchedule;
import com.starcode.skedi.model.FeedSearchScheduleAll.FeedSearchScheduleAll;
import com.starcode.skedi.model.ScheduleResponse;
import com.starcode.skedi.model.SearchScheduleAllResponse;
import com.starcode.skedi.session.SessionDetailHomeWork;
import com.starcode.skedi.session.SessionDetailSchedule;
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
    private SessionDetailHomeWork sessionDetailHomeWork;
    private SessionDetailSchedule sessionDetailSchedule;

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
    int startHours ;
    int startMinute ;
    int weekday;
    int dayNumber=1;
    private TextView tvName;
    private TextView tvJurusan;
    private int minutSave,hoursSave;

    private int beforeMinut=0;

    ArrayList<AlarmSchedPerminute> mAlarmSchedPerminutes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(Setting_Activity.this);
        sessionMinuteBefore=new SessionMinuteBefore(Setting_Activity.this);
        sessionDetailSchedule = new SessionDetailSchedule(this);
        sessionDetailHomeWork =new SessionDetailHomeWork(this);
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
    public void btn_MinuteBferoe(View view){
        mDialog.setContentView(R.layout.popup_minut);

        scheduleData();
        final RadioGroup radioMinutGroup = (RadioGroup)mDialog.findViewById(R.id.radioMinut);
        TextView minutSimpan=(TextView)mDialog.findViewById(R.id.tvBtnSimpan);
        minutSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioMinutGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton radioMinutButton = (RadioButton)mDialog.findViewById(selectedId);
                switch (selectedId){
                    case R.id.radio0Minut:
                        beforeMinut=0;
                        break;
                    case R.id.radio5Minut:
                        beforeMinut=5;
                        break;
                    case R.id.radio10Minut:
                        beforeMinut=10;
                        break;
                    case R.id.radio15Minut:
                        beforeMinut=15;
                        break;
                    case R.id.radio30Minut:
                        beforeMinut=30;
                        break;
                    case R.id.radio60Minut:
                        beforeMinut=60;
                        break;

                }
                if(mAlarmSchedPerminutes.size()!=0){

                    if(beforeMinut==0){
                        sessionMinuteBefore.saveSPString(sessionMinuteBefore.SP_ALARM_TIME, "OFF");
                        AlarmSchedCancel();
                    }else {
                        sessionMinuteBefore.saveSPString(sessionMinuteBefore.SP_ALARM_TIME, beforeMinut+" Minut");
                        AlarmSchedBefMinut(beforeMinut);
                    }
                        Toast.makeText(Setting_Activity.this, "Alarm Tersimapan", Toast.LENGTH_SHORT).show();

                }else {
                        sessionMinuteBefore.saveSPString(sessionMinuteBefore.SP_ALARM_TIME, "OFF");
                        Toast.makeText(Setting_Activity.this, "jadwal Tidak ditemukan cek koneksi", Toast.LENGTH_SHORT).show();
                        AlarmSchedCancel();
                }
                Intent intent=new Intent(Setting_Activity.this,Setting_Activity.class);
                startActivity(intent);
                mDialog.dismiss();
//                sessionMinuteBefore.saveSPInt(SessionMinuteBefore.SP_ALARM_Active,ActivePosition);

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

    public void AlarmSchedBefMinut(int beforeMinut){

        for (int i=0;i<mAlarmSchedPerminutes.size();i++){
            notifId=1000020+i;
            TimeStr=mAlarmSchedPerminutes.get(i).getStart_time();
            dayNumber=mAlarmSchedPerminutes.get(i).getDay_number();
            mapelName=mAlarmSchedPerminutes.get(i).getMapel_name();
            mapelId=mAlarmSchedPerminutes.get(i).getSchedule_id();
            startHours=Integer.parseInt(TimeStr.substring(0,2));
            startMinute=Integer.parseInt(TimeStr.substring(3,5));
            if(beforeMinut<60){

                startMinute=startMinute-beforeMinut;
                minutSave=Math.abs(startMinute);

                if(startMinute<0){
                    minutSave=60-minutSave;
                    hoursSave=startHours-1;
                    startMinute=minutSave;
                    startHours=hoursSave;
                }
                startMinute=minutSave;

            }else {
                startHours=(startHours-1);
            }


//            saveNotification(dayNumber,startHours,startMinute,notifId,mapelId,i);

        }
        mAlarmSchedPerminutes.clear();
        clearDataSession();
    }

    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("minutebeforesched",  Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        mAlarmSchedPerminutes.clear();

    }
    public void saveNotification(int week,int hour,int minut,int idNotif,int idSched,int i){

        System.err.println("hour :"+hour+" minut:"+minut+" week:"+week+" id"+idSched+" notifId"+idNotif);
        Calendar calSet = Calendar.getInstance();
        if(week==2){
            weekday=Calendar.MONDAY;
        }if (week==3){
            weekday=Calendar.TUESDAY;
        }if(week==6){
            weekday=Calendar.FRIDAY;
        }

        calSet.set(Calendar.DAY_OF_WEEK, weekday);
        calSet.set(Calendar.HOUR_OF_DAY, hour);
        calSet.set(Calendar.MINUTE, minut);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);
        startAlarm(calSet,idNotif,idSched,i);


    }

    private void startAlarm(Calendar c,int notifId1,int idSched,int i) {
        AlarmManager [] alarmManager=new AlarmManager[i];
         alarmManager[i] = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(this, AlertReceiverMinuteBefore2.class);
            intent.putExtra("NOTIFID",""+ notifId1);
            intent.putExtra("SchedlID", ""+idSched);
            intent.putExtra("MapelName", ""+mapelName);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
            alarmManager[i].setRepeating(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(),  7 * 24 * 3600 * 1000, pendingIntent);
        }else{
            Intent intent = new Intent(this, AlertReceiverMinuteBefore.class);
            intent.putExtra("NOTIFID",""+ notifId1);
            intent.putExtra("SchedlID", ""+idSched);
            intent.putExtra("MapelName", ""+mapelName);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
            alarmManager[i].setRepeating(AlarmManager.RTC_WAKEUP,
                    c.getTimeInMillis(),  7 * 24 * 3600 * 1000, pendingIntent);
        }

    }

    private void cancelAlarm(int notifId1) {
        System.err.println("idcancel"+notifId1);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(this, AlertReceiverMinuteBefore2.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(Setting_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();

        }else{
            Intent intent = new Intent(this, AlertReceiverMinuteBefore.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, notifId1, intent, 0);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(Setting_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();

        }

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
            sessionDetailSchedule.saveSPInt(SessionDetailSchedule.SP_RELOADS,1);
            startActivity(new Intent(Setting_Activity.this, Home_activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {
            Toast.makeText(Setting_Activity.this, "Setting", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_homework) {
            sessionDetailHomeWork.saveSPInt(SessionDetailHomeWork.SP_RELOADH,1);
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


    }

}
