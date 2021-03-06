package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
    Pambihirang buhayy tohhh hayyy ano ba ginagawa kooooo whoooo ahhahahha
    Sadlife di na ako masaya sa course koo :(
 */

public class LoginTeacher extends AppCompatActivity {

    /*
        Ang need lang natin is imagebutton na login para makapasok sa susunod na activity base sa
        kung sino ang nag sign up and kasama yung textview na signup para makapasok sa sign up activity
        and lastly yung dalawang editText para makuha natin yung laman nung string value na nandun
        so bale pag tama ang username and password makakapaglogin tayo, pag hindi gagamit tayo ng toast
        to notify the user na mali yung credentials na nilagay niya

       Ps. Hindi na natin need galawin yung ibang components such as yung mga textview na andun
       or yung imageview na logo natin

     */

    private TextView tv_signup; //Ito yung sign up textview na pipindutin para makapunta sa next activity
    private ImageButton teacherlogin; //Yung image button na may pangalan na Login
    private EditText et_teacherusername,et_teacherpassword; //Yung dalawang parang textbox natin

    Quiview quiview = new Quiview(); //Yung Quiview Class na andito student and teachers data

    String URL_teacherlogin= "http://e2019cc107group5.000webhostapp.com/finalproject/teacher_login.php"; //URL sa webhost para makalogin

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts(); //Eh saaa gusto ko tanggalin yung status bar sa taas ahahaha
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        //Kapag wala ito magkakaroon ng Null Pointer Exception base sa debugging ko
        sessionManager = new SessionManager(this);

        //Need natin kunin yung id dun sa xml file natin
        teacherlogin = (ImageButton) findViewById(R.id.login); //ImageButton natin
        tv_signup = (TextView) findViewById(R.id.tv_signup); //TextView natin
        et_teacherusername = (EditText) findViewById(R.id.et_teacherusername); //EditText ng Username natin
        et_teacherpassword = (EditText) findViewById(R.id.et_teacherpassword); //EditText ng Password natin

        //Attempted Login pero ichecheck muna natin kung tama ba ang mga credentials base sa nandun
        //sa database natin
        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Final logic na ito para sa login natin hehehe
                //Pasok laman ng edit text sa ating getters and setters
                quiview.setTeacherUsername(et_teacherusername.getText().toString().trim()); //Username na tinype
                quiview.setTeacherPassword(et_teacherpassword.getText().toString().trim()); //Password na tinype

                //Ah basta kapag may laman ang username and password go na pag wala edi yung else
                //Pero seriously, sa database tayo kukuha nung laman hayyy buhay

                String teach_username = quiview.getTeacherUsername();
                String teach_password = quiview.getTeacherPassword();

                if(TextUtils.isEmpty(teach_username.trim())){
                    et_teacherusername.setError("Please enter Username");
                    et_teacherusername.requestFocus();
                }
                if(TextUtils.isEmpty(teach_password.trim())){
                    et_teacherpassword.setError("Please enter Password");
                    et_teacherpassword.requestFocus();
                }
                else if(!(TextUtils.isEmpty(teach_username.trim())) && !(TextUtils.isEmpty(teach_password.trim()))){
                    //Login function or method natin dito I think lagay nalang ako parameter ahahha
                    Login(teach_username, teach_password);
                }
            }
        });

        //Ahhh basta ito yung sign up textview na color blue na light na hayy ewan
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dito sa signup textview natin pag naclick siya lilipat siya sa next class or activity
                //using intent hehehe

                Intent intent = new Intent(getApplicationContext(),SignupAs.class);

                //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginTeacher.this).toBundle());
                    finish(); //Iwas balik sa activity hehehee
                } else {
                    // Edi walang transition
                    startActivity(intent);
                    finish(); //Para ngaa iwas balikkk sa activity na itooo
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

    private void Login(String teach_username, String teach_password){

        //Another String Request using Volley hehehe :)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_teacherlogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            Log.i("tagconvertstr", "["+response+"]");

                            if(success.equals("1")){
                                /***************
                                 * Sooo update lang nasolve ko na yung problem regarding JSONArray
                                 * Ang problem is nandunn pala sa PHP file natin
                                 * Grabe yung source ko regarding sa ganitong bagay hayy mali-mali
                                 * need ko pa idebug
                                 *
                                 * By observing the flow of code na gawa ko sa PHP napansin ko naaa
                                 * hindi sakop ng json_encode ang login array dahil ang array ay hindi under ng
                                 * if/else condition na ginawa ko kaya ang nangyayari which is also base
                                 * sa observation ko imbes na maparse siya as a JSONObject na gagamitin sanaaa
                                 * ng JSONArray kooo nababasa siya as HTML kung tawagin kaya nga Values <br of
                                 * type string cannot be converted to object something ang stack trace natin
                                 */

                                //Kukunin natin ang values ng array na associated dun sa student id and password sa php
                                for(int i = 0; i < jsonArray.length();i++) {

                                    //Kukuhanin ko yung array dun sa login sa php heheheh then pasok ko dito
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    //teach_surname
                                    String t_sname = object.getString("teach_surname").trim();

                                    //teach_firstname
                                    String t_fname = object.getString("teach_firstname").trim();

                                    //teach_username
                                    String t_uname = object.getString("teach_username").trim();

                                    //teach_email
                                    String t_email = object.getString("teach_email").trim();

                                    //Ipasok sila sa ating getters and setters
                                    quiview.setTeacherUsername(t_uname);
                                    quiview.setTeacherEmail(t_email);
                                    quiview.setTeacherSurname(t_sname);
                                    quiview.setTeacherFirstname(t_fname);

                                }

                                //Intent to Teacher Home Page Here
                                Toast.makeText(LoginTeacher.this, jsonObject.getString("message") +" " + quiview.getTeacherFirstname() + " " + quiview.getTeacherSurname(), Toast.LENGTH_SHORT).show();

                                //Session Manager ng teacher natin using shared preference
                                sessionManager.setLogin(true);
                                sessionManager.setUser("teacher");
                                sessionManager.setUsername(quiview.getTeacherUsername());
                                sessionManager.setEmail(quiview.getTeacherEmail());
                                sessionManager.setSurname(quiview.getTeacherSurname());
                                sessionManager.setFirstname(quiview.getTeacherFirstname());

                                if(sessionManager.getLogin() && sessionManager.getUser().trim().equals("teacher")){
                                    Intent intent = new Intent(LoginTeacher.this, TeacherHomePage.class);

                                    //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        // Activity transition dito hehehe
                                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginTeacher.this).toBundle());
                                        finish(); //Iwas balik sa activity hehehee
                                    } else {
                                        // Edi walang transition
                                        startActivity(intent);
                                        finish(); //PAra ngaa iwas balikkk sa activity na itooo
                                    }
                                }
                            }
                            else{
                                //Then Pag invalid ito lalabas.. Honestly pwedeng wala na itong else eh
                                //Kasi may catch ako ehh dun ko pwede ipasok yung jsonObject error message natin
                                Toast.makeText(LoginTeacher.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginTeacher.this, "Login Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(LoginTeacher.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(LoginTeacher.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(LoginTeacher.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(LoginTeacher.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(LoginTeacher.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(LoginTeacher.this,"Login Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }) //End of Volley Error Response

            //Start of putting data in the hashmap for insertion into the database
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("teach_username",teach_username);
                params.put("teach_password",teach_password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}