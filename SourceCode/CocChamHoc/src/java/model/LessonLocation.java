/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Yui
 */
public class LessonLocation {
    int courseId;
    int chapterId;
    int lessonNumber;

    public LessonLocation() {
    }
    
    public LessonLocation(int courseId, int chapterId, int lessonNumber) {
        this.courseId = courseId;
        this.chapterId = chapterId;
        this.lessonNumber = lessonNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}
