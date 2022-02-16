package com.group5.finalproject;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

//Profile ng teacher which is somewhat similar sa code ng student yeahh
public class MyProfileTeacher extends AppCompatActivity {

    ImageButton btn_back;
    TextView teacher_fullname, teacher_username,teacher_email;
    AppCompatButton btn_teacherlogout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_my_profile_teacher);

        //Also pag wala nito magkakaroon ng null pointer exception
        sessionManager = new SessionManager(this); //Para maipasok ang laman ng shared preferences sa textview

        btn_back = (ImageButton) findViewById(R.id.btn_teacherprofileback);
        btn_teacherlogout = (AppCompatButton) findViewById(R.id.btn_teacherlogout);

        //Yung TextViews kung san natin ilalagay mga need natin
        teacher_fullname = (TextView) findViewById(R.id.teacher_fullname);
        teacher_username = (TextView) findViewById(R.id.teacher_username);
        teacher_email = (TextView) findViewById(R.id.teacher_email);

        //Then as usual Ilagay ang laman ng shared preference kung san man dapat ilagay
        teacher_username.setText(sessionManager.getUsername());
        teacher_email.setText(sessionManager.getEmail());

        String fullname = sessionManager.getFirstname() + " " + sessionManager.getSurname();
        teacher_fullname.setText(fullname);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pabalik sa teacher homepage
                Intent back = new Intent(MyProfileTeacher.this, TeacherHomePage.class);

                //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(back, ActivityOptions.makeSceneTransitionAnimation(MyProfileTeacher.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(back);
                    finish();
                }
            }
        });

        btn_teacherlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent logout = new Intent(MyProfileTeacher.this, LoginTeacher.class);

                //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe

                    //Set natina ng login sa false then clear lahat ng laman ng shared preference them commit natin ang changes
                    sessionManager.setLogin(false);
                    sessionManager.editor.clear();
                    sessionManager.editor.commit();

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(logout, ActivityOptions.makeSceneTransitionAnimation(MyProfileTeacher.this).toBundle());
                    finish();
                } else {
                    // Edi walang transition
                    //Set natina ng login sa false then clear lahat ng laman ng shared preference them commit natin ang changes
                    sessionManager.setLogin(false);
                    sessionManager.editor.clear();
                    sessionManager.editor.commit();

                    // Closing all the Activities
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(logout);
                    finish();
                }
            }
        });

    }

    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }

    private void killActivity(){
        finish();
    }

}
