package com.group5.finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginAs extends AppCompatActivity implements View.OnClickListener {

    //Dito dalawang Buttons lang and isang textview
    ImageButton login_teacher,login_student;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);


        //Ilagay yung onclick listener sa kanila hehhe para makapag switch case akoo
        login_teacher = (ImageButton) findViewById(R.id.imagebutton_teacher);
        login_student = (ImageButton) findViewById(R.id.imagebutton_student);
        signup = (TextView) findViewById(R.id.tv_signup_here);

        //Implement sa kanila yung on click listener hayy buhayyyy katamad mag type
        login_teacher.setOnClickListener(this);
        login_student.setOnClickListener(this);
        signup.setOnClickListener(this);

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
                //Intent para bumalik sa login
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
                //toast kasi di wala silang naclick hahhaha hayy buhayyy
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