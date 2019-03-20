package com.starcode.skedi.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.starcode.skedi.Adapter.Onboard_Adapter;
import com.starcode.skedi.Adapter.Onboard_item;
import com.starcode.skedi.R;
import com.starcode.skedi.session.SessionOnboard;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Onboard_Activity extends AppCompatActivity {

    private int dotsCount;
    private ImageView[] dots;

    private Onboard_Adapter mAdapter;
    int previous_pos = 0;
    private SessionOnboard sessionOnboard;


    ArrayList<Onboard_item> onBoardItems = new ArrayList<>();

    @BindView(R.id.btn_get_started) Button btn_get_started;
    @BindView(R.id.pager_introduction) ViewPager onboard_pager;
    @BindView(R.id.viewPagerCountDots) LinearLayout pager_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard_);
        ButterKnife.bind(this);
        sessionOnboard=new SessionOnboard(this);
        if (sessionOnboard.getSpSesionOnboard()){
            Intent intent=new Intent(Onboard_Activity.this,Main_Activity.class);
            startActivity(intent);
            finish();
        }

        loadData();

        mAdapter = new Onboard_Adapter(this,onBoardItems);
        onboard_pager.setAdapter(mAdapter);
        onboard_pager.setCurrentItem(0);
        onboard_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // Change the current position intimation

                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(Onboard_Activity.this, R.drawable.non_selected_item_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(Onboard_Activity.this, R.drawable.selected_item_dot));


                int pos=position+1;

                if(pos==dotsCount&&previous_pos==(dotsCount-1))
                    show_animation();
                else if(pos==(dotsCount-1)&&previous_pos==dotsCount)
                    hide_animation();

                previous_pos=pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(Onboard_Activity.this,"Redirect to wherever you want",Toast.LENGTH_LONG).show();
                sessionOnboard.saveSPBoolean(SessionOnboard.SP_ONBOARD, true);
                Intent intent=new Intent(Onboard_Activity.this,Main_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        setUiPageViewController();

    }

    public void loadData()
    {

        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageId = {R.drawable.onboard1, R.drawable.onboard2, R.drawable.onboard3};

        for(int i=0;i<imageId.length;i++)
        {
            Onboard_item item=new Onboard_item();
            item.setImageID(imageId[i]);
            item.setTitle(getResources().getString(header[i]));
            item.setDescription(getResources().getString(desc[i]));

            onBoardItems.add(item);
        }
    }

    // Button bottomUp animation

    public void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        btn_get_started.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                btn_get_started.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();

            }

        });


    }

    // Button Topdown animation

    public void hide_animation()
    {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);

        btn_get_started.startAnimation(hide);

        hide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                btn_get_started.clearAnimation();
                btn_get_started.setVisibility(View.GONE);

            }

        });


    }

    // setup the
    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(Onboard_Activity.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(Onboard_Activity.this, R.drawable.selected_item_dot));
    }
}
