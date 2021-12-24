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
import android.widget.Toast;

import java.util.ArrayList;

/*
    Isa nanamang class hayyy this time student sign up naman
    Hayy buhayyy bigtii nalang tayooooo
 */
public class SignupStudent extends AppCompatActivity {

    //Base sa xml file may dalawang mapipindot hehehe at yun ay ang sign in and textview na login

    //Itong EditText para sa TextWatcher ko hehehe gusto ko sana pag di complete ang values then
    //Di mag activate ang Sign-in Button
    EditText et_studentsurname, et_studentfirstname,et_studentcourse,et_studentschoolID,
            et_studentcreatepassword,et_studentconfirmpassword;
    AppCompatButton button_signin; //Yung button sign in na color blue
    ImageButton ib_back; //Yung Image button na back button para bumalik sa nakaraang activity
    TextView textview_login; //Yung login din na color blue

    //Try ko ipasok yung component ng edit text sa arraylist para looping na hanapin lang
    ArrayList<EditText> et_studentarrlist = new ArrayList<>();

    Quiview quiview = new Quiview();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        //Kuhanin yung ID nung mga component para sa onclick event listener natin and text watcher
        button_signin = (AppCompatButton) findViewById(R.id.button_studentsignin);
        textview_login = (TextView) findViewById(R.id.textView_login);
        ib_back = (ImageButton) findViewById(R.id.ib_back);

        //Mga EditText natin
        et_studentsurname = (EditText) findViewById(R.id.et_studentsurname);
        et_studentfirstname = (EditText) findViewById(R.id.et_studentfirstname);
        et_studentcourse = (EditText) findViewById(R.id.et_studentcourse);
        et_studentschoolID = (EditText) findViewById(R.id.et_studentschoolID);
        et_studentcreatepassword = (EditText) findViewById(R.id.et_studentcreatePassword);
        et_studentconfirmpassword = (EditText) findViewById(R.id.et_studentconfirmPassword);

        //Pasok ko muna sila sa setters ko pero wag muna MAY GAGAWIN LANG DUN PAPALITAN DATA

        //Pasok sila sa arraylist natin kasi iloop natin sila
        et_studentarrlist.add(et_studentsurname);
        et_studentarrlist.add(et_studentfirstname);
        et_studentarrlist.add(et_studentcourse);
        et_studentarrlist.add(et_studentschoolID);
        et_studentarrlist.add(et_studentcreatepassword);
        et_studentarrlist.add(et_studentconfirmpassword);

        button_signin.setEnabled(false);

        for (final EditText editText : et_studentarrlist) { //need to be final daw eh
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

            //Ang sunoddd ay ang ating button onClickListenerrr yeheyyy
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            ib_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignupStudent.this,SignupAs.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignupStudent.this).toBundle());
                        finish();

                    } else {
                        // Edi walang transition
                        startActivity(intent);
                        finish();
                    }
                }
            });
            //Last is yung text view login natin na may onclick listener hayahayy
            textview_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignupStudent.this,MainActivity.class);

                    //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SignupStudent.this).toBundle());
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