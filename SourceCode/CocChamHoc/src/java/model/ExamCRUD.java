package model;

import java.sql.Time;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Phuoc
 */
public class ExamCRUD {
    private int id;
    private String examName;
    private int courseId;
    private String durationInSecond;

    public ExamCRUD() {
    }

    public ExamCRUD(int id, String examName, int courseId, String durationInSecond) {
        this.id = id;
        this.examName = examName;
        this.courseId = courseId;
        this.durationInSecond = durationInSecond;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDurationInSecond() {
        return durationInSecond;
    }

    public void setDurationInSecond(String durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", examName=" + examName + ", courseId=" + courseId + ", durationInSecond=" + durationInSecond + '}';
    }
    
}
