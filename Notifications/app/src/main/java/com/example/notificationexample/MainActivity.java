package com.example.notificationexample;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// CODE FOR NOTIFICATION HERE!
        Button btnNotify = findViewById(R.id.btnShow);
        btnNotify.setOnClickListener(v -> {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle("Tutlane Send New Message")
                    .setContentText("Hi, Welcome to tutlane tutorial site");

// Set the intent to fire when the user taps onnotification.
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 1;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId, mBuilder.build());
        });


// CODE FOR BIGTEXT NOTIFICATION HERE!
        Button btnNotif = findViewById(R.id.btnShow2);
        btnNotif.setOnClickListener(v -> {
//To set large icon in notification
            Bitmap licon = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_nasa);
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("Welcome to tutlane, it provides a tutorials related to all technologies in software industry. Here we covered complete tutorials from basic to adavanced topics from all technologies");
            bigText.setSummaryText("By: Tutlane");
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_bigtxt)
                    .setContentTitle("Big Text Notification Example")

                    .setLargeIcon(licon)
                    .setStyle(bigText);
// Set the intent to fire when the user taps onnotification.
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 2;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId,
                    mBuilder.build());
            });


//  CODE FOR INBOX NOTIFICATION HERE!
        Button btnNotify3 = findViewById(R.id.btnShow3);
        btnNotify3.setOnClickListener(v -> {
//Implement inbox style notification
            NotificationCompat.InboxStyle iStyle = new NotificationCompat.InboxStyle();
            iStyle.addLine("Message 1.");
            iStyle.addLine("Message 2.");
            iStyle.addLine("Message 3.");
            iStyle.addLine("Message 4.");
            iStyle.addLine("Message 5.");
            iStyle.setSummaryText("+2 more");
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_message)
                    .setContentTitle("Inbox Style Notification Example")
                    .setStyle(iStyle);
// Set the intent to fire when the user taps on-notification.
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 3;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId,
                    mBuilder.build());
        });


//  CODE FOR BIG PICTURE NOTIFICATION HERE!
        Button btnNotify4 = findViewById(R.id.btnShow4);
        btnNotify4.setOnClickListener(v -> {
// Assign big picture notification
            NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
            bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_nasa)).build();
// Set the intent to fire when the user taps on-notification.
                    Intent rIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://tutlane.com/"));
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentTitle("Big Picture Notification Example")
                    .addAction(R.drawable.ic_share, "Share", pendingIntent)
                    .setStyle(bpStyle);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 4;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId,
                    mBuilder.build());
        });

    }
}