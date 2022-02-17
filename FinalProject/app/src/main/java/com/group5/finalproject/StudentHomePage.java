package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Slide;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StudentHomePage extends AppCompatActivity implements RecyclerViewInterface{

    ImageView student_profile, join_classes, btn_dialogcancel;
    Button btn_joinquiz; // Button para sa join quiz ng dialog

    TextView et_joinLink;

    SessionManager sessionManager;

    //Custom Arraylist Question Objects
    ArrayList<Questions.QuestionsItem> questions = new ArrayList<>();
    ArrayList<QuestionsList> questi = new ArrayList<>();

    //URL of the join link
    String URL_joinquiz = "http://e2019cc107group5.000webhostapp.com/finalproject/join_quiz.php"; //URL ng join quiz php file natin sa webhost

    // Sample code palang to dun sa classes -------------------------------------------------------
    String []data = {"Questions"};
    int cntr = 0;

    // --------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_student_home_page);

        sessionManager = new SessionManager(this);

        student_profile = (ImageView) findViewById(R.id.image_profile_student); //Image natin

        student_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Pupunta sa Next activity which is yung student profile
                    Intent student_profile = new Intent(StudentHomePage.this, MyProfileStudent.class);

                    //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // Activity transition dito hehehe
                        startActivity(student_profile, ActivityOptions.makeSceneTransitionAnimation(StudentHomePage.this).toBundle());
                        finish();
                    } else {
                        // Edi walang transition
                        startActivity(student_profile);
                        finish();
                    }
            }
        });

//--------------- Custom Dialog for Class Link ----------------------------------------------------
        join_classes = (ImageView) findViewById(R.id.student_joinquiz); // Button Join Quiz

        join_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(); //
            }
        });
//*--------end of Custom Dialog --------------------------------------------------------------------


    }
    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }

    // May bugs pa! 
    public void openDialog() {
        Dialog joinDialog = new Dialog(this);
        joinDialog.setContentView(R.layout.layout_dialog_join);
        joinDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        joinDialog.setCanceledOnTouchOutside(false); // para hnd mawala yung dialog kapag na click sa sa labas ng dialog
        joinDialog.show();

        List<String> items = new LinkedList<>();

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items, this);
        recyclerView.setAdapter(adapter);


        et_joinLink = (TextView)  joinDialog.findViewById(R.id.et_joinLink);
        btn_dialogcancel = (ImageView) joinDialog.findViewById(R.id.btn_dialogcancel);
        btn_joinquiz = (Button) joinDialog.findViewById(R.id.btn_join);

        btn_dialogcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinDialog.dismiss(); // pag click ng close
            }
        });

        btn_joinquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // function here for joining new quiz
                joinDialog.dismiss();

                //Gagamit ako ng JSON object and array inside ng string request
                if(TextUtils.isEmpty(et_joinLink.getText().toString().trim())){
                    joinDialog.dismiss();
                    Toast.makeText(StudentHomePage.this,"Enter Quiz Link",Toast.LENGTH_LONG).show();
                }
                else{
                    //Gagamit ako ng JSON object and array inside ng string request
                    try{
                        // function here for joining new quiz
                        items.add(data[cntr%3]);
                        cntr++;
                        adapter.notifyItemInserted(items.size()-1);
                        joinquiz();
                        joinDialog.dismiss();
                    }
                    catch(Exception e){
                        Toast.makeText(StudentHomePage.this,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(StudentHomePage.this,QuizUI.class);
        startActivity(intent);
    }

    private void joinquiz() throws JSONException{
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_joinquiz,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("PHP RESPONSE: ",response);
                        try {
                            
                            Toast.makeText(StudentHomePage.this,response,Toast.LENGTH_LONG).show();
                            JSONObject obj = new JSONObject(response); //Ipasok ang response here
                            Log.d("OBJECT: ",obj.toString());
                            JSONArray array = obj.getJSONArray("quizzes"); //Quizzes array dun sa php
                            String success = obj.getString("success"); // dun sa php
                            //traversing through all the object
                            if(success.equals("1")){
                                Toast.makeText(StudentHomePage.this,obj.getString("message"),Toast.LENGTH_LONG).show();
                                for (int i = 0; i < array.length(); i++) {

                                    //getting quiz object from json array
                                    JSONObject quiz = array.getJSONObject(i);

                                    String question = quiz.getString("quiz_questions");
                                    String choice_A = quiz.getString("choice_a");
                                    String choice_B = quiz.getString("choice_b");
                                    String choice_C = quiz.getString("choice_c");
                                    String choice_D = quiz.getString("choice_d");
                                    String answer = quiz.getString("answer");

                                    //Lagay ang quiz sa custom objects natin
                                    questions.add(new Questions.QuestionsItem(
                                            "",
                                            question,
                                            choice_A,
                                            choice_B,
                                            choice_C,
                                            choice_D,
                                            answer
                                    ));
                                }

                                for(Questions.QuestionsItem que:questions){
                                    Log.d("QUESTIONS: ",que.getQuestion());
                                    Log.d("CHOICE A: ",que.getChoiceA());
                                    Log.d("CHOICE B: ",que.getChoiceB());
                                    Log.d("CHOICE C: ",que.getChoiceC());
                                    Log.d("CHOICE D: ",que.getChoiceD());
                                    Log.d("ANSWER: ",que.getAnswer());
                                }
                            }
                            else{
                                Toast.makeText(StudentHomePage.this,obj.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            //Toast.makeText(StudentHomePage.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(StudentHomePage.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(StudentHomePage.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(StudentHomePage.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(StudentHomePage.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(StudentHomePage.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(StudentHomePage.this,"Login Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("joinlink",et_joinLink.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        //FCql42
    }
}