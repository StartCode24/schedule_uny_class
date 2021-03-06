package com.starcode.skedi.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starcode.skedi.Adapter.List.HomeWorkList;
import com.starcode.skedi.Adapter.List.ScheduleList;
import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.FeedSchedule.FeedSchedule;
import com.starcode.skedi.model.ScheduleResponse;
import com.starcode.skedi.session.SessionDetailHomeWork;
import com.starcode.skedi.session.SessionDetailSchedule;
import com.starcode.skedi.session.SessionManager;
import com.starcode.skedi.apiHolder.utilsApi;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        WeekView.EventClickListener, MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;


    private ArrayList<WeekViewEvent> mNewEvents;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fabRefresh)
    FloatingActionButton fabRefresh;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    private SessionManager sessionManager;
    private SessionDetailSchedule sessionDetailSchedule;
    private SessionDetailHomeWork sessionDetailHomeWork;
    private baseApiService baseApiService;
    private static String status, status2;
    private static String name;
    private static String jurusan;
    private static String kelas_id="";
    private static String jurusan_id="";

    private ImageView imageViewProfil;
    private TextView tvName;
    private TextView tvJurusan;
    int colorId;
    private int setRefresh;
    ArrayList<ScheduleList> mScheduleList;
    Context mcContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        ButterKnife.bind(Home_activity.this);
        sessionManager = new SessionManager(Home_activity.this);
        sessionDetailSchedule = new SessionDetailSchedule(Home_activity.this);
        sessionDetailHomeWork =new SessionDetailHomeWork(Home_activity.this);
        baseApiService = utilsApi.getApiServices();

        mcContext = this;
        loadData();
        getProfil();
        setSupportActionBar(toolbar);


        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDataSession();
                sessionDetailSchedule.saveSPInt(SessionDetailSchedule.SP_RELOADS,1);
                Intent intent = new Intent(Home_activity.this, Home_activity.class);
                startActivity(intent);


            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        mWeekView.goToHour(6);
        mWeekView.setNumberOfVisibleDays(7);


        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);
        if(sessionManager.getSpReloadSM()==1){
            Toast.makeText(this, ""+sessionDetailSchedule.getSpReloadS(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Home_activity.this, Home_activity.class);
            startActivity(intent);
            sessionManager.saveSPInt(SessionManager.SP_RELOADSM,0);

        }


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
                        startActivity(new Intent(Home_activity.this, Main_Activity.class).
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

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            Toast.makeText(Home_activity.this, "Lihat Jadwal", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            clearDataSession();
            startActivity(new Intent(Home_activity.this, Setting_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_homework) {
            clearDataSession();
            sessionDetailHomeWork.saveSPInt(SessionDetailHomeWork.SP_RELOADH,1);
            startActivity(new Intent(Home_activity.this, HomeWork_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            //        Toast.makeText(Home_activity.this,"PR",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            clearDataSession();
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(Home_activity.this, Main_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else if (id == R.id.nav_about) {
            clearDataSession();
            startActivity(new Intent(Home_activity.this, About_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_Profile) {
            startActivity(new Intent(Home_activity.this, Profile_activity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initComponentNavHeader() {
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);


        tvName = headerView.findViewById(R.id.tvName);
        tvJurusan = headerView.findViewById(R.id.tvJurusan);

        tvName.setText(name);
        tvJurusan.setText(jurusan);


    }

    @Override
    protected void onResume() {
        super.onResume();
        schedulee();


    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                weekdayNameFormat.setTimeZone(tz);
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d", Locale.getDefault());
                format.setTimeZone(tz);
                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() ;
            }

            @Override
            public String interpretTime(int hour) {
                return   hour > 12 ? (hour + 1) + " :00" : (hour == 0 ? "00 :00" : hour + " :00");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Pelajaran Fisika of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        sessionDetailSchedule.saveSPLong(SessionDetailSchedule.SP_IDSCHEDULE, event.getId());
        String eventName=String.valueOf(event.getName());
        if(eventName.equals("Libur\n")){
            Toast.makeText(this,"Hari Libur",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Click " + eventName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Home_activity.this, DetailSchedule_Activity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getId(), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (mScheduleList.size() > 0) {

            for (int i = 0; i < mScheduleList.size(); i++) {
                Calendar endTime;
                WeekViewEvent event;
                Calendar startTime;
                String TimeStr = mScheduleList.get(i).getStart_time();
                String TimeFns = mScheduleList.get(i).getFinish_time();
                String colorMapel= mScheduleList.get(i).getColor_mapel();


                String startHours = TimeStr.substring(0, 2);
                String startMinute = TimeStr.substring(3, 5);
                String FinisHours = TimeFns.substring(0, 2);
                String FinisMinute = TimeFns.substring(3, 5);
                String monthSched=mScheduleList.get(i).getMonthNow();
                int month=Integer.parseInt(monthSched);
                System.err.println("bulan : "+newMonth +" "+monthSched);
                if(newMonth==month) {
                    startTime = Calendar.getInstance();

                    startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHours));
                    startTime.set(Calendar.MINUTE, Integer.parseInt(startMinute));
                    startTime.set(Calendar.MONTH, newMonth - 1);
                    startTime.set(Calendar.YEAR, newYear);
                    startTime.set(Calendar.DATE, mScheduleList.get(i).getDay_date());
//                startTime.set(Calendar.DAY_OF_WEEK_IN_MONTH,0);
                    endTime = (Calendar) startTime.clone();
                    endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(FinisHours));
                    startTime.set(Calendar.MINUTE, Integer.parseInt(FinisMinute));
                    endTime.set(Calendar.MONTH, newMonth - 1);
                    endTime.set(Calendar.DATE, mScheduleList.get(i).getDay_date());

                    event = new WeekViewEvent(mScheduleList.get(i).getSchedule_id(), getEventTitle(startTime), startTime, endTime);
                    if(colorMapel.equals("green")){
                        event.setColor(getResources().getColor(R.color.green));
                    }else {
                        event.setColor(getResources().getColor(R.color.gray));
                    }
                    event.setName(mScheduleList.get(i).getMapel_name() + "\n" +
                            mScheduleList.get(i).getRoom_name());
                    events.add(event);
                }
            }


        }

        return events;
    }

    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mScheduleList);
        editor.putString("schedule", json);
        editor.apply();
        if(sessionDetailSchedule.getSpReloadS()==1){
//            Toast.makeText(this, ""+sessionDetailSchedule.getSpReloadS(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Home_activity.this, Home_activity.class);
            startActivity(intent);
            sessionDetailSchedule.saveSPInt(SessionDetailSchedule.SP_RELOADS,0);

        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("schedule", null);
        Type type = new TypeToken<ArrayList<ScheduleList>>() {
        }.getType();
        mScheduleList = gson.fromJson(json, type);

        if (mScheduleList == null) {
            mScheduleList = new ArrayList<>();
        }
    }


    private void schedulee() {
        //baseApiService.Schedule(kelas_id,jurusan_id);
        Call<ScheduleResponse> call = baseApiService.Schedule(kelas_id, jurusan_id);
        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()) {
                    status2 = response.body().getAuth_Schedule().getStatus();
                    if (status2.equals("200")) {
                        if (mScheduleList.size() == 0) {
                            List<FeedSchedule> schedule = response.body().getAuth_Schedule().getData().getSchedule();
                            for (int i = 0; i < schedule.size(); i++) {
                                mScheduleList.add(new ScheduleList(schedule.get(i).getSchedule_id(),
                                        schedule.get(i).getStart_time(), schedule.get(i).getFinish_time(), schedule.get(i).getDay_name(), schedule.get(i).getDay_date(),
                                        schedule.get(i).getMonth(),schedule.get(i).getNote(), schedule.get(i).getGuru_id(), schedule.get(i).getGuru_name(),
                                        schedule.get(i).getMapel_id(), schedule.get(i).getMapel_name(), schedule.get(i).getKelas_id(),
                                        schedule.get(i).getKelas_name(), schedule.get(i).getJurusan_id(), schedule.get(i).getJurusan_name(),
                                        schedule.get(i).getRoom_id(), schedule.get(i).getRoom_name(),schedule.get(i).getColor_mapel()));

                            }

                            saveData();

                        } else {

                        }
                    }
                } else {
                    Toast.makeText(Home_activity.this, "Data Tidak ditemukan",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
//                Toast.makeText(Home_activity.this, "Cek Koneksi",
//                        Toast.LENGTH_SHORT).show();
                if (t instanceof IOException) {
                    Toast.makeText(Home_activity.this, "Cek Koneksi", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(Home_activity.this, "Cek Koneksi sangat bermasalah:(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }

            }
        });

    }
}


