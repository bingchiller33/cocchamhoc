/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Yui
 */
public class Chapter {
    private int id;
    private int chapterNumber;
    private int courseId;
    private String name;

    public Chapter() {
    }
    
    public Chapter(int id, int number, int courseId, String name) {
        this.id = id;
        this.chapterNumber = number;
        this.courseId = courseId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chapter{" + "id=" + id + ", chapterNumber=" + chapterNumber + ", courseId=" + courseId + ", name=" + name + '}';
    }
    
    
    
    
}
