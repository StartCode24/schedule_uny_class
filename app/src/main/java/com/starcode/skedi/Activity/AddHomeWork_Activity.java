package com.starcode.skedi.Activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Vibrator;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.reginald.editspinner.EditSpinner;
import com.starcode.skedi.Activity.ActivityTimePicker.NotifHomeWork_Activity;
import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.database.DatabaseHomeWorkHelper;
import com.starcode.skedi.model.AddHomeWorkResponse;
import com.starcode.skedi.model.AllMapelResponse;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.FeedAllMapel.FeedAllMapel;
import com.starcode.skedi.model.IDHomeWorkResponse;
import com.starcode.skedi.session.SessionManager;
import com.starcode.skedi.Receiver.AlertReceiver;
import com.starcode.skedi.utils.AlarmUtil;
import com.starcode.skedi.utils.DateAndTimeUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddHomeWork_Activity extends AppCompatActivity {
    @BindView(R.id.timePickerAlrm)TimePicker timePickerAlrm;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.TvTanggal)TextView TvTanggal;
    @BindView(R.id.EdNote)EditText EdNote;

    @BindView(R.id.EdsMapel)EditSpinner EdsMapel;


    private Calendar calendar;
    Context mContext;
    private SessionManager sessionManager;
    private String waktuMulai="";
    private String waktuAkhir="";
    private String tanggalHomework="";
    private String alarm="";
    private String status="",status2="";
    private String error="";
    private String message="",message2="";
    private String kelasId="";
    private String jurusanId="";
    private String siswaNis="";
    private String note="";
    private String idHomeWork;
    private String mapelName="";
    private int mounth,years,date;
    private int hours=0;
    private int minute=0;
    private int idNotif=0;



    private NotificationCompat.Builder notificationBuilder;
    private int currentNotificationID = 0;
    private NotificationManager notificationManager;
    private Bitmap icon;


    private baseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home_work_);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(AddHomeWork_Activity.this);
        baseApiService = utilsApi.getApiServices();
        mContext = this;
        getIdHomeWork();
        getProfil();
        getMapel();
        timePickerAlrm.setIs24HourView(true);
        icon = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.ic_launcher);

        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                alarm=hour + ":" + minut;
                hours=hour;
                minute=minut;
                waktuMulai=alarm;
                waktuAkhir=(hour+1) + ":" + minut;

            }
        });

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        calendar = Calendar.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddHomeWork_Activity.this,HomeWork_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @OnClick(R.id.LnBTanggal)
    public void TvBtnTanggal(View view){
        DatePickerDialog DatePicker = new DatePickerDialog(AddHomeWork_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker DatePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                TvTanggal.setText(DateAndTimeUtil.toStringReadableDate(calendar));
                tanggalHomework=DateAndTimeUtil.toStringDate(calendar);
                mounth=month;
                date=dayOfMonth;
                years=year;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker.show();
    }



    private void getMapel(){
        retrofit2.Call<AllMapelResponse> call=baseApiService.getAllMapel();
        call.enqueue(new Callback<AllMapelResponse>() {
            @Override
            public void onResponse(retrofit2.Call<AllMapelResponse> call, Response<AllMapelResponse> response) {
                status=response.body().getAuth_Mapel().getStatus();
                if (status.equals("200")){
                    List<FeedAllMapel> allMapel=response.body().getAuth_Mapel().getData().getMapel();
                    ArrayList<String> MapelArray=new ArrayList<String>();
                    for (int i=0;i<allMapel.size();i++){
                        MapelArray.add(allMapel.get(i).getMapelName());
                    }
                    ListAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item,MapelArray);
                    EdsMapel.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AllMapelResponse> call, Throwable t) {

            }
        });
    }

    //    get Profil
    private void getProfil() {
        retrofit2.Call<DataProfilResponse> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(retrofit2.Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()) {
                    status = response.body().getAuth_user().getStatus();
                    if (status.equals("200")) {
                        kelasId = response.body().getAuth_user().getData().getKelas_id();
                        jurusanId = response.body().getAuth_user().getData().getJurusan_id();
                        siswaNis=response.body().getAuth_user().getData().getSiswa_nik();
                    } else {
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(AddHomeWork_Activity.this, Main_Activity.class).
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<DataProfilResponse> call, Throwable t) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_homework, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menuSimpan:
                validateInput();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void validateInput(){
        note=EdNote.getText().toString();
        mapelName=EdsMapel.getText().toString();
        if(tanggalHomework.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Tanggal Pengingat",Toast.LENGTH_SHORT).show();
        }else if(waktuMulai.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Waktu Mulai Jam Pelajaran",Toast.LENGTH_SHORT).show();
        }else if(waktuAkhir.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Waktu Akhir Jam Pelajaran",Toast.LENGTH_SHORT).show();
        }else if (alarm.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Jam Pengingat",Toast.LENGTH_SHORT).show();
        }else if(note.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali catatan Pengingat",Toast.LENGTH_SHORT).show();
        }else if(mapelName.equals("")){
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Mata Pelajaran Pengingat",Toast.LENGTH_SHORT).show();
        }else if(idHomeWork.isEmpty()) {
            Toast.makeText(AddHomeWork_Activity.this,"Cek Kembali Jaringan Internet Anda id Taks Tidak ditemukan",Toast.LENGTH_SHORT).show();
        }else {
            InputHomework();
            saveNotification();

        }

    }

    public void getIdHomeWork(){
        Call<IDHomeWorkResponse> call=baseApiService.getIdHomeWork();
        call.enqueue(new Callback<IDHomeWorkResponse>() {
            @Override
            public void onResponse(Call<IDHomeWorkResponse> call, Response<IDHomeWorkResponse> response) {
                if(response.isSuccessful()){
                    status2=response.body().getAuth_idHomeWork().getStatus();
                    message2=response.body().getAuth_idHomeWork().getMessage();
                    if(status2.equals("200")){
                        idHomeWork=response.body().getAuth_idHomeWork().getData().getIdHomeWork();
                        idNotif=Integer.parseInt(idHomeWork);
                    }else {
                        Toast.makeText(AddHomeWork_Activity.this,"Cek keneksi "+message2,Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<IDHomeWorkResponse> call, Throwable t) {

            }
        });
    }

    private void InputHomework(){
        if(!idHomeWork.isEmpty()){
        retrofit2.Call<AddHomeWorkResponse> call=baseApiService.AddHomeWork(idHomeWork,mapelName,note,tanggalHomework,
                waktuMulai,waktuAkhir,alarm,kelasId,jurusanId,siswaNis);

        call.enqueue(new Callback<AddHomeWorkResponse>() {
            @Override
            public void onResponse(retrofit2.Call<AddHomeWorkResponse> call, Response<AddHomeWorkResponse> response) {
                if(response.isSuccessful()){
                error=response.body().getAuth_AddHomework().getError();
                message=response.body().getAuth_AddHomework().getMessage();
                if(error.equals("200")){
                    Toast.makeText(AddHomeWork_Activity.this,"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddHomeWork_Activity.this,HomeWork_Activity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(AddHomeWork_Activity.this,""+message,Toast.LENGTH_SHORT).show();
                }
                }else {
                    Toast.makeText(AddHomeWork_Activity.this,"Data Gagal Disimpan Cek Koneksi",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddHomeWork_Activity.this,HomeWork_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AddHomeWorkResponse> call, Throwable t) {
                Toast.makeText(AddHomeWork_Activity.this,"Cek Koneksi Internet Anda",Toast.LENGTH_SHORT).show();
            }
        });
        }else {
            Toast.makeText(AddHomeWork_Activity.this,"Cek Koneksi Internet Anda",Toast.LENGTH_SHORT).show();
        }
    }




    public void saveNotification() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.set(Calendar.YEAR, years);
        c.set(Calendar.MONTH, mounth);
        c.set(Calendar.DAY_OF_MONTH, date);
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        startAlarm(c);

    }


    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT).format(c.getTime());

        Toast.makeText(AddHomeWork_Activity.this,"update Alarrm",Toast.LENGTH_LONG).show();
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("NOTIF_ID",""+ idNotif);
        intent.putExtra("NOTIF_MAPEL",""+ mapelName);
        intent.putExtra("NOTIF_NOTE",""+ note);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, idNotif, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, -1, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(AddHomeWork_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();
    }


}
