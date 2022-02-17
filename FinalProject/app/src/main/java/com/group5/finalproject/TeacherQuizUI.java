package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class TeacherQuizUI extends AppCompatActivity {

    private TextView questions;
    private TextView question;

    private int totalTimeInMins = 10;
    private int second = 0;

    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton btn_next;

    private ArrayList<QuestionsList> questionsList;

    private int currentQuestionPosition = 0;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_quiz_ui);

        progressBar = findViewById(R.id.progressBar);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        btn_next = findViewById(R.id.btn_next);

        questionsList = QuestionsBank.getQuestions();

        // We need to set color for our options
        option1.setBackgroundResource(R.drawable.ic_choices_btn);
        option1.setTextColor(Color.BLACK);
        option2.setBackgroundResource(R.drawable.ic_choices_btn);
        option2.setTextColor(Color.BLACK);
        option3.setBackgroundResource(R.drawable.ic_choices_btn);
        option3.setTextColor(Color.BLACK);
        option4.setBackgroundResource(R.drawable.ic_choices_btn);
        option4.setTextColor(Color.BLACK);

        questions.setText((currentQuestionPosition+1)+"/"+questionsList.size());
        Collections.shuffle(questionsList);

        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());

        revealAnswer();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setProgress(currentQuestionPosition);
                progressBar.setMax(questionsList.size());

                // function to go to the next question
                    changeNextQuestion();
            }
        });


    }
    private void changeNextQuestion(){
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionsList.size()){
            btn_next.setText("Finish");
        }
        if(currentQuestionPosition < questionsList.size()){

            option1.setBackgroundResource(R.drawable.ic_choices_btn);
            option1.setTextColor(Color.BLACK);
            option2.setBackgroundResource(R.drawable.ic_choices_btn);
            option2.setTextColor(Color.BLACK);
            option3.setBackgroundResource(R.drawable.ic_choices_btn);
            option3.setTextColor(Color.BLACK);
            option4.setBackgroundResource(R.drawable.ic_choices_btn);
            option4.setTextColor(Color.BLACK);

            questions.setText(("Question: "+(currentQuestionPosition+1)+"/"+questionsList.size()));
            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
            option4.setText(questionsList.get(currentQuestionPosition).getOption4());

            revealAnswer();
        }
        else {
            Intent  intent = new Intent(TeacherQuizUI.this, TeacherHomePage.class);
            startActivity(intent);
            finish();
        }
    }


    // ito yung pang reveal ng answer kung natapos na yung quiz
    private void revealAnswer(){
        final String getAnswer = questionsList.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals((getAnswer))) {
            option1.setBackgroundResource(R.drawable.option_green_bg);
            option1.setTextColor(Color.WHITE);
        }
        else if (option2.getText().toString().equals((getAnswer))) {
            option2.setBackgroundResource(R.drawable.option_green_bg);
            option2.setTextColor(Color.WHITE);
        }else if (option3.getText().toString().equals((getAnswer))) {
            option3.setBackgroundResource(R.drawable.option_green_bg);
            option3.setTextColor(Color.WHITE);
        }else if (option4.getText().toString().equals((getAnswer))) {
            option4.setBackgroundResource(R.drawable.option_green_bg);
            option4.setTextColor(Color.WHITE);
        }
    }
}