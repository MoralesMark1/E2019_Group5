package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Quiview quiview = new Quiview(); //The quiview class containing data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}