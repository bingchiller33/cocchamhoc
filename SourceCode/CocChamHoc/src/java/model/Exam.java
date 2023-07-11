/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author Viet
 */
public class Exam {
    private int examID;
    private String examName;
    private int courseID;
    private Time duration;

    public Exam(int examID, String examName, int courseID, Time duration) {
        this.examID = examID;
        this.examName = examName;
        this.courseID = courseID;
        this.duration = duration;
    }

    public int getExamID() {
        return examID;
    }
    
    public Exam() {
    }

    public void setExamID(int examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
    
}
