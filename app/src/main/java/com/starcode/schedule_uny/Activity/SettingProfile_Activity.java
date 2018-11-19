package com.starcode.schedule_uny.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starcode.schedule_uny.R;
import com.starcode.schedule_uny.apiHolder.baseApiService;
import com.starcode.schedule_uny.apiHolder.utilsApi;
import com.starcode.schedule_uny.model.DataProfilResponse;
import com.starcode.schedule_uny.model.EditUserProfile;
import com.starcode.schedule_uny.model.LoginUserResponse;
import com.starcode.schedule_uny.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingProfile_Activity extends AppCompatActivity {

    @BindView(R.id.appbar)
            AppBarLayout appBarLayout;
    @BindView(R.id.toolbarDetProfil)
            Toolbar toolbarDetProfil;
    @BindView(R.id.image_backdropDetProduct)
            ImageView backDropDetProduct;
    @BindView(R.id.collapsing_toolbar)
            CollapsingToolbarLayout collapsingToolbarLayout;


    @BindView(R.id.tvNik)
            TextView tvNik;
    @BindView(R.id.tvNama)
            TextView tvNama;
    @BindView(R.id.tvJurusan)
            TextView tvJurusan;
    @BindView(R.id.tvKelas)
            TextView tvKelas;
    @BindView(R.id.tvAlamat)
            TextView tvAlamat;
    @BindView(R.id.tvPassword)
            TextView tvPassword;

    private SessionManager sessionManager;
    private com.starcode.schedule_uny.apiHolder.baseApiService baseApiService;
    private static String status;
    public static String alamat,passwordLama,passwordBaru,nama,
            jurusan,kelas,nik,idSiswa,note,kelasId,JurusanId,passwordLama1;
    Dialog mDialog;

    TextView txtclose;
    EditText edAlamat;
    Button btnEditAlamat;
    TextInputLayout TiName,TiAlamat,TipasswordLama,TiPasswordBaru;

    EditText edNama;
    Button btnEdName;


    Button btnEdPassword;
    EditText edPasswrdLama,edPasswrdBaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__profile);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(SettingProfile_Activity.this);
        baseApiService= utilsApi.getApiServices();
        mDialog= new Dialog(this);
        getProfil();
        setSupportActionBar(toolbarDetProfil);
        initCollapsingToolbar();


//      action back
        ActionBar menuBack= getSupportActionBar();
        menuBack.setDisplayHomeAsUpEnabled(true);
        menuBack.setDisplayShowHomeEnabled(true);

        toolbarDetProfil.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Setting_Activity.class));
            }
        });

    }

    private void getProfil(){
        Call<DataProfilResponse> call= baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()){
                    status=response.body().getAuth_user().getStatus();
                    if (status.equals("200")){

                        nama=response.body().getAuth_user().getData().getSiswa_name();
                        alamat=response.body().getAuth_user().getData().getSiswa_alamat();
                        jurusan=response.body().getAuth_user().getData().getSiswa_jurusan();
                        nik=response.body().getAuth_user().getData().getSiswa_nik();
                        kelas=response.body().getAuth_user().getData().getSiswa_kelas();
                        idSiswa=response.body().getAuth_user().getData().getSiswa_id();
                        passwordLama=response.body().getAuth_user().getData().getSiswa_password();
                        note=response.body().getAuth_user().getData().getSiswa_note();
                        kelasId=response.body().getAuth_user().getData().getKelas_id();
                        JurusanId=response.body().getAuth_user().getData().getJurusan_id();

                        tvNama.setText(nama);
                        tvJurusan.setText(jurusan);
                        tvNik.setText(nik);
                        tvAlamat.setText(alamat);
                        tvKelas.setText(kelas);
                        tvPassword.setText("*************");

                    }else{
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(SettingProfile_Activity.this, Main_Activity.class).
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


    @OnClick(R.id.LnNama)
    void btnNama(){

        mDialog.setContentView(R.layout.popup_editname);
        txtclose =(TextView) mDialog.findViewById(R.id.txtclose);
        txtclose.setText("X");
        edNama=(EditText) mDialog.findViewById(R.id.EdNama);
        TiName=(TextInputLayout)mDialog.findViewById(R.id.TI_Nama);
        edNama.setText(nama);

        btnEdName = (Button) mDialog.findViewById(R.id.btnEditName);
        btnEdName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(!validateName()){
                 return;
             }else {
                nama=edNama.getText().toString();
                 Call<EditUserProfile> call= baseApiService.editUserProfile(
                         idSiswa,nik,nama,
                         alamat,kelasId,JurusanId,
                         passwordLama,note
                         );

                 call.enqueue(new Callback<EditUserProfile>() {
                     @Override
                     public void onResponse(Call<EditUserProfile> call, Response<EditUserProfile> response) {
                         if (response.isSuccessful()){
                             status=response.body().getAuth_update_siswa().getStatus();

                             if(status.equals("200")){
                                Toast.makeText(SettingProfile_Activity.this,"Ubah nama siswa berhasil",Toast.LENGTH_SHORT).show();
                                mDialog.dismiss();
                                 finish();
                                 startActivity(getIntent());
                             }else {
                                 Toast.makeText(SettingProfile_Activity.this,"Ubah nama siswa gagal",Toast.LENGTH_SHORT).show();
                                 mDialog.dismiss();
                                 finish();
                                 startActivity(getIntent());
                             }
                         }

                     }

                     @Override
                     public void onFailure(Call<EditUserProfile> call, Throwable t) {

                     }
                 });
             }
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.LnAlamat)
    void btnAlamat(){

        mDialog.setContentView(R.layout.popup_editalamat);
        txtclose =(TextView) mDialog.findViewById(R.id.txtclose);
        edAlamat=(EditText) mDialog.findViewById(R.id.edAlamat);
        edAlamat.setText(alamat);
        txtclose.setText("X");
        btnEditAlamat = (Button) mDialog.findViewById(R.id.btnEditAlamat);
        btnEditAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamat=edAlamat.getText().toString();
                if(alamat.isEmpty()){
                    Toast.makeText(SettingProfile_Activity.this,"alamat tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }else {
                    Call<EditUserProfile> call= baseApiService.editUserProfile(
                            idSiswa,nik,nama,
                            alamat,kelasId,JurusanId,
                            passwordLama,note
                    );

                    call.enqueue(new Callback<EditUserProfile>() {
                        @Override
                        public void onResponse(Call<EditUserProfile> call, Response<EditUserProfile> response) {
                            if (response.isSuccessful()){
                                status=response.body().getAuth_update_siswa().getStatus();

                                if(status.equals("200")){
                                    Toast.makeText(SettingProfile_Activity.this,"Ubah alamat siswa berhasil",Toast.LENGTH_SHORT).show();
                                    mDialog.dismiss();
                                    finish();
                                    startActivity(getIntent());
                                }else {
                                    Toast.makeText(SettingProfile_Activity.this,"Ubah alamat siswa gagal",Toast.LENGTH_SHORT).show();
                                    mDialog.dismiss();
                                    finish();
                                    startActivity(getIntent());
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<EditUserProfile> call, Throwable t) {

                        }
                    });
                }
            }
        });
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }

    @OnClick(R.id.LnPassword)
    void btnPassword(){

        mDialog.setContentView(R.layout.popup_editpassword);
        txtclose =(TextView) mDialog.findViewById(R.id.txtclose);
        edPasswrdLama=(EditText) mDialog.findViewById(R.id.EdPasswordLama);
        edPasswrdBaru=(EditText)mDialog.findViewById(R.id.EdPasswordBaru);
        TipasswordLama=(TextInputLayout)mDialog.findViewById(R.id.TI_passwordLama);
        TiPasswordBaru=(TextInputLayout)mDialog.findViewById(R.id.TI_passwordBaru);
        txtclose.setText("X");
        btnEdPassword = (Button) mDialog.findViewById(R.id.btnEditPassword);

        btnEdPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validatePasswordLama()){
                    return;
                }else if(!validatePasswordBaru()){
                    return;
                }else {
                    passwordLama1 = edPasswrdLama.getText().toString();
                    passwordBaru = edPasswrdBaru.getText().toString();
                    if (!passwordLama1.equals(passwordLama)) {
                        Toast.makeText(SettingProfile_Activity.this, "ubah password siswa gagal", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else {
                        Call<EditUserProfile> call = baseApiService.editUserProfile(
                                idSiswa, nik, nama,
                                alamat, kelasId, JurusanId,
                                passwordBaru, note
                        );

                        call.enqueue(new Callback<EditUserProfile>() {
                            @Override
                            public void onResponse(Call<EditUserProfile> call, Response<EditUserProfile> response) {
                                if (response.isSuccessful()) {
                                    status = response.body().getAuth_update_siswa().getStatus();

                                    if (status.equals("200")) {
                                        Toast.makeText(SettingProfile_Activity.this, "Ubah password siswa berhasil", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                        finish();
                                        startActivity(getIntent());
                                    } else {
                                        Toast.makeText(SettingProfile_Activity.this, "Ubah password siswa gagal", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                        finish();
                                        startActivity(getIntent());
                                    }
                                }

                            }

                            @Override
                            public void onFailure(Call<EditUserProfile> call, Throwable t) {

                            }
                        });
                    }
                }
            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initCollapsingToolbar() {

        collapsingToolbarLayout.setTitle(" ");
        appBarLayout.setExpanded(true);

// hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Setting Profile");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }




    //  validasi nama
    private boolean validateName() {
        String name= edNama.getText().toString().trim();

        if (name.isEmpty()) {
            TiName.setError("Nama Tidak Boleh Kosong");
            Toast.makeText(SettingProfile_Activity.this,"Nama Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
            RequestFocus(edNama);
            return false;
        } else {
            TiName.setErrorEnabled(false);
        }

        return true;
    }
    //  validasi password Lama
    private boolean validatePasswordLama() {
        if (edPasswrdLama.getText().toString().trim().isEmpty()) {
            TipasswordLama.setError("Password Tidak Boleh Kosong");
            Toast.makeText(SettingProfile_Activity.this,"Masukan Password Yang Benar!",Toast.LENGTH_SHORT).show();
            RequestFocus(edPasswrdLama);
            return false;
        } else {
            TipasswordLama.setErrorEnabled(false);
        }

        return true;
    }

    //  validasi password Baru
    private boolean validatePasswordBaru() {
        if (edPasswrdBaru.getText().toString().trim().isEmpty()) {
            TiPasswordBaru.setError("Password Tidak Boleh Kosong");
            Toast.makeText(SettingProfile_Activity.this,"Masukan Password Yang Benar!",Toast.LENGTH_SHORT).show();
            RequestFocus(edPasswrdBaru);
            return false;
        } else {
            TiPasswordBaru.setErrorEnabled(false);
        }

        return true;
    }


    private  void RequestFocus(View view){
        if (view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }

}
