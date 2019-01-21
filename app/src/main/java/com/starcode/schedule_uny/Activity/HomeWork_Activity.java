package com.starcode.schedule_uny.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.starcode.schedule_uny.Adapter.List.HomeWorkList;
import com.starcode.schedule_uny.R;
import com.starcode.schedule_uny.apiHolder.baseApiService;
import com.starcode.schedule_uny.apiHolder.utilsApi;
import com.starcode.schedule_uny.model.DataProfilResponse;
import com.starcode.schedule_uny.model.FeedSchedule.FeedSchedule;
import com.starcode.schedule_uny.model.ScheduleResponse;
import com.starcode.schedule_uny.session.SessionManager;
import com.starcode.schedule_uny.xample.xample;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeWork_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        WeekView.EventClickListener,MonthLoader.MonthChangeListener,
        WeekView.EventLongPressListener,WeekView.EmptyViewLongPressListener{


    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;

    private SessionManager sessionManager;
    private com.starcode.schedule_uny.apiHolder.baseApiService baseApiService;
    private static String status,status2;
    private static String name;
    private static String jurusan;
    private static String kelas_id;
    private static String jurusan_id;

    ArrayList<HomeWorkList> mHomeWorkLists;

    private ImageView imageViewProfil;
    private TextView tvName;
    private TextView tvJurusan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_);
        ButterKnife.bind(HomeWork_Activity.this);


        loadData();
        sessionManager = new SessionManager(HomeWork_Activity.this);
        setSupportActionBar(toolbar);
        baseApiService= utilsApi.getApiServices();
        getProfil();
//        Toast.makeText(HomeWork_Activity.this,"Size "+ mXample.get(0).getName(),Toast.LENGTH_SHORT).show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mWeekView =(WeekView) findViewById(R.id.HomeWork_weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEmptyViewLongPressListener(this);
        setupDateTimeInterpreter(false);


    }


    //    get Profil
    private void getProfil(){
        Call<DataProfilResponse> call= baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()){
                    status=response.body().getAuth_user().getStatus();
                    if (status.equals("200")){
                        name=response.body().getAuth_user().getData().getSiswa_name();
                        jurusan=response.body().getAuth_user().getData().getSiswa_jurusan();
                        kelas_id=response.body().getAuth_user().getData().getKelas_id();
                        jurusan_id=response.body().getAuth_user().getData().getJurusan_id();
//                        Toast.makeText(HomeWork_Activity.this,"name :"+name+
//                                "\njurusan :"+jurusan,Toast.LENGTH_SHORT).show();
                        initComponentNavHeader();
                    }else{
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(HomeWork_Activity.this, Main_Activity.class).
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
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        clearDataSession();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home_work_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_schedule) {
            clearDataSession();
            startActivity(new Intent(HomeWork_Activity.this, Home_activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {
            clearDataSession();
            startActivity(new Intent(HomeWork_Activity.this, Setting_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }else if (id == R.id.nav_homework) {

            Toast.makeText(HomeWork_Activity.this,"Lihat PR",Toast.LENGTH_SHORT).show();
        }else if(id==R.id.nav_logout){
            clearDataSession();
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(HomeWork_Activity.this, Main_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        schedulee();

    }

    public void initComponentNavHeader(){
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);

        imageViewProfil=headerView.findViewById(R.id.imageViewProfile);
        tvName=headerView.findViewById(R.id.tvName);
        tvJurusan=headerView.findViewById(R.id.tvJurusan);

        tvName.setText(name);
        tvJurusan.setText(jurusan);

        imageViewProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Foto Profile",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 12 ? (hour + 1) + " :00" : (hour == 0 ? "00 :00" : hour + " :00");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }


    @Override
    public List<? extends WeekViewEvent> onMonthChange(final int newYear, final int newMonth) {

        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (mHomeWorkLists.size()>0) {
            for (int i = 0; i < mHomeWorkLists.size(); i++) {
                Calendar endTime;
                WeekViewEvent event;
                Calendar startTime;
                String TimeStr=mHomeWorkLists.get(i).getStart_time();
                String TimeFns=mHomeWorkLists.get(i).getFinish_time();
                String startHours=TimeStr.substring(0,2);
                String startMinute=TimeStr.substring(3,5);
                String FinisHours=TimeFns.substring(0,2);
                String FinisMinute=TimeFns.substring(3,5);


                Toast.makeText(HomeWork_Activity.this," "+newMonth,Toast.LENGTH_LONG).show();
                startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY,Integer.parseInt(startHours) );
                startTime.set(Calendar.MINUTE, Integer.parseInt(startMinute));
                startTime.set(Calendar.MONTH, newMonth-1);
                startTime.set(Calendar.YEAR, newYear);
                startTime.set(Calendar.DATE, mHomeWorkLists.get(i).getDay());
                endTime = (Calendar) startTime.clone();
                endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(FinisHours));
                startTime.set(Calendar.MINUTE, Integer.parseInt(FinisMinute));
                endTime.set(Calendar.MONTH, newMonth - 1);
                endTime.set(Calendar.DATE, mHomeWorkLists.get(i).getDay());
                event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
                event.setColor(getResources().getColor(R.color.event_color_03));
                event.setName(mHomeWorkLists.get(i).getMapel_name()+"\n"+
                        mHomeWorkLists.get(i).getRoom_name());
                events.add(event);
            }
        }

        return events ;
    }


    private void clearDataSession(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    private  void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mHomeWorkLists);
        editor.putString("HomeWork", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("HomeWork", null);
        Type type = new TypeToken<ArrayList<HomeWorkList>>() {}.getType();
        mHomeWorkLists = gson.fromJson(json, type);

        if (mHomeWorkLists == null) {
            mHomeWorkLists = new ArrayList<>();
        }
    }


    private void schedulee(){
        //baseApiService.Schedule(kelas_id,jurusan_id);
        Call<ScheduleResponse> call=baseApiService.Schedule("31","9");
        call.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()){
                    status2=response.body().getAuth_Schedule().getStatus();
                    if (status2.equals("200")){
                        if(mHomeWorkLists.size()==0) {
                            List<FeedSchedule> schedule = response.body().getAuth_Schedule().getData().getSchedule();
                            for (int i = 0; i < schedule.size(); i++) {
                                mHomeWorkLists.add(new HomeWorkList(schedule.get(i).getSchedule_id(),schedule.get(i).getSchedule_date(),
                                        schedule.get(i).getStart_time(),schedule.get(i).getFinish_time(),schedule.get(i).getDay(),
                                        schedule.get(i).getNote(),schedule.get(i).getGuru_id(),schedule.get(i).getGuru_name(),
                                        schedule.get(i).getMapel_id(),schedule.get(i).getMapel_name(),schedule.get(i).getKelas_id(),
                                        schedule.get(i).getKelas_name(),schedule.get(i).getJurusan_id(),schedule.get(i).getJurusan_name(),
                                        schedule.get(i).getRoom_id(),schedule.get(i).getRoom_name()));
                            }
                            saveData();

                            Intent intent=new Intent(HomeWork_Activity.this,HomeWork_Activity.class);
                            startActivity(intent);
                        }else {

                        }
                    }
                }else {
                    Toast.makeText(HomeWork_Activity.this,"Data Tidak ditemukan",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                Toast.makeText(HomeWork_Activity.this,"Cek Koneksi",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }






}
