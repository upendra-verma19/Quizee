package com.quizee;


public class QuestionLayout {
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionLayout() {
    }

//    public QuestionLayout(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
//        this.question = question;
//        this.optionA = optionA;
//        this.optionB = optionB;
//        this.optionC = optionC;
//        this.optionD = optionD;
//        this.answer = answer;
//    }


    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAnswer() {
        return answer;
    }
}
