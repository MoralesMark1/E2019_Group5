package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/*
    So ito yung class nung teachers sign up hehehe
 */
public class SignupTeacher extends AppCompatActivity {

    EditText et_teachersurname, et_teacherfirstname, et_teacheremail, et_teacherusername,
            et_teachercreatepassword, et_teacherconfirmpassword;
    AppCompatButton button_signin; //Yung button sign in na color blue
    ImageButton imb_back; //Yung Image button na back button para bumalik sa nakaraang activity
    TextView textview_login; //Yung login din na color blue

    //Try ko ipasok yung component ng edit text sa arraylist para looping na hanapin lang
    ArrayList<EditText> et_teacherarrlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teacher);

        //Kuhanin muna natin ID nila hehhehe
        button_signin = (AppCompatButton) findViewById(R.id.button_signin);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        textview_login = (TextView) findViewById(R.id.tv_login_here);

        //Mga Edit Text natin hehehe
        et_teachersurname = (EditText) findViewById(R.id.et_teachersurname);
        et_teacherfirstname = (EditText) findViewById(R.id.et_teacherfirstname);
        et_teacheremail = (EditText) findViewById(R.id.et_teacherEmail);
        et_teacherusername = (EditText) findViewById(R.id.et_teacherusername);
        et_teachercreatepassword = (EditText) findViewById(R.id.et_teachercreatePassword);
        et_teacherconfirmpassword = (EditText) findViewById(R.id.et_teacherconfirmPassword);

        //Pasok sila sa arraylist natin kasi iloop natin sila
        et_teacherarrlist.add(et_teachersurname);
        et_teacherarrlist.add(et_teacherfirstname);
        et_teacherarrlist.add(et_teacheremail);
        et_teacherarrlist.add(et_teacherusername);
        et_teacherarrlist.add(et_teachercreatepassword);
        et_teacherarrlist.add(et_teacherconfirmpassword);

        button_signin.setEnabled(false);

        for (final EditText editText : et_teacherarrlist) { //need to be final daw eh
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {

                }

            });

            //Yung back image button arrow pabalik dun sa sign up as class
            imb_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignupTeacher.this,SignupAs.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignupTeacher.this).toBundle());

                    } else {
                        // Edi walang transition
                        startActivity(intent);
                    }

                }
            });

            //Yung sign in button
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            textview_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignupTeacher.this,LoginAs.class);

                    //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignupTeacher.this).toBundle());
                        finish();

                    } else {
                        // Edi walang transition
                        startActivity(intent);
                        finish();
                    }
                }
            });


        }
    }

    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }
}