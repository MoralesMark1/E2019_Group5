package com.example.toastexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btnShow);
        btn.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            // here, we call the customized layout that we created!
            View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
            TextView tv = layout.findViewById(R.id.txt_view);
            tv.setText("Customized Toast by: Group 5");

            Toast toast = new Toast(getApplicationContext());
            //toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100); // to make it centered!
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
        });
    }
}