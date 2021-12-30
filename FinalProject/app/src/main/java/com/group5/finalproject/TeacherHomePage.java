package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class TeacherHomePage extends AppCompatActivity {

    String []data = {"Mobile Programming", "Micro-controller", "Software Engineering"};
    int cntr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_page);

        List<String> items = new LinkedList<>();
        items.add("Code here");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.new_class).setOnClickListener(view -> {
            items.add(data[cntr%3]);
            cntr++;
            adapter.notifyItemInserted(items.size()-1);
        });


    }
}