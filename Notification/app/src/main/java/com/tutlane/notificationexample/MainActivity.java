package com.tutlane.notificationexample;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //These are the 4 buttons we need in our app

        //Button for small icon notification
        Button btnSmallIcon = (Button)findViewById(R.id.btnSmallIcon);
        btnSmallIcon.setOnClickListener(v -> {
            //Method of Small Icon here
            showSmallIcon();
        });

        //Button for big text notification
        Button btnBigText = (Button)findViewById(R.id.btnBigText);
        btnBigText.setOnClickListener(v -> {
            //Method of Big Text Notification here
            showBigText();
        });

        //Button for inbox style notification
        Button btnInboxStyle = (Button)findViewById(R.id.btnInboxStyle);
        btnInboxStyle.setOnClickListener(v -> {
            //Method of Inbox Style Notification here
            showInboxNotif();
        });

        //Button for picture style notification
        Button btnPictureStyle = (Button)findViewById(R.id.btnBigPicture);
        btnPictureStyle.setOnClickListener(v -> {
            //Method of Big Picture Style Notification here
            showPictureNotif();
        });
    }

    //For the Small Icon Notification
    private void showSmallIcon(){
        //We need these things first
        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_3";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //Here we set everything now
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,channelId);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Small Icon Notification Activity");
        builder.setContentText("Hi, We are Group 5");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        /*
        *
        * Here this Notification Channel is very important
        * Without this the notification would not appear
        * This is for Android Oreo 8.1 above
        *
        */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager != null && notificationManager.getNotificationChannel((channelId)) == null){
                NotificationChannel notificationChannel = new NotificationChannel(channelId,"NotificationChannel",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Channel for this Activity");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if(notificationManager != null){
            notificationManager.notify(notificationId,builder.build());
        }
    }

    //For the Big Text Notification
    private void showBigText(){
        //We need these things first
        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_3";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //And then add bitmap here for the large icon
        Bitmap largeicon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_notification);
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("This is the Big Text Notification created by Group 5");
        bigText.setSummaryText("By: Group 5");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,channelId);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Big Text Notification Activity");
        builder.setLargeIcon(largeicon);
        builder.setStyle(bigText);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        /*
         *
         * Here this Notification Channel is very important
         * Without this the notification would not appear
         * This is for Android Oreo 8.1 above
         *
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager != null && notificationManager.getNotificationChannel((channelId)) == null){
                NotificationChannel notificationChannel = new NotificationChannel(channelId,"NotificationChannel",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Channel for this Activity");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if(notificationManager != null){
            notificationManager.notify(notificationId,builder.build());
        }
    }

    //For the Inbox Style Notification
    private void showInboxNotif(){
        //We need these things first
        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_3";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //Then we create the inbox style notification here
        NotificationCompat.InboxStyle iStyle = new NotificationCompat.InboxStyle();
        iStyle.addLine("Leader: Charlie Ochada");
        iStyle.addLine("Members:");
        iStyle.addLine("Jerome Pasag");
        iStyle.addLine("Mark Morales");
        iStyle.addLine("Veronica Calising");
        iStyle.addLine("Maricar Ledesma");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,channelId);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Inbox Style Notification Activity");
        builder.setStyle(iStyle);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        /*
         *
         * Here this Notification Channel is very important
         * Without this the notification would not appear
         * This is for Android Oreo 8.1 above
         *
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager != null && notificationManager.getNotificationChannel((channelId)) == null){
                NotificationChannel notificationChannel = new NotificationChannel(channelId,"NotificationChannel",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Channel for this Activity");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if(notificationManager != null){
            notificationManager.notify(notificationId,builder.build());
        }
    }

    //For the Picture Style Notification
    private void showPictureNotif(){
        //We need these things first
        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_3";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //We start to assign the big picture notification here
        NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
        bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.tcu)).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,channelId);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setContentTitle("Big Picture Notification Activity");
        builder.setStyle(bpStyle);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        /*
         *
         * Here this Notification Channel is very important
         * Without this the notification would not appear
         * This is for Android Oreo 8.1 above
         *
         */

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(notificationManager != null && notificationManager.getNotificationChannel((channelId)) == null){
                NotificationChannel notificationChannel = new NotificationChannel(channelId,"NotificationChannel",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Channel for this Activity");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        if(notificationManager != null){
            notificationManager.notify(notificationId,builder.build());
        }
    }
}

