package com.starcode.skedi.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    @Override
    public void onReceive(Context context, Intent intent) {
        icon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
//        note=intent.getStringExtra("NOTIF_NOTE");
//        mapel=intent.getStringExtra("NOTIF_MAPEL");
        Bundle bundle = intent.getExtras();
        ArrayList<String> MapelName = (ArrayList<String>) bundle.getStringArrayList("MapelName");
//        for (int i=0;i<MapelName.size();i++){
//            note ="\n"+MapelName.get(i);
//            mapel +=note;
//        }
        mapel=""+MapelName.size();

        currentNotificationID=Integer.parseInt(intent.getStringExtra("NOTIFID"));

        notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_white_24dp)
                .setLargeIcon(icon)
                .setContentTitle("Mapel Hari Depan")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mapel))
                .setPriority(Notification.PRIORITY_MAX)
                .setContentText(mapel);

//        Intent answerIntent = new Intent(context, DetailHomeWork_activity.class);
//        answerIntent.setAction("Yes");
//        PendingIntent pendingIntentYes = PendingIntent.getActivity(context, 1, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notificationBuilder.addAction(R.drawable.ic_alarm_black_24dp, "Yes", pendingIntentYes);
//
//
//        answerIntent.setAction("No");
//        PendingIntent pendingIntentNo = PendingIntent.getActivity(context, 1, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notificationBuilder.addAction(R.drawable.ic_alarm_black_24dp, "No", pendingIntentNo);


        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        sendNotification(notificationBuilder.build(),context,currentNotificationID);
//        notificationHelper.getManager().notify(1, nb.build());

    }

    private void sendNotification(Notification notification,Context context, int currentNotificationID) {

        Intent notificationIntent = new Intent(context, Home_activity.class);
        notificationIntent.putExtra("NOTIFID",""+ currentNotificationID);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder.setContentIntent(contentIntent);
        notification = notificationBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;


        int notificationId = currentNotificationID;
        if (notificationId == Integer.MAX_VALUE - 1)
            notificationId = 0;


//        if (notificationId >= 4)
//            notificationId = 4;


        notificationManager.notify(notificationId, notification);
        // notificationManager.cancel(currentNotificationID-1);
    }
}
