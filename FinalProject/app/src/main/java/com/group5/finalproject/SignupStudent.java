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
    Isa nanamang class hayyy this time student sign up naman
    Hayy buhayyy bigtii nalang tayooooo
 */
public class SignupStudent extends AppCompatActivity {

    //Base sa xml file may dalawang mapipindot hehehe at yun ay ang sign in and textview na login

    //Itong EditText para sa TextWatcher ko hehehe gusto ko sana pag di complete ang values then
    //Di mag activate ang Sign-in Button
    private EditText et_studentsurname, et_studentfirstname,et_studentemail,et_studentschoolID,
            et_studentcreatepassword,et_studentconfirmpassword;
    private AppCompatButton button_signin; //Yung button sign in na color blue
    private ImageButton ib_back; //Yung Image button na back button para bumalik sa nakaraang activity
    private TextView textview_login; //Yung login din na color blue

    //Try ko ipasok yung component ng edit text sa arraylist para looping na hanapin lang
    ArrayList<EditText> et_studentarrlist = new ArrayList<>();

    Quiview quiview = new Quiview();

    String URL_StudentReg = "http://e2019cc107group5.000webhostapp.com/finalproject/student_register.php"; //URL ng database natin sa webhost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);

        //Kuhanin yung ID nung mga component para sa onclick event listener natin and text watcher
        button_signin = (AppCompatButton) findViewById(R.id.button_studentsignin);
        textview_login = (TextView) findViewById(R.id.tv_login_here);
        ib_back = (ImageButton) findViewById(R.id.ib_back);

        //Mga EditText natin
        et_studentsurname = (EditText) findViewById(R.id.et_studentsurname);
        et_studentfirstname = (EditText) findViewById(R.id.et_studentfirstname);
        et_studentemail = (EditText) findViewById(R.id.et_studentEmail);
        et_studentschoolID = (EditText) findViewById(R.id.et_studentschoolID);
        et_studentcreatepassword = (EditText) findViewById(R.id.et_studentcreatePassword);
        et_studentconfirmpassword = (EditText) findViewById(R.id.et_studentconfirmPassword);

        //Pasok sila sa arraylist natin kasi iloop natin sila
        et_studentarrlist.add(et_studentsurname);
        et_studentarrlist.add(et_studentfirstname);
        et_studentarrlist.add(et_studentemail);
        et_studentarrlist.add(et_studentschoolID);
        et_studentarrlist.add(et_studentcreatepassword);
        et_studentarrlist.add(et_studentconfirmpassword);

        button_signin.setEnabled(false);

        for (final EditText editText : et_studentarrlist) {
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
                    button_signin.setEnabled(
                            et_studentarrlist.get(0).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(1).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(2).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(3).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(4).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(5).getText().toString().trim().length() > 0
                            && et_studentarrlist.get(4).getText().toString().trim().equals(
                                    et_studentarrlist.get(5).getText().toString().trim()
                            )
                    );
                }
            });

            //Ang sunoddd ay ang ating button onClickListenerrr yeheyyy
            button_signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Dito ibig sabihin successful ang sign in plano ko sana redirect sa login student class
                    //Toast.makeText(getApplicationContext(),"Sign-up Successfully!", Toast.LENGTH_LONG );

                    //Register function here
                    Register();
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
                    Intent intent = new Intent(SignupStudent.this,LoginAs.class);

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
        // 3 - School ID
        // 4 - Create Password
        // 5 - Confirm Password

        quiview.setStudentSurname(et_studentarrlist.get(0).getText().toString().trim());
        quiview.setStudentFirstname(et_studentarrlist.get(1).getText().toString().trim());
        quiview.setStudentEmail(et_studentarrlist.get(2).getText().toString().trim());
        quiview.setStudent_id(et_studentarrlist.get(3).getText().toString().trim());

        //Sa String ko muna ipasok yung sa create password
        String createpass = et_studentarrlist.get(4).getText().toString().trim();
        quiview.setStudentPassword(et_studentarrlist.get(5).getText().toString().trim());

        //Hayy balik tayo sa String
        String stud_surname = quiview.getStudentSurname();
        String stud_firstname = quiview.getStudentFirstname();
        String stud_email = quiview.getStudentEmail();
        String studid = quiview.getStudentId();
        String stud_password = quiview.getStudentPassword();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_StudentReg,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            //Log.d("JSONR", jsonObject.toString());
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){
                                Toast.makeText(SignupStudent.this,"Registered Successfully", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignupStudent.this,"Register Error! " + e.toString() , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    //Ito ay para sa Error kung sakaling di makapasok ang data
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignupStudent.this,"Register Error! " , Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("stud_surname", stud_surname);
                params.put("stud_firstname",stud_firstname);
                params.put("stud_email",stud_email);
                params.put("studid",studid);
                params.put("stud_password",stud_password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
} 