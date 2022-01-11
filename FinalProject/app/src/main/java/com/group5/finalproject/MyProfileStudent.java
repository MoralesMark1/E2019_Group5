package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MyProfileStudent extends AppCompatActivity {

    ImageButton btn_profileback;
    TextView student_username, student_fullname,student_email;
    AppCompatButton btn_logout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_my_profile_student);

        sessionManager = new SessionManager(this);

        btn_logout = (AppCompatButton) findViewById(R.id.btn_logout);
        btn_profileback = (ImageButton) findViewById(R.id.btn_profileback);
        student_username = (TextView) findViewById(R.id.student_username);
        student_email = (TextView) findViewById(R.id.student_email);
        student_fullname = (TextView) findViewById(R.id.student_fullname);

        student_username.setText(sessionManager.getUsername());
        student_email.setText(sessionManager.getEmail());

        String fullname = sessionManager.getFirstname() + " " + sessionManager.getSurname();
        student_fullname.setText(fullname);

        btn_profileback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(MyProfileStudent.this,StudentHomePage.class);
                //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe

                    // Closing all the Activities
                    back.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    back.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(back, ActivityOptions.makeSceneTransitionAnimation(MyProfileStudent.this).toBundle());
                } else {
                    // Edi walang transition
                    startActivity(back);
                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sessionManager.setLogin(false);
                sessionManager.editor.clear();
                sessionManager.editor.commit();
                Intent logout = new Intent(MyProfileStudent.this, LoginStudent.class);

                //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(logout, ActivityOptions.makeSceneTransitionAnimation(MyProfileStudent.this).toBundle());
                    finish(); //Iwas balik sa activity hehehee
                } else {
                    // Edi walang transition

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(logout);
                    finish(); //PAra ngaa iwas balikkk sa activity na itooo
                }
            }
        });

    }
    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }
}