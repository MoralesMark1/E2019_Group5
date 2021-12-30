package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class StudentHomePage extends AppCompatActivity {

    ImageView student_profile;

    // Sample code palang to dun sa classes
    String []data = {"Mobile Programming", "Micro-controller", "Software Engineering"};
    int cntr = 0;

    // ------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        student_profile = (ImageView) findViewById(R.id.image_profile_student); //Image natin

        student_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Pupunta sa Next activity which is yung student profile
                    Intent student_profile = new Intent(getApplicationContext(),StudentHomePage.class);

                    //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(student_profile, ActivityOptions.makeSceneTransitionAnimation(StudentHomePage.this).toBundle());

                    } else {
                        // Edi walang transition
                        startActivity(student_profile);
                    }
            }
        });

        // -------------------------------------------------------------------------------------------
        // sample code para sa classes
        List<String> items = new LinkedList<>();
        items.add("Code here");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.join_class).setOnClickListener(view -> {
            items.add(data[cntr%3]);
            cntr++;
            adapter.notifyItemInserted(items.size()-1);
        });
    }
}