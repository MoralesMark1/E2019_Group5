package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizUI extends AppCompatActivity {
    private TextView questions;
    private TextView question;

    private Timer quizTimer;
    private int totalTimeInMins = 10;
    private int second = 0;

    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton btn_next;

    private ArrayList<QuestionsList> questionsList;

    private String selectedOptionbyUser ="";
    private int currentQuestionPosition = 0;

    TextView correctAnswer;
    AppCompatButton done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_ui2);

        final TextView timer = findViewById(R.id.timer);

        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        btn_next = findViewById(R.id.btn_next);

        questionsList = QuestionsBank.getQuestions();

        startTimer(timer);

        questions.setText((currentQuestionPosition+1)+"/"+questionsList.size());
        Collections.shuffle(questionsList);
        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionbyUser = "";

                option2.setBackgroundResource(R.drawable.ic_choices_btn);
                option2.setTextColor(Color.BLACK);
                option3.setBackgroundResource(R.drawable.ic_choices_btn);
                option3.setTextColor(Color.BLACK);
                option4.setBackgroundResource(R.drawable.ic_choices_btn);
                option4.setTextColor(Color.BLACK);

                if(selectedOptionbyUser.isEmpty()){

                    selectedOptionbyUser = option1.getText().toString();

                    option1.setBackgroundResource(R.drawable.option_selected_bg);
                    option1.setTextColor(Color.WHITE);


                    //revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer((selectedOptionbyUser));

                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionbyUser = "";

                option1.setBackgroundResource(R.drawable.ic_choices_btn);
                option1.setTextColor(Color.BLACK);
                option3.setBackgroundResource(R.drawable.ic_choices_btn);
                option3.setTextColor(Color.BLACK);
                option4.setBackgroundResource(R.drawable.ic_choices_btn);
                option4.setTextColor(Color.BLACK);

                if(selectedOptionbyUser.isEmpty()){

                    selectedOptionbyUser = option2.getText().toString();

                    option2.setBackgroundResource(R.drawable.option_selected_bg);
                    option2.setTextColor(Color.WHITE);


                    //revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer((selectedOptionbyUser));

                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionbyUser = "";

                option2.setBackgroundResource(R.drawable.ic_choices_btn);
                option2.setTextColor(Color.BLACK);
                option1.setBackgroundResource(R.drawable.ic_choices_btn);
                option1.setTextColor(Color.BLACK);
                option4.setBackgroundResource(R.drawable.ic_choices_btn);
                option4.setTextColor(Color.BLACK);

                if(selectedOptionbyUser.isEmpty()){

                    selectedOptionbyUser = option3.getText().toString();

                    option3.setBackgroundResource(R.drawable.option_selected_bg);
                    option3.setTextColor(Color.WHITE);


                    //revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer((selectedOptionbyUser));

                }

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedOptionbyUser = "";

                option2.setBackgroundResource(R.drawable.ic_choices_btn);
                option2.setTextColor(Color.BLACK);
                option3.setBackgroundResource(R.drawable.ic_choices_btn);
                option3.setTextColor(Color.BLACK);
                option1.setBackgroundResource(R.drawable.ic_choices_btn);
                option1.setTextColor(Color.BLACK);

                if(selectedOptionbyUser.isEmpty()){

                    selectedOptionbyUser = option4.getText().toString();

                    option4.setBackgroundResource(R.drawable.option_selected_bg);
                    option4.setTextColor(Color.WHITE);


                    //revealAnswer();

                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer((selectedOptionbyUser));

                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(selectedOptionbyUser.isEmpty()){
                    Toast.makeText(QuizUI.this, "Select your Answer!",Toast.LENGTH_SHORT).show();
                }
                else {
                    changeNextQuestion();
                }

            }
        });



    }
    private void changeNextQuestion(){
        currentQuestionPosition++;

        if ((currentQuestionPosition+1) == questionsList.size()){
            btn_next.setText("Submit and Finish");
        }
        if(currentQuestionPosition < questionsList.size()){

            selectedOptionbyUser = "";

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
            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
            option4.setText(questionsList.get(currentQuestionPosition).getOption4());
        }
        else {
            openDialog(getCorrectAnswers());
        }
    }

    public void openDialog(int getCorrectAnswers) {
        Dialog resultDialog = new Dialog(this);
        resultDialog.setContentView(R.layout.activity_quiz_results);
        resultDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        resultDialog.setCanceledOnTouchOutside(false); // para hnd mawala yung dialog kapag na click sa sa labas ng dialog
        resultDialog.show();

        correctAnswer = (TextView) resultDialog.findViewById(R.id.tv_correct_answers);
        correctAnswer.setText(String.valueOf(getCorrectAnswers)+"/"+questionsList.size());

        done = (AppCompatButton) resultDialog.findViewById(R.id.btn_done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(QuizUI.this, StudentHomePage.class);
                //intent.putExtra("correct", getCorrectAnswers());
                //intent.putExtra("incorrect", getInCorrectAnswers());
                startActivity(intent);
                finish();
                resultDialog.dismiss(); // pag click ng close
            }
        });


    }

    private void startTimer(TextView timerTextView){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(second == 0){
                    totalTimeInMins--;
                    second =59;
                } else if(second == 0 && totalTimeInMins == 0){

                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(QuizUI.this, "Time Over",Toast.LENGTH_SHORT).show();

                    openDialog(getCorrectAnswers());
                }
                else{
                    second--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         String finalMinutes = String.valueOf(totalTimeInMins);
                         String finalSeconds = String.valueOf(second);

                        if (finalMinutes.length() == 1){
                            finalMinutes = "0"+finalMinutes;
                        }
                        if (finalSeconds.length() == 1){
                            finalSeconds ="0"+finalSeconds;
                        }

                        timerTextView.setText(finalMinutes +":"+finalSeconds);
                    }
                });
            }
        }, 1000, 1000);
    }

    private int getCorrectAnswers(){
        int correctAnswer = 0;

        for(int i=0; i<questionsList.size(); i++){

            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++;
            }
        }
        return correctAnswer;
    }

    private int getInCorrectAnswers(){
        int correctAnswer = 0;

        for(int i=0; i<questionsList.size(); i++){

            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++;
            }
        }
        return correctAnswer;
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