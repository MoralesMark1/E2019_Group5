package com.group5.finalproject;

//Ito ay para sa pagretrieve ng data galing sa excel
public class Questions {

        private String quizlink;
        private String question;
        private String choiceA;
        private String choiceB;
        private String choiceC;
        private String choiceD;
        private String answer;

        public Questions(String quizlink,String question,String choiceA,String choiceB,String choiceC,String choiceD,String answer){
            this.quizlink = quizlink;
            this.question = question;
            this.choiceA = choiceA;
            this.choiceB = choiceB;
            this.choiceC = choiceC;
            this.choiceD = choiceD;
            this.answer = answer;

        }

        public String getQuizlink(){
            return quizlink;
        }
        public void setQuizlink(String quizlink){
            this.quizlink = quizlink;
        }
        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoiceA() {
            return choiceA;
        }

        public void setChoiceA(String choiceA) {
            this.choiceA = choiceA;
        }

        public String getChoiceB() {
            return choiceB;
        }

        public void setChoiceB(String choiceB) {
            this.choiceB = choiceB;
        }

        public String getChoiceC() {
            return choiceC;
        }

        public void setChoiceC(String choiceC) {
            this.choiceC = choiceC;
        }

        public String getChoiceD() {
            return choiceD;
        }

        public void setChoiceD(String choiceD) {
            this.choiceD = choiceD;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }








