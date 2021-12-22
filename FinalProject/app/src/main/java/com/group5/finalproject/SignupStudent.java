package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;

/*
    Isa nanamang class hayyy this time student sign up naman
    Hayy buhayyy bigtii nalang tayooooo
 */
public class SignupStudent extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);


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