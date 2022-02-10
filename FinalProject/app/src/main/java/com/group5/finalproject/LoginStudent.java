package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginStudent extends AppCompatActivity {

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
    private ImageButton studentlogin; //Yung image button na may pangalan na Login
    private EditText et_studentId,et_studentpassword; //Yung dalawang parang textbox natin

    Quiview quiview = new Quiview(); //Yung Quiview Class na andito student and teachers data

    final String URL_studentlogin= "http://e2019cc107group5.000webhostapp.com/finalproject/student_login.php"; //URL ng database natin sa webhost

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts(); //Eh saaa gusto ko tanggalin yung status bar sa taas ahahaha
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        sessionManager = new SessionManager(this); //Yung context parameter ng constructor natin

        //Need natin kunin yung id dun sa xml file natin
        studentlogin = findViewById(R.id.login); //ImageButton natin
        tv_signup = findViewById(R.id.tv_signup); //TextView natin
        et_studentId = findViewById(R.id.et_studentID); //EditText ng StudentID natin
        et_studentpassword = findViewById(R.id.et_studentpassword); //EditText ng Password natin

        //Attempted Login pero ichecheck muna natin kung tama ba ang mga credentials base sa nandun
        //sa database natin
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hindi ito yung final na logic ahh
                //Pasok laman ng edit text sa ating getters and setters
                quiview.setStudent_id(et_studentId.getText().toString().trim()); //StudentID na tinype
                quiview.setStudentPassword(et_studentpassword.getText().toString().trim()); //Password na tinype

                //Ah basta kapag may laman ang username and password go na pag wala edi yung else
                //Pero seriously, sa database tayo kukuha nung laman hayyy buhay

                String stud_id = quiview.getStudentId();
                String stud_password = quiview.getStudentPassword();

                if(TextUtils.isEmpty(stud_id.trim())){
                    //Set lang tayo ng error
                    et_studentId.setError("Please Enter Your Student ID");
                    et_studentId.requestFocus();
                }
                if(TextUtils.isEmpty(stud_password.trim())){
                    et_studentpassword.setError("Please Enter Your Password");
                    et_studentpassword.requestFocus();
                }
                else if(!(TextUtils.isEmpty(stud_id.trim())) && !(TextUtils.isEmpty(stud_password.trim()))){
                    //Yung login function with student id and student password na parameter
                    Login(stud_id,stud_password);
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
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginStudent.this).toBundle());
                    finish(); //Iwas balik sa activity hehehee
                } else {
                    // Edi walang transition
                    startActivity(intent);
                    finish(); //PAra ngaa iwas balikkk sa activity na itooo
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

    private void Login(String stud_id, String stud_password){
        //Another String Request using Volley hehehe :)
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_studentlogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Ipasok yung response sa ating JSONObject
                            JSONObject jsonObject = new JSONObject(response);
                            //Gamitin ang JSONObject para makuha ang JSONArray
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            String success = jsonObject.getString("success");
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

                                //Kukunin natin ang values ng array na associated dun sa username and password sa php
                                for(int i = 0; i < jsonArray.length();i++) {

                                    //Kukuhanin ko yung array dun sa login sa php heheheh then pasok ko dito
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    //stud_surname
                                    String s_sname = object.getString("stud_surname").trim();

                                    //stud_firstname
                                    String s_fname = object.getString("stud_firstname").trim();

                                    //stud_email
                                    String s_email = object.getString("stud_email").trim();

                                    //stud_id
                                    String s_id = object.getString("stud_id").trim();

                                    quiview.setStudent_id(s_id);
                                    quiview.setStudentEmail(s_email);
                                    quiview.setStudentSurname(s_sname);
                                    quiview.setStudentFirstname(s_fname);

                                }
                                Toast.makeText(LoginStudent.this,jsonObject.getString("message") + " " + quiview.getStudentFirstname() + " " + quiview.getStudentSurname(),Toast.LENGTH_SHORT).show();

                                //Session Manager ng Student natin using Shared Preference
                                sessionManager.setLogin(true);
                                sessionManager.setUser("student");
                                sessionManager.setUsername(quiview.getStudentId());
                                sessionManager.setEmail(quiview.getStudentEmail());
                                sessionManager.setSurname(quiview.getStudentSurname());
                                sessionManager.setFirstname(quiview.getStudentFirstname());

                                if(sessionManager.getLogin() && sessionManager.getUser().trim().equals("student")){
                                    Intent intent = new Intent(LoginStudent.this, StudentHomePage.class);
                                    //Kung ang build version daw ay more than lollipop "android 5.0" edi goods sa transition sabi sa docs ahh
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        // Activity transition dito hehehe

                                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginStudent.this).toBundle());
                                        finish(); //Iwas balik sa activity hehehee
                                    } else {
                                        // Edi walang transition

                                        startActivity(intent);
                                        finish(); //Para ngaa iwas balikkk sa activity na itooo
                                    }
                                }
                            }
                            else{
                                //Then Pag invalid ito lalabas.. Honestly pwedeng wala na itong else eh
                                //Kasi may catch ako ehh dun ko pwede ipasok yung jsonObject error message natin
                                Toast.makeText(LoginStudent.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginStudent.this,"Login Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(LoginStudent.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(LoginStudent.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(LoginStudent.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(LoginStudent.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(LoginStudent.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginStudent.this,"Login Error!",Toast.LENGTH_SHORT).show();
                        }
                }}) //End of Volley Error Response

            //Start of putting data in hashmap for getting ready in inserting to database
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("stud_id",stud_id);
                params.put("stud_password",stud_password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
