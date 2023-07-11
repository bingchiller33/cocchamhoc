/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Phuoc
 */
public class ChoiceCRUD {
    private int choiceId;
    private int questionId;
    private boolean isTrueAnswer;
    private String  description;

    public ChoiceCRUD() {
    }

    public ChoiceCRUD(int choiceId, int questionId, boolean isTrueAnswer, String description) {
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.isTrueAnswer = isTrueAnswer;
        this.description = description;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isIsTrueAnswer() {
        return isTrueAnswer;
    }

    public void setIsTrueAnswer(boolean isTrueAnswer) {
        this.isTrueAnswer = isTrueAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Choice{" + "choiceId=" + choiceId + ", questionId=" + questionId + ", isTrueAnswer=" + isTrueAnswer + ", description=" + description + '}';
    }
    
}
