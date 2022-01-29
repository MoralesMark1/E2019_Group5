package com.group5.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.Session2Command;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TeacherHomePage extends AppCompatActivity {



    String []data = {"Mobile Programming", "Micro-controller", "Software Engineering"};
    int cntr = 0;

    //Initialize yung ating mga components for onClickListener dito ahhh
    ImageButton teacher_profile;
    Intent myfileIntent;
    String path; // Dito mai-istore yung file na napili ng user

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_teacher_home_page);



        //sessionManager = new SessionManager(this);
        teacher_profile = (ImageButton) findViewById(R.id.image_profileteacher);

        List<String> items = new LinkedList<>();
        items.add("Code here");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items);
        recyclerView.setAdapter(adapter);

        teacher_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myfileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myfileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Specific FIle format
                myfileIntent.addCategory(Intent.CATEGORY_OPENABLE); // to open the file
                startActivityForResult(myfileIntent, 10);


                /*
                Intent teacher_profile = new Intent(TeacherHomePage.this, MyProfileTeacher.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(teacher_profile, ActivityOptions.makeSceneTransitionAnimation(TeacherHomePage.this).toBundle());
                } else {
                    // Edi walang transition
                    startActivity(teacher_profile);
                }
                */
            }
        });

    }

    //  ----------------------------- Excel Import Code -------------------------------
//  --------------- Mga Pre Eto yung Code para sa importing @-Charlie -------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {
                    path = data.getData().getPath(); // dito lang naka store yung file

                }
                break;
        }
    }

// ----------------------------- End of Import Excel Code---------------------------------

    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }






}