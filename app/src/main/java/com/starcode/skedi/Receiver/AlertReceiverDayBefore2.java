package com.starcode.skedi.Receiver;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.starcode.skedi.R;

import java.util.ArrayList;

public class AlertReceiverDayBefore2 extends BroadcastReceiver {
    private NotificationHelperDayBefore mNotificationHelperDayBefore;
    private int currentNotificationID ;
    private String note="",mapel="";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        ArrayList<String> MapelName = (ArrayList<String>) bundle.getStringArrayList("MapelName");
                System.err.println("sizeee"+ MapelName.size());
        for (int i=0;i<MapelName.size();i++){
            note ="\n"+MapelName.get(i);
            mapel +=note;
        }
        currentNotificationID=Integer.parseInt(intent.getStringExtra("NOTIFID"));
        mNotificationHelperDayBefore = new NotificationHelperDayBefore(context);
        Notification.Builder notificationBuilder = null;
        notificationBuilder =
                mNotificationHelperDayBefore.getNotificationDM(
                        "Mapel Hari Depan",mapel);

        if (notificationBuilder != null) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            mNotificationHelperDayBefore.notify(currentNotificationID, notificationBuilder);
        }
//        Toast.makeText(context, "masuk", Toast.LENGTH_SHORT).show();
//        System.err.println("Tolong");
    }
}
