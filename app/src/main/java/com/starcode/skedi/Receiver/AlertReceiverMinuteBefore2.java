package com.starcode.skedi.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Vibrator;

public class AlertReceiverMinuteBefore2 extends BroadcastReceiver {
    private NotificationHelperMinutBefore mNotificationHelperMinutBefore;
    private int currentNotificationID ;
    private NotificationManager notificationManager;
    private Bitmap icon;
    private String mapelName="";
    private String schedId="";
    @Override
    public void onReceive(Context context, Intent intent) {
        mapelName=intent.getStringExtra("MapelName");
        schedId=intent.getStringExtra("SchedlID");
        currentNotificationID=Integer.parseInt(intent.getStringExtra("NOTIFID"));
        mNotificationHelperMinutBefore = new NotificationHelperMinutBefore(context);
        Notification.Builder notificationBuilder = null;
        notificationBuilder =
                mNotificationHelperMinutBefore.getNotificationDM(
                        "Mapel Selanjutnya ",mapelName,schedId);

        if (notificationBuilder != null) {
            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);
            mNotificationHelperMinutBefore.notify(currentNotificationID, notificationBuilder);
        }
    }
}
