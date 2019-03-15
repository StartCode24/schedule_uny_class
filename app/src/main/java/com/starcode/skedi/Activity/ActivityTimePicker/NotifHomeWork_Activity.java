package com.starcode.skedi.Activity.ActivityTimePicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.starcode.skedi.R;

public class NotifHomeWork_Activity extends AppCompatActivity {
    private TextView tvAnswerReceiveText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_home_work_);
        tvAnswerReceiveText = (TextView) findViewById(R.id.tvAnswerReceiveText);
//        Log.d("Main", getIntent().getAction());
        tvAnswerReceiveText.setText("treeee");
    }
}
