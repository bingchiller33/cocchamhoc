/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Phuoc
 */
public class QuestionCRUD {

    private int questionId;
    private String questionDetail;
    private int examId;

    public QuestionCRUD() {
    }

    public QuestionCRUD(int questionId, String questionDetail, int examId) {
        this.questionId = questionId;
        this.questionDetail = questionDetail;
        this.examId = examId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Override
    public String toString() {
        return "Question{" + "questionId=" + questionId + ", questionDetail=" + questionDetail + ", examId=" + examId + '}';
    }

}
