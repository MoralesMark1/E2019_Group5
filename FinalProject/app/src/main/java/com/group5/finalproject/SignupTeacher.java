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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    So ito yung class nung teachers sign up hehehe
 */
public class SignupTeacher extends AppCompatActivity {

    private EditText et_teachersurname, et_teacherfirstname, et_teacheremail, et_teacherusername,
            et_teachercreatepassword, et_teacherconfirmpassword;
    private AppCompatButton button_teachersignin; //Yung button sign in na color blue
    private ImageButton imb_back; //Yung Image button na back button para bumalik sa nakaraang activity
    private TextView textview_login; //Yung login din na color blue

    //Try ko ipasok yung component ng edit text sa arraylist para looping na hanapin lang
    ArrayList<EditText> et_teacherarrlist = new ArrayList<>();

    Quiview quiview = new Quiview();

    String URL_TeacherReg = "http://e2019cc107group5.000webhostapp.com/finalproject/teacher_register.php"; //URL ng database natin sa webhost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_signup_teacher);

        //Kuhanin muna natin ID nila hehhehe
        button_teachersignin = (AppCompatButton) findViewById(R.id.button_teachersignin);
        imb_back = (ImageButton) findViewById(R.id.imb_back);
        textview_login = (TextView) findViewById(R.id.tv_login_here);

        //Mga Edit Text natin hehehe
        et_teachersurname = (EditText) findViewById(R.id.et_teachersurname);
        et_teacherfirstname = (EditText) findViewById(R.id.et_teacherfirstname);
        et_teacheremail = (EditText) findViewById(R.id.et_teacheremail);
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

        button_teachersignin.setEnabled(false);

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
                    //Isa-isahin ko nalang sila dito
                    button_teachersignin.setEnabled(
                            et_teacherarrlist.get(0).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(1).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(2).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(3).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(4).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(5).getText().toString().trim().length() > 0
                                    && et_teacherarrlist.get(4).getText().toString().trim().equals(
                                        et_teacherarrlist.get(5).getText().toString().trim()
                            )
                    );
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
            button_teachersignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"Sign-up Successfully", Toast.LENGTH_LONG).show();

                    Register();

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

    /**************
     *  Dito sa part na ito papaikliin ko ang connection sa database
     *  using Volley library para easyyyyyyyy hehehhehe :) :) :) :)
     *
     *  Created by: The Group 5
     */
    private void Register() {
        //Kunin ang mga text natin sa ating edit text whether using edittext mismo
        //Pwede rin kunin nalang from the arraylist natin hehehe
        // 0 - Surname
        // 1 - Firstname
        // 2 - Email
        // 3 - Username
        // 4 - Create Password
        // 5 - Confirm Password

        //Then pasok ko ulit sa setters hehehe
        quiview.setTeacherSurname(et_teacherarrlist.get(0).getText().toString().trim());
        quiview.setTeacherFirstname(et_teacherarrlist.get(1).getText().toString().trim());
        quiview.setTeacherEmail(et_teacherarrlist.get(2).getText().toString().trim());
        quiview.setTeacherUsername(et_teacherarrlist.get(3).getText().toString().trim());

        //Yung passwords natin pero later baka tanggalin ko yung create password di ko na isama
        //since may parang validation ako sa setEnabled function ng button sign in na yun
        String createpass = et_teacherarrlist.get(4).getText().toString().trim();
        quiview.setTeacherPassword(et_teacherarrlist.get(5).getText().toString().trim());

        //Then balik ko ulit sa String
        String teach_surname = quiview.getTeacherSurname();
        String teach_firstname = quiview.getTeacherFirstname();
        String teach_email = quiview.getTeacherEmail();
        String teach_username = quiview.getTeacherUsername();
        String teach_password = quiview.getTeacherPassword();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TeacherReg,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            //Log.d("JSONR", jsonObject.toString());
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(SignupTeacher.this,"Registered Successfully", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignupTeacher.this,"Register Error! " + e.toString() , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    //Ito ay para sa Error kung sakaling di makapasok ang data
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupTeacher.this,"Register Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("teach_surname", teach_surname);
                params.put("teach_firstname",teach_firstname);
                params.put("teach_email",teach_email);
                params.put("teach_username",teach_username);
                params.put("teach_password",teach_password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}