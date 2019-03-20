package com.starcode.skedi.Receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import com.starcode.skedi.Activity.DetailHomeWork_activity;
import com.starcode.skedi.Activity.Home_activity;
import com.starcode.skedi.R;

import java.util.ArrayList;

public class AlertReceiverDayBefore extends BroadcastReceiver {
    private NotificationCompat.Builder notificationBuilder;
    private int currentNotificationID ;
    private NotificationManager notificationManager;
    private Bitmap icon;
    private String note="",mapel="";
    int importance = NotificationManager.IMPORTANCE_HIGH;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        icon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);

        Bundle bundle = intent.getExtras();
        ArrayList<String> MapelName = (ArrayList<String>) bundle.getStringArrayList("MapelName");
        for (int i=0;i<MapelName.size();i++){
            note ="\n"+MapelName.get(i);
            mapel +=note;
        }


        currentNotificationID=Integer.parseInt(intent.getStringExtra("NOTIFID"));

        notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                .setLargeIcon(icon)
                .setContentTitle("Mapel Hari Depan")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mapel))
                .setPriority(Notification.PRIORITY_MAX)
                .setContentText(mapel);



        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        sendNotification(notificationBuilder.build(),context,currentNotificationID);


    }

    private void sendNotification(Notification notification,Context context, int currentNotificationID) {
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, Home_activity.class);
        notificationIntent.putExtra("NOTIFID",""+ currentNotificationID);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent(contentIntent);
        notification = notificationBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;


        int notificationId = currentNotificationID;
        if (notificationId == Integer.MAX_VALUE - 1)
            notificationId = 0;





        notificationManager.notify(notificationId, notification);
        // notificationManager.cancel(currentNotificationID-1);
    }
}
