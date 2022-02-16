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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
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
import java.util.Map;

public class StudentHomePage extends AppCompatActivity implements RecyclerViewInterface{

    ImageView student_profile, join_classes, btn_dialogcancel;
    Button btn_joinquiz; // Button para sa join quiz ng dialog

    TextView et_joinLink;

    SessionManager sessionManager;

    //Custom Arraylist Question Objects
    ArrayList<Questions.QuestionsItem> questions = new ArrayList<>();

    //URL of the join link
    String URL_joinquiz = "http://e2019cc107group5.000webhostapp.com/finalproject/join_quiz.php"; //URL ng join quiz php file natin sa webhost

    // Sample code palang to dun sa classes -------------------------------------------------------
    String []data = {"Mobile Programming", "Micro-controller", "Software Engineering"};
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


//-------- sample code para sa classes ------------------------------------------------------------




//----- end of classes ----------------------------------------------------------------------------

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
                items.add(data[cntr]);
                cntr++;
                adapter.notifyItemInserted(items.size()-1);
                joinDialog.closeOptionsMenu();

                //Gagamit ako ng JSON object and array inside ng string request
                try{
                    joinquiz();
                    joinDialog.dismiss();
                }
                catch(Exception e){
                    Toast.makeText(StudentHomePage.this,e.toString(),Toast.LENGTH_LONG).show();
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
        StringRequest stringRequest = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, URL_joinquiz,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting quiz object from json array
                                JSONObject quiz = array.getJSONObject(i);

                                String question = String.valueOf(quiz.get("quiz_questions"));
                                String choice_A = String.valueOf(quiz.get("choice_a"));
                                String choice_B = String.valueOf(quiz.get("choice_b"));
                                String choice_C = String.valueOf(quiz.get("choice_c"));
                                String choice_D = String.valueOf(quiz.get("choice_d"));
                                String answer = String.valueOf(quiz.get("answer"));
                                Log.d("HAHAHA: ", question);
                                Log.d("HEHEHE: ", answer);
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
                            Log.d("QUEST: ", questions.toString());
                            Log.d("response: ", response);
                        }
                        catch (JSONException e){
                            Toast.makeText(StudentHomePage.this,e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentHomePage.this,error.toString(),Toast.LENGTH_LONG).show();
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
        //"My6XnD"
    }
}