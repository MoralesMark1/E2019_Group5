package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
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

    String URL_studentlogin= "http://e2019cc107group5.000webhostapp.com/finalproject/student_login.php"; //URL ng database natin sa webhost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layouts(); //Eh saaa gusto ko tanggalin yung status bar sa taas ahahaha
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

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

                if(!stud_id.isEmpty() || !stud_password.isEmpty()){
                    //Yung login function with student id and student password na parameter
                    Login(stud_id,stud_password);
                }
                else{
                    //Set lang tayo ng error
                    et_studentId.setError("Please Enter Your Student ID");
                    et_studentpassword.setError("Please Enter Your Password");
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
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            //JSONArray jsonArray = jsonObject.getJSONArray("login");
                            Log.i("tagconvertstr", "["+response+"]");

                            if(success.equals("1")){
                                //So may problem pala kapag JSONArray ang gagamitin hayyyy
                                /*
                                for(int i = 0; i < jsonArray.length();i++) {

                                    //Kukuhanin ko yung array dun sa login sa php heheheh then pasok ko dito
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    //teach_surname
                                    String s_sname = object.getString("stud_surname").trim();

                                    //teach_firstname
                                    String s_fname = object.getString("stud_firstname").trim();

                                }
                                 */
                                Toast.makeText(LoginStudent.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                //Intent to Student Home Page Here
                            }
                            else{
                                //Then Pag invalid ito lalabas.. Honestly pwedeng wala na itong else eh
                                //Kasi may catch ako ehh dun ko pwede ipasok yung jsonObject error message natin
                                Toast.makeText(LoginStudent.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginStudent.this,"Login Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(LoginStudent.this,"Login Error: " + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
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
