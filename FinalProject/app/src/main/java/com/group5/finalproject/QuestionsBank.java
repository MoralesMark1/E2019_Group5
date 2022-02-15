package com.group5.finalproject;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {


    private static List<QuestionsList> SampleQuestion() {

        final List<QuestionsList> questionsListList = new ArrayList<>();

        final QuestionsList question1 = new QuestionsList("What is the most common PL", "Java","Python", "Html", "Php", "Java", "");
        final QuestionsList question2 = new QuestionsList("How many star in Our Flag", "4","6", "8", "3", "3", "");
        final QuestionsList question3 = new QuestionsList("Our National Hero", "Rizal","Bonifacio", "Luna", "Lapu-Lapu", "Rizal", "");
        final QuestionsList question4 = new QuestionsList("Python was created in year", "February 20, 1991","January 25, 1991", "February 20, 1992", "February 25, 1991", "February 20, 1991", "");
        final QuestionsList question5 = new QuestionsList("Java was first released", "1995","1996", "1997", "1998", "1995", "");
        final QuestionsList question6 = new QuestionsList("The first version of HTML was written by Tim Berners-Lee in ", "1993","1994", "1996", "1998", "1993", "");
        final QuestionsList question7 = new QuestionsList("Our National Hero", "Rizal","Bonifacio", "Luna", "Lapu-Lapu", "Rizal", "");
        final QuestionsList question8 = new QuestionsList("What is the most common PL", "Java","Python", "Html", "Php", "Java", "");
        final QuestionsList question9 = new QuestionsList("How many star in Our Flag", "4","6", "8", "3", "3", "");
        final QuestionsList question10 = new QuestionsList("What is the most common PL", "Java","Python", "Html", "Php", "Java", "");

        // ipasok sa questionlist yung mga quiz item
        questionsListList.add(question1);
        questionsListList.add(question2);
        questionsListList.add(question3);
        questionsListList.add(question4);
        questionsListList.add(question5);
        questionsListList.add(question6);
        questionsListList.add(question7);
        questionsListList.add(question8);
        questionsListList.add(question9);
        questionsListList.add(question10);

        return questionsListList;
    }

    public static List<QuestionsList> getQuestions(){
        return SampleQuestion();
    }
}
