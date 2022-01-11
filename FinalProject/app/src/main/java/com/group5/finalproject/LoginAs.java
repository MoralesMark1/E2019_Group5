package com.group5.finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginAs extends AppCompatActivity implements View.OnClickListener {

    //Dito dalawang Buttons lang and isang textview
    ImageButton login_teacher,login_student;
    TextView signup;

    Quiview quiview = new Quiview(); //Yung Quiview Class na andito student and teachers data

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);

        sessionManager = new SessionManager(this);

        //Ilagay yung onclick listener sa kanila hehhe para makapag switch case akoo
        login_teacher = (ImageButton) findViewById(R.id.imagebutton_teacher);
        login_student = (ImageButton) findViewById(R.id.imagebutton_student);
        signup = (TextView) findViewById(R.id.tv_signup_here);


        //Implement sa kanila yung on click listener hayy buhayyyy katamad mag type
        login_teacher.setOnClickListener(this); //Para maglogin as Teacher
        login_student.setOnClickListener(this); //Para maglogin as Student
        signup.setOnClickListener(this); //Para pumunta sa signup


        /**********
         *
         * ito yun nilagay ko pacheck nalang kung working sa inyo hehehee :) - Mark
         *
         *
         * Working naman men kaso tinanggal ko yung nilagay mo sa taas na mga setters galing sa SessionManager...
         * Para gumana ang need nalang natin is get ang ating login boolean value kasi di na natin need magset
         * ng mga panibagong values hindi gagana ang ating shared preferences - Jerome
         *
         * Ang problema ngalang is paano natin madedetermine kung student or teacher
         * since ang nandito lang is intent papuntang student homepage diba? - Jerome
         *
         * Therefore ang solution/logic ko is gumawa ako ng setUser and getUser function sa Session Manager
         * na magdedetermine kung "student" or "teacher" ba ang user natin diba? and isasama ko yan sa ating
         * if-else condition using AND && operator kung student edi ang punta is sa student homepage kapag naman
         * teacher edi ang punta is sa teacher homepage - Jerome
         *
         * P.S Provided dapat na sa Login ng teacher and student dun sa kanilang respective Login Class is
         * nakaset na both setLogin and setUser :) :) :) - Jerome
         *
         */

        if(sessionManager.getLogin() && sessionManager.getUser().trim().equals("student")){

            //Intent papuntang student homepage
            Intent intent = new Intent(LoginAs.this, StudentHomePage.class);

            //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Activity transition dito hehehe

                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginAs.this).toBundle());
                finish(); //Iwas balik sa activity hehehee
            } else {
                // Edi walang transition
                startActivity(intent);
                finish(); //PAra ngaa iwas balikkk sa activity na itooo
            }
        }

        /*********************
         * Since nakacomment ang ating session manager sa ating teacher login class di naman ito
         * gagana hahaha kaya no need to comment this else if conditon ng ating teacher
         */
        else if(sessionManager.getLogin() && sessionManager.getUser().equals("teacher")){

            //Intent papuntang teacher homepage
            Intent intent = new Intent(LoginAs.this, TeacherHomePage.class);

            //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Activity transition dito hehehe
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginAs.this).toBundle());
                finish(); //Iwas balik sa activity hehehee
            } else {
                // Edi walang transition
                startActivity(intent);
                finish(); //PAra ngaa iwas balikkk sa activity na itooo
            }
        }
    }

    /*
            Itooo ang pinakamahalagaaa kasi pag wala yung onClick method mamumulaa yung class natin
            na nag implement ng onClick Listenerr kaya need yung onClick method/function natin
            na may parameter na View v variable
            Kaya ko lang naman inimplement onclick listener sa class mismo paraa mapadali sa click
            events ko ehhh para hindi yung kada button onclick here panibagong function nanaman since
            new UI component nanaman... nagegets niyooo????

            Override o hindi gagana parin yang onClick sabi kasi ng IDE ko lagyan ko ng Override eh
            kahit wag na hay naku pero ilalagay ko talaga hehhehe para maganda

            okiiee......

         */

    @Override
    public void onClick(View v) {

        //At ito na ngaaa ditoo need natin kunin yung id na nilagay natin sa ating UI components
        //Itong si switch case with v variable na view bale dito na lahat ng onclicklisteners natin sa tatlong component
        // sa taas para mabilis

        switch (v.getId()){
            //ImageButton ng Teacher dun sa XML file
            case R.id.imagebutton_teacher:
                //Intent para makapunta sa log-in class and xml layout ng teacher
                Intent login_teacher = new Intent(getApplicationContext(),LoginTeacher.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(login_teacher, ActivityOptions.makeSceneTransitionAnimation(LoginAs.this).toBundle());

                } else {
                    // Edi walang transition
                    startActivity(login_teacher);

                }

                break;
            //ImageButton ng Student dun sa XML File
            case R.id.imagebutton_student:
                //Intent para makapunta sa log-in class and xml layout ng student
                Intent login_student = new Intent(getApplicationContext(),LoginStudent.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(login_student, ActivityOptions.makeSceneTransitionAnimation(LoginAs.this).toBundle());

                } else {
                    // Edi walang transition
                    startActivity(login_student);

                }

                break;
            //TextView ng login para bumalik dun sa login natin na main activity
            //Pacheck nalang dito pre...
            case R.id.tv_signup_here:
                //Intent para bumalik sa signup class natin for signup
                Intent back = new Intent(getApplication(),SignupAs.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(back, ActivityOptions.makeSceneTransitionAnimation(LoginAs.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(back);
                    finish();
                }
                break;

            default:
                //Nevermind wala namang dapat idefault dito ehh alangan namang gawa ako ng
                //toast kasi wala silang naclick hahhaha hayy buhayyy
                break;


        }
    }

    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh wala naman akong theme naging dalawa sila bigla
        //Siguro sa dating android studio may theme.xml talaga eh dito sakin style.xml ehh hayy buhay
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }
}