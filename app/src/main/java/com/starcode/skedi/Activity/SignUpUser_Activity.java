package com.starcode.skedi.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.reginald.editspinner.EditSpinner;
import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.AllJurusanResponse;
import com.starcode.skedi.model.AllKelasResponse;
import com.starcode.skedi.model.FeedAllJurusan.FeedAllJurusan;
import com.starcode.skedi.model.FeedAllKelas.FeedAllKelas;
import com.starcode.skedi.model.SignUpUserResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpUser_Activity extends AppCompatActivity {
    @BindView(R.id.rellayS1)
    RelativeLayout rellayS1;
    @BindView(R.id.eds_kelasSiswa)
    EditSpinner eds_kelasSiswa;
//    @BindView(R.id.eds_jurusanSiswa)
//    EditSpinner eds_jurusanSiswa;

    @BindView(R.id.ed_nis)
    EditText ed_nis;
    @BindView(R.id.ed_namaSiswa)
    EditText ed_namaSiswa;
    @BindView(R.id.ed_alamatSiswa)
    EditText ed_alamatSiswa;
    @BindView(R.id.ed_password1)
    EditText ed_password1;
    @BindView(R.id.ed_password2)
    EditText ed_password2;
    @BindView(R.id.showPassword)
    CheckBox showPassword;



    private static final String TAG = "SignUpUser_Activity";
    private String status, status2, status3, message;


    private com.starcode.skedi.apiHolder.baseApiService baseApiService;
    Context mContext;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellayS1.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user_);
        ButterKnife.bind(this);
        mContext = this;
        baseApiService = utilsApi.getApiServices();
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        getKelas();
//        getJurusan();
        eds_kelasSiswa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                closeKeyboard();
                return false;
            }
        });
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean value) {
                if(value){
                    ed_password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ed_password2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    ed_password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ed_password2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

//        eds_jurusanSiswa.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                closeKeyboard();
//                return false;
//            }
//        });


    }


    public void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void getKelas() {
        Call<AllKelasResponse> call = baseApiService.getAllKelas();
        call.enqueue(new Callback<AllKelasResponse>() {
            @Override
            public void onResponse(Call<AllKelasResponse> call, Response<AllKelasResponse> response) {
                status = response.body().getAuth_Kelas().getStatus();
                if (status.equals("200")) {
                    List<FeedAllKelas> allKelas = response.body().getAuth_Kelas().getData().getKelas();
                    ArrayList<String> KelasArray = new ArrayList<String>();
                    for (int i = 0; i < allKelas.size(); i++) {
                        KelasArray.add(allKelas.get(i).getKelas_notasi());
                    }

                    ListAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item,
                            KelasArray);

                    eds_kelasSiswa.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<AllKelasResponse> call, Throwable t) {

            }
        });
    }

    private void getJurusan() {
//        Call<AllJurusanResponse> call2 = baseApiService.getAllJurusan();
//        call2.enqueue(new Callback<AllJurusanResponse>() {
//            @Override
//            public void onResponse(Call<AllJurusanResponse> call, Response<AllJurusanResponse> response) {
//                status2 = response.body().getAuth_Jurusan().getStatus();
//                if (status2.equals("200")) {
//                    List<FeedAllJurusan> allJurusan = response.body().getAuth_Jurusan().getData().getJurusan();
//                    ArrayList<String> JurusanArray = new ArrayList<String>();
//                    for (int i = 0; i < allJurusan.size(); i++) {
//                        JurusanArray.add(allJurusan.get(i).getJurusan_name());
//                    }
//
//                    ListAdapter adapter2 = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item,
//                            JurusanArray);
//                    eds_jurusanSiswa.setAdapter(adapter2);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllJurusanResponse> call, Throwable t) {
//
//            }
//        });
    }

    @OnClick(R.id.btn_Login)
    public void btn_login(View view){
        Intent intent=new Intent(SignUpUser_Activity.this,Main_Activity.class);
        startActivity(intent);
        finish();
    }


    @OnClick(R.id.btn_daftar)
    public void btn_Daftar(View view) {
        closeKeyboard();
        if (ed_nis.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "NIS Harus di isi", Toast.LENGTH_SHORT).show();
        } else if (ed_namaSiswa.getText().toString().isEmpty() ) {
            Toast.makeText(mContext, "Nama Siswa Harus di isi", Toast.LENGTH_SHORT).show();
        } else if (ed_alamatSiswa.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Alamat Siswa Harus di isi", Toast.LENGTH_SHORT).show();
        } else if (eds_kelasSiswa.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Kelas Siswa Harus di isi", Toast.LENGTH_SHORT).show();
        } else if (ed_password1.getText().toString().isEmpty() ) {
            Toast.makeText(mContext, "Password Harus di isi", Toast.LENGTH_SHORT).show();
        } else if (ed_password2.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Konfirmasi Password Harus di isi", Toast.LENGTH_SHORT).show();
        } else {
            Call<SignUpUserResponse> call = baseApiService.SignUpUser(ed_nis.getText().toString(), ed_namaSiswa.getText().toString(),
                    ed_alamatSiswa.getText().toString(), eds_kelasSiswa.getText().toString(),
                    ed_password1.getText().toString(), ed_password2.getText().toString());

            call.enqueue(new Callback<SignUpUserResponse>() {
                @Override
                public void onResponse(Call<SignUpUserResponse> call, Response<SignUpUserResponse> response) {
                    if (response.isSuccessful()) {
                        status3 = response.body().getAuth_SignUp().getStatus();
                        message = response.body().getAuth_SignUp().getMessage();
                        if (status3.equals("true")) {
                            Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpUser_Activity.this, Main_Activity.class).
                                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        } else {
                            Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SignUpUserResponse> call, Throwable t) {

                }
            });
        }
    }
}
