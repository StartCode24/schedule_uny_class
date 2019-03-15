package com.starcode.skedi.Activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.reginald.editspinner.EditSpinner;
import com.starcode.skedi.R;
import com.starcode.skedi.Receiver.AlertReceiver;
import com.starcode.skedi.apiHolder.baseApiService;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.AllMapelResponse;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.EditHomeWorkResponse;
import com.starcode.skedi.model.FeedAllMapel.FeedAllMapel;
import com.starcode.skedi.model.SearchHomeWorkResponse;
import com.starcode.skedi.session.SessionDetailHomeWork;
import com.starcode.skedi.session.SessionManager;
import com.starcode.skedi.utils.DateAndTimeUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditHomework_Activity extends AppCompatActivity {
    @BindView(R.id.timePickerAlrm)TimePicker timePickerAlrm;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.TvTanggal)TextView TvTanggal;
    @BindView(R.id.EdNote)EditText EdNote;
    @BindView(R.id.TvPeringatan)TextView TvPeringatan;
    @BindView(R.id.EdsMapel)EditSpinner EdsMapel;
    @BindView(R.id.ChkTugas)CheckBox ChekTugas;


    private String waktuMulai="";
    private String waktuAkhir="";
    private String tanggalHomework="";
    private String alarm="";
    private String status="";
    private String error="";
    private String message="";
    private String kelasId="";
    private String jurusanId="";
    private String siswaNis="";
    private String note="";
    private String mapelName="";
    private int idHomeWork;
    private int mounth,years,date;
    private int hours=0;
    private int minute=0;
    private int idNotif=0;
    private int beforeMinut=0;
    private int minutSave,hoursSave;
    private int homework_detail=0;
    Dialog mDialog;

    private com.starcode.skedi.apiHolder.baseApiService baseApiService;
    private Calendar calendar;
    Context mContext;
    private SessionManager sessionManager;
    private SessionDetailHomeWork sessionDetailHomeWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework_);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(EditHomework_Activity.this);
        sessionDetailHomeWork =new SessionDetailHomeWork(EditHomework_Activity.this);
        baseApiService = utilsApi.getApiServices();
        idHomeWork=(int) sessionDetailHomeWork.getSpIdHomeWork();
        idNotif=idHomeWork;
        mContext = this;
        mDialog = new Dialog(this);
        getProfil();

        DetailHomeWork();
        timePickerAlrm.setIs24HourView(true);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        calendar = Calendar.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditHomework_Activity.this,DetailHomeWork_activity.class);
                startActivity(intent);
                finish();
            }
        });
        timePickerAlrm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minut) {
                alarm=hour + ":" + minut;
                sessionManager.saveSPInt(SessionManager.SP_HOURS,hour);
                sessionManager.saveSPInt(SessionManager.SP_MINUTE,minut);
                hours=sessionManager.getSpHours();
                minute=sessionManager.getMinute();
                waktuMulai=alarm;
                waktuAkhir=(hour+1) + ":" + minut;

            }
        });

    }

    @OnClick(R.id.LnBTanggal)
    public void TvBtnTanggal(View view){
        DatePickerDialog DatePicker = new DatePickerDialog(EditHomework_Activity.this, R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker DatePicker, int year, int month, int dayOfMonth) {
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
    @OnClick(R.id.LnBTPeringatan)
    public void LnBtPeringatan(View view){
        mDialog.setContentView(R.layout.popup_minut);
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
                TvPeringatan.setText(radioMinutButton.getText().toString());
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }



    private void getMapel(){
        retrofit2.Call<AllMapelResponse> call=baseApiService.getAllMapel(kelasId,jurusanId);
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
                        siswaNis=response.body().getAuth_user().getData().getSiswa_nis();
                        getMapel();
                    } else {
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(EditHomework_Activity.this, Main_Activity.class).
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
                validateInputUpdate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void validateInputUpdate(){
        note=EdNote.getText().toString();
        mapelName=EdsMapel.getText().toString();
        if(ChekTugas.isChecked())homework_detail=1;
        else homework_detail=0;
        if(tanggalHomework.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali Tanggal Pengingat",Toast.LENGTH_SHORT).show();
        }else if(waktuMulai.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali Waktu Mulai Jam Pelajaran",Toast.LENGTH_SHORT).show();
        }else if(waktuAkhir.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali Waktu Akhir Jam Pelajaran",Toast.LENGTH_SHORT).show();
        }else if (alarm.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali Jam Pengingat",Toast.LENGTH_SHORT).show();
        }else if(note.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali catatan Pengingat",Toast.LENGTH_SHORT).show();
        }else if(mapelName.equals("")){
            Toast.makeText(EditHomework_Activity.this,"Cek Kembali Mata Pelajaran Pengingat",Toast.LENGTH_SHORT).show();
        }else {
            hours=sessionManager.getSpHours();
            minute=sessionManager.getMinute();
            if(beforeMinut<60){
//            if(minute<=5){
                minute=minute-beforeMinut;
                minutSave=Math.abs(minute);

                if(minute<0){
                    minutSave=60-minutSave;
                    hoursSave=hours-1;
                    minute=minutSave;
                    hours=hoursSave;
                }
                minute=minutSave;
//            }
                alarm=hours + ":" + minutSave;
            }else {
                hours=(hours-1);
                alarm=hours + ":" + minute;
            }
            updateHomework();
            saveNotification();
            clearDataSession();
        }
    }
    public void DetailHomeWork(){
        idHomeWork=(int) sessionDetailHomeWork.getSpIdHomeWork();
        retrofit2.Call<SearchHomeWorkResponse> call=baseApiService.SearchHomeWork(idHomeWork);
        call.enqueue(new Callback<SearchHomeWorkResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SearchHomeWorkResponse> call, Response<SearchHomeWorkResponse> response) {
                if(response.isSuccessful()){
                    status=response.body().getAuth_SearchHomeWork().getStatus();
                    message=response.body().getAuth_SearchHomeWork().getMessage();
                    if(status.equals("200")){
                        EdsMapel.setText(response.body().getAuth_SearchHomeWork().getData().getMapel_name());
                        TvTanggal.setText(response.body().getAuth_SearchHomeWork().getData().getDateName());
                        EdNote.setText(response.body().getAuth_SearchHomeWork().getData().getNote());
                        waktuMulai=response.body().getAuth_SearchHomeWork().getData().getStart_time();
                        waktuAkhir=response.body().getAuth_SearchHomeWork().getData().getFinish_time();
                        alarm=response.body().getAuth_SearchHomeWork().getData().getAlarm_time();
                        tanggalHomework=response.body().getAuth_SearchHomeWork().getData().getHomework_date();
                        date=response.body().getAuth_SearchHomeWork().getData().getDate();
                        years=response.body().getAuth_SearchHomeWork().getData().getYears();
                        mounth=response.body().getAuth_SearchHomeWork().getData().getMonth();
                        hours=response.body().getAuth_SearchHomeWork().getData().getHours();
                        minute=response.body().getAuth_SearchHomeWork().getData().getMinute();
                        sessionManager.saveSPInt(SessionManager.SP_HOURS,hours);
                        sessionManager.saveSPInt(SessionManager.SP_MINUTE,minute);
                        if(response.body().getAuth_SearchHomeWork().getData().getHomework_detail()==1) ChekTugas.isChecked();

                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<SearchHomeWorkResponse> call, Throwable t) {

            }
        });
    }


    public void updateHomework(){

//        Toast.makeText(EditHomework_Activity.this,""+idHomeWork+mapelName+note+tanggalHomework+
//                waktuMulai+waktuAkhir+alarm+kelasId+jurusanId+siswaNis,Toast.LENGTH_SHORT).show();

        Call<EditHomeWorkResponse> call=baseApiService.UpdateHomeWork(idHomeWork,mapelName,note,tanggalHomework,
                waktuMulai,waktuAkhir,alarm,kelasId,jurusanId,siswaNis,homework_detail,beforeMinut);
        call.enqueue(new Callback<EditHomeWorkResponse>() {
            @Override
            public void onResponse(Call<EditHomeWorkResponse> call, Response<EditHomeWorkResponse> response) {
                if(response.isSuccessful()) {
                    error = response.body().getAuth_UpdateHomework().getError();
                    message = response.body().getAuth_UpdateHomework().getMessage();
                    if (error.equals("200")) {
                        Toast.makeText(EditHomework_Activity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditHomework_Activity.this, HomeWork_Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EditHomework_Activity.this, "" + message, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(EditHomework_Activity.this, "Galgal Tersimpan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditHomeWorkResponse> call, Throwable t) {

            }
        });
    }
    private void clearDataSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences2", MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
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

        Toast.makeText(EditHomework_Activity.this,"update Alarrm",Toast.LENGTH_LONG).show();
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
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(EditHomework_Activity.this,"Cancel Alarrm",Toast.LENGTH_LONG).show();
    }

}
