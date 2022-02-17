package com.group5.finalproject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuestionsBank {


    public static ArrayList<QuestionsList> SampleQuestion() {

        ArrayList<QuestionsList> questionsList = new ArrayList<>();

        //StudentHomePage homepage = new StudentHomePage();

        //ArrayList<Questions.QuestionsItem> que = new ArrayList<>(homepage.questions);

        final QuestionsList question1 = new QuestionsList("What is the most commonly used Programming Language", "Java","Python", "Html", "PHP", "Java", "");
        final QuestionsList question2 = new QuestionsList("This language use to style an HTML document", "JSON","CSS", "JavaScript", "PHP", "CSS", "");
        final QuestionsList question3 = new QuestionsList("It is recursive acronym for PHP", "Preprocessor Hypertext","Hypertext Preprocessor", "Hypertext Processor", "Text Processor", "Hypertext Preprocessor", "");
        final QuestionsList question4 = new QuestionsList("Python was created in year", "February 20, 1991","January 25, 1991", "February 20, 1992", "February 25, 1991", "February 20, 1991", "");
        final QuestionsList question5 = new QuestionsList("Java was first released in year", "1995","1996", "1997", "1998", "1995", "");
        final QuestionsList question6 = new QuestionsList("The first version of HTML was written in", "1993","1994", "1996", "1998", "1993", "");
        final QuestionsList question7 = new QuestionsList("____ is a general-purpose, multi-paradigm programming language", "C","C++", "C#", "C##", "C#", "");
        final QuestionsList question8 = new QuestionsList("Known as the scripting language for Web pages", "Java","JavaScript", "Html", "PHP", "JavaScript", "");
        final QuestionsList question9 = new QuestionsList("HTML is stands for ______text markup language", "High","Hi", "Hyper", "Hello", "Hyper", "");
        final QuestionsList question10 = new QuestionsList("XML stands for _____ markup language", "executable","extensible", "extended", "extension", "extensible", "");

        // ipasok sa questionlist yung mga quiz item
        questionsList.add(question1);
        questionsList.add(question2);
        questionsList.add(question3);
        questionsList.add(question4);
        questionsList.add(question5);
        questionsList.add(question6);
        questionsList.add(question7);
        questionsList.add(question8);
        questionsList.add(question9);
        questionsList.add(question10);


       /* for(Questions.QuestionsItem q:que){
            questionsList.add(new QuestionsList(
                    q.getQuestion(),
                    q.getChoiceA(),
                    q.getChoiceB(),
                    q.getChoiceC(),
                    q.getChoiceD(),
                    q.getAnswer(),
                    ""
            ));
        }*/
        return questionsList;
    }




    public static ArrayList<QuestionsList> getQuestions(){
        return SampleQuestion();
    }

}
