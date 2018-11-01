package com.starcode.schedule_uny.Activity;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.starcode.schedule_uny.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingProfile_Activity extends AppCompatActivity {

    @BindView(R.id.img_settingProfile)
    ImageView img_settingProfile;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.appbar)
    AppBarLayout appBarSettingProfile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__profile);
        ButterKnife.bind(this);
        View view = findViewById(R.id.activity_profil_setting);
        initCollapsingToolbar(view);

    }

    private void initCollapsingToolbar(View view){
        collapsingToolbarLayout.setTitle("");
        appBarSettingProfile.setExpanded(true);

        appBarSettingProfile.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange= -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange==-1){
                    scrollRange=appBarLayout.getTotalScrollRange();
                }
                if (scrollRange+verticalOffset==0){
                    collapsingToolbarLayout.setVisibility(View.VISIBLE);
                    isShow=true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow=false;
                }
            }
        });
    }
}
