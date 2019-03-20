package com.starcode.skedi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starcode.skedi.R;
import com.starcode.skedi.apiHolder.utilsApi;
import com.starcode.skedi.model.DataProfilResponse;
import com.starcode.skedi.model.EditUserProfile;
import com.starcode.skedi.session.SessionDetailHomeWork;
import com.starcode.skedi.session.SessionDetailSchedule;
import com.starcode.skedi.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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


    @BindView(R.id.nav_view)
    NavigationView navigationView;

    public static String alamat, passwordLama, passwordBaru, nama,
            jurusan, kelas, nik, idSiswa, note, kelasId, JurusanId, passwordLama1;
    Dialog mDialog;

    TextView txtclose;
    EditText edAlamat;
    Button btnEditAlamat;
    TextInputLayout TiName, TiAlamat, TipasswordLama, TiPasswordBaru;

    EditText edNama;
    Button btnEdName;


    Button btnEdPassword;
    EditText edPasswrdLama, edPasswrdBaru;

    private com.starcode.skedi.apiHolder.baseApiService baseApiService;
    private static String status, status2;
    private static String name;

    private ImageView imageViewProfil;
    private TextView tvName;
    private SessionManager sessionManager;
    private SessionDetailHomeWork sessionDetailHomeWork;
    private SessionDetailSchedule sessionDetailSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(Profile_activity.this);
        sessionDetailSchedule = new SessionDetailSchedule(this);
        sessionDetailHomeWork =new SessionDetailHomeWork(this);
        mDialog = new Dialog(this);
        getProfil();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //  validasi password Lama
    private boolean validatePasswordLama() {
        if (edPasswrdLama.getText().toString().trim().isEmpty()) {
            TipasswordLama.setError("Password Tidak Boleh Kosong");
            Toast.makeText(Profile_activity.this, "Masukan Password Yang Benar!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(Profile_activity.this, "Masukan Password Yang Benar!", Toast.LENGTH_SHORT).show();
            RequestFocus(edPasswrdBaru);
            return false;
        } else {
            TiPasswordBaru.setErrorEnabled(false);
        }

        return true;
    }


    private void RequestFocus(View view) {
        if (view.requestFocus())
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

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
                    collapsingToolbarLayout.setTitle("");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @OnClick(R.id.LnPassword)
    void btnPassword() {

        mDialog.setContentView(R.layout.popup_editpassword);
        txtclose = (TextView) mDialog.findViewById(R.id.txtclose);
        edPasswrdLama = (EditText) mDialog.findViewById(R.id.EdPasswordLama);
        edPasswrdBaru = (EditText) mDialog.findViewById(R.id.EdPasswordBaru);
        TipasswordLama = (TextInputLayout) mDialog.findViewById(R.id.TI_passwordLama);
        TiPasswordBaru = (TextInputLayout) mDialog.findViewById(R.id.TI_passwordBaru);
        txtclose.setText("X");
        btnEdPassword = (Button) mDialog.findViewById(R.id.btnEditPassword);

        btnEdPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatePasswordLama()) {
                    return;
                } else if (!validatePasswordBaru()) {
                    return;
                } else {
                    passwordLama1 = edPasswrdLama.getText().toString();
                    passwordBaru = edPasswrdBaru.getText().toString();
                    if (!passwordLama1.equals(passwordLama)) {
                        Toast.makeText(Profile_activity.this, "ubah password siswa gagal", Toast.LENGTH_SHORT).show();
                        mDialog.dismiss();
                    } else {
                        Call<EditUserProfile> call = baseApiService.editUserProfile(
                                idSiswa, nik, nama,
                                alamat, kelasId, JurusanId,
                                passwordBaru, "siswa"
                        );

                        call.enqueue(new Callback<EditUserProfile>() {
                            @Override
                            public void onResponse(Call<EditUserProfile> call, Response<EditUserProfile> response) {
                                if (response.isSuccessful()) {
                                    status2 = response.body().getAuth_update_siswa().getStatus();

                                    if (status2.equals("200")) {
                                        Toast.makeText(Profile_activity.this, "Ubah password siswa berhasil", Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                        finish();
                                        startActivity(getIntent());
                                    } else {
                                        Toast.makeText(Profile_activity.this, "Ubah password siswa gagal", Toast.LENGTH_SHORT).show();
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

    private void getProfil() {
        Call<DataProfilResponse> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAuthorization());
        call.enqueue(new Callback<DataProfilResponse>() {
            @Override
            public void onResponse(Call<DataProfilResponse> call, Response<DataProfilResponse> response) {
                if (response.isSuccessful()) {
                    status = response.body().getAuth_user().getStatus();
                    if (status.equals("200")) {
                        nama = response.body().getAuth_user().getData().getSiswa_name();
                        alamat = response.body().getAuth_user().getData().getSiswa_alamat();
                        jurusan = response.body().getAuth_user().getData().getSiswa_jurusan();
                        nik = response.body().getAuth_user().getData().getSiswa_nis();
                        kelas = response.body().getAuth_user().getData().getSiswa_kelas();
                        idSiswa = response.body().getAuth_user().getData().getSiswa_id();
                        passwordLama = response.body().getAuth_user().getData().getSiswa_password();
                        note = response.body().getAuth_user().getData().getSiswa_note();
                        kelasId = response.body().getAuth_user().getData().getKelas_id();
                        JurusanId = response.body().getAuth_user().getData().getJurusan_id();

                        tvNama.setText(nama);
                        tvJurusan.setText(jurusan);
                        tvNik.setText(nik);
                        tvAlamat.setText(alamat);
                        tvKelas.setText(kelas);
                        tvPassword.setText("*************");
                        initComponentNavHeader();
                    } else {
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
                        startActivity(new Intent(Profile_activity.this, Main_Activity.class).
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
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
            startActivity(new Intent(Profile_activity.this, Home_activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(Profile_activity.this, Setting_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_homework) {
            sessionDetailHomeWork.saveSPInt(SessionDetailHomeWork.SP_RELOADH,1);
            startActivity(new Intent(Profile_activity.this, HomeWork_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            Toast.makeText(Setting_Activity.this,"PR",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(Profile_activity.this, Main_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(Profile_activity.this, About_Activity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_Profile) {
            Toast.makeText(Profile_activity.this, "Profile", Toast.LENGTH_SHORT).show();
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

        tvName.setText(nama);
        tvJurusan.setText(jurusan);

    }
}
