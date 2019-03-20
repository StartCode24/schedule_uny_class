package com.starcode.skedi.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

public class AlertReceiver2 extends BroadcastReceiver {
    private NotificationHelperHomeWork mNotificationHelperHomeWork;
    private int currentNotificationID ;
    private NotificationManager notificationManager;
    private Bitmap icon;
    private String note,mapel;
    @Override
    public void onReceive(Context context, Intent intent) {
        note=intent.getStringExtra("NOTIF_NOTE");
        mapel=intent.getStringExtra("NOTIF_MAPEL");
        currentNotificationID=Integer.parseInt(intent.getStringExtra("NOTIF_ID"));
        mNotificationHelperHomeWork = new NotificationHelperHomeWork(context);
        Notification.Builder notificationBuilder = null;
        notificationBuilder =
                mNotificationHelperHomeWork.getNotificationDM(
                        "PR",mapel,currentNotificationID);

        if (notificationBuilder != null) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            mNotificationHelperHomeWork.notify(currentNotificationID, notificationBuilder);
        }
    }
}
