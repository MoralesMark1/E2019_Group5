package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

/*
    Another class nanaman ang malalagyan ng comment na may halong buntong hiningaaaa whooooo
    buhayyy grabeee merry christmas nga palaaa sa inyoooo para akong nagvlog ditoooo

    Created by: Jerome Pasag na nawawalan na ng gana dahil sa pandemic

 */
public class SignupAs extends AppCompatActivity implements View.OnClickListener{

    //Dito dalawang Buttons lang and isang textview
    ImageButton signup_teacher,signup_student;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_as);


        //Ilagay yung onclick listener sa kanila hehhe para makapag switch case akoo
        signup_teacher = (ImageButton) findViewById(R.id.imagebutton_teacher);
        signup_student = (ImageButton) findViewById(R.id.imagebutton_student);
        login = (TextView) findViewById(R.id.textView_login);

        //Implement sa kanila yung on click listener hayy buhayyyy katamad mag type
        signup_teacher.setOnClickListener(this);
        signup_student.setOnClickListener(this);
        login.setOnClickListener(this);

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

         */

    @Override
    public void onClick(View v) {

        //At ito na ngaaa ditoo need natin kunin yung id na nilagay natin sa ating UI components
        //Itong si switch case with v variable na view bale dito na lahat ng onclicklisteners natin sa tatlong component
        // sa taas para mabilis

        switch (v.getId()){
            //ImageButton ng Teacher dun sa XML file
            case R.id.imagebutton_teacher:
                    //Intent para makapunta sa sign up class and xml layout ng teacher
                    Intent signup_teacher = new Intent(getApplicationContext(),SignupTeacher.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(signup_teacher, ActivityOptions.makeSceneTransitionAnimation(SignupAs.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(signup_teacher);
                    finish();
                }

                break;
            //ImageButton ng Student dun sa XML File
            case R.id.imagebutton_student:
                    //Intent para makapunta sa sign up class and xml layout ng student
                    Intent signup_student = new Intent(getApplicationContext(),SignupStudent.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(signup_student, ActivityOptions.makeSceneTransitionAnimation(SignupAs.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(signup_student);
                    finish();
                }

                break;
            //TextView ng login para bumalik dun sa login natin na main activity
            case R.id.textView_login:
                    //Intent para bumalik sa login
                    Intent back_login = new Intent(getApplication(),MainActivity.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(back_login, ActivityOptions.makeSceneTransitionAnimation(SignupAs.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(back_login);
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

        //Tanggalin yung status bar na may battery percentage or signal sa taas ng kahit anong cp
        getWindow().requestFeature(View.SYSTEM_UI_FLAG_FULLSCREEN);

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh wala naman akong theme naging dalawa sila bigla
        //Siguro sa dating android studio may theme.xml talaga eh dito sakin style.xml ehh hayy buhay
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Fade());
    }
}