/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Viet
 */
public class Choice {
    private int choiceID;
    private int questionID;
    private String description;
    private boolean isTrueAnswer;

    public Choice(int choiceID, int questionID, String description, boolean isTrueAnswer) {
        this.choiceID = choiceID;
        this.questionID = questionID;
        this.description = description;
        this.isTrueAnswer = isTrueAnswer;
    }

    public Choice() {
    }

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isIsTrueAnswer() {
        return isTrueAnswer;
    }

    public void setIsTrueAnswer(boolean isTrueAnswer) {
        this.isTrueAnswer = isTrueAnswer;
    }
    
}
