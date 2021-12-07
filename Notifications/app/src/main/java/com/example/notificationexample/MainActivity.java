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

// CODE FOR NORMAL NOTIFICATION HERE!
        Button btnNotify1 = findViewById(R.id.btnNormal);
        btnNotify1.setOnClickListener(v -> {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_notify)
                    .setContentTitle("Normal Notification")
                    .setContentText("Hi, We are Group 5 from E2019")
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_MAX);

// Set the intent to fire when the user taps on-notification.
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 1;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId, mBuilder.build());
        });     // END OF CODE FOR NORMAL NOTIFICATION HERE!


// CODE FOR BIG-TEXT NOTIFICATION HERE!
        Button btnNotify2 = findViewById(R.id.btnBigtext);
        btnNotify2.setOnClickListener(v -> {
//To set large icon in notification
            Bitmap smallIcon = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_nasa);
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("This is the sample of Big text Notification that we created");
            bigText.setSummaryText("By: Group 5");
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_bigtxt)
                    .setContentTitle("Big Text Notification")
                    .setLargeIcon(smallIcon)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setStyle(bigText);

// Set the intent to fire when the user taps on-notification.
            Intent resultIntent = new Intent(MainActivity.this, MainActivity.class);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, resultIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 2;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId,
                    mBuilder.build());
            });     // END OF CODE FOR BIG-TEXT NOTIFICATION HERE!


//  CODE FOR INBOX NOTIFICATION HERE!
        Button btnNotify3 = findViewById(R.id.btnInbox);
        btnNotify3.setOnClickListener(v -> {
//Implement inbox style notification
            NotificationCompat.InboxStyle iStyle = new NotificationCompat.InboxStyle();
            iStyle.addLine("Charlie Ochada");
            iStyle.addLine("Jerome Pasag");
            iStyle.addLine("Mark Morales");
            iStyle.addLine("Veronica Calising");
            iStyle.addLine("Maricar Ledesma");
            iStyle.setSummaryText("+2 more");

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_message)
                    .setContentTitle("Inbox Style Notification")
                    .setStyle(iStyle)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

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
        });     // END OF CODE FOR INBOX NOTIFICATION HERE!


//  CODE FOR BIG PICTURE NOTIFICATION HERE!
        Button btnNotify4 = findViewById(R.id.btnBigPicture);
        btnNotify4.setOnClickListener(v -> {
// Assign big picture notification
            NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
            bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_nasa)).build();
// Set the intent to fire when the user taps on-notification.
                    Intent rIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MoralesMark1/E2019_Group5"));
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_android)
                    .setContentTitle("Big Picture Notification")
                    .addAction(R.drawable.ic_visit_site, "Visit Site", pendingIntent)
                    .setStyle(bpStyle)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setAutoCancel(true);

            mBuilder.setContentIntent(pendingIntent);
// Sets an ID for the notification
            int mNotificationId = 4;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// It will display the notification in notification bar
            notificationManager.notify(mNotificationId,
                    mBuilder.build());
        });     // END OF CODE FOR BIG PICTURE NOTIFICATION HERE!

    }
}