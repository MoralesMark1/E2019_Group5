package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
    TextView textview_login; //Yung login din na color blue

    //Try ko ipasok yung component ng edit text sa arraylist para looping na hanapin lang
    ArrayList<EditText> et_arrlist = new ArrayList<>();

    Quiview quiview = new Quiview();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        //Kuhanin yung ID nung mga component para sa onclick event listener natin and text watcher
        button_signin = (AppCompatButton) findViewById(R.id.button_studentsignin);
        textview_login = (TextView) findViewById(R.id.textView_login);

        //Mga EditText natin
        et_studentsurname = (EditText) findViewById(R.id.et_studentsurname);
        et_studentfirstname = (EditText) findViewById(R.id.et_studentfirstname);
        et_studentcourse = (EditText) findViewById(R.id.et_studentcourse);
        et_studentschoolID = (EditText) findViewById(R.id.et_studentschoolID);
        et_studentcreatepassword = (EditText) findViewById(R.id.et_studentcreatePassword);
        et_studentconfirmpassword = (EditText) findViewById(R.id.et_studentconfirmPassword);

        //Pasok ko muna sila sa setters ko pero wag muna MAY GAGAWIN LANG DUN PAPALITAN DATA

        //Pasok sila sa arraylist natin kasi iloop natin sila
        et_arrlist.add(et_studentsurname);
        et_arrlist.add(et_studentfirstname);
        et_arrlist.add(et_studentcourse);
        et_arrlist.add(et_studentschoolID);
        et_arrlist.add(et_studentcreatepassword);
        et_arrlist.add(et_studentconfirmpassword);

        button_signin.setEnabled(false);

        for (final EditText editText : et_arrlist) { //need to be final daw eh
            editText.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    //Check natin kung may mga nakalagay ba dun sa edit text

                    if(s.toString().trim().length() == 0){
                        button_signin.setEnabled(false);
                    }
                    else {
                        //Now need natin iconfirm kung same ba ang nilagay sa password and
                        //confirm password
                        //Well logic ko ito sooo hehe di ako nag google :P
                        if(et_arrlist.get(4).toString().trim() == et_arrlist.get(5).toString().trim()){
                            button_signin.setEnabled(true);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //Ang sunoddd ay ang ating button onClickListenerrr yeheyyy
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //So ang plano is pag kumpleto na mga mga informations dun sa mga editText edi goods

                    //Hindi pa ito final for testing purposes lang ito hehhehe
                    Toast toast = new Toast(getApplicationContext());
                    toast.setText("Yeheyyy naka sign in na siyaa");
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
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

        //Tanggalin yung status bar na may battery percentage or signal sa taas ng kahit anong cp
        getWindow().requestFeature(View.SYSTEM_UI_FLAG_FULLSCREEN);

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Explode());
    }
}