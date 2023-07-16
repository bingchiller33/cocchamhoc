/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Yui
 */
public class Course {
    private int id;
    private String title;
    private String description;
    private String imgUrl;
    private Date publishDate;
    private boolean isDiscontinued;
    private int newVersionId;
    private int durationInSeconds;
    private String lecturer;
    private Level level;
    private Category category;
    
    public Course() {
    }

    public Course(int id, String title, String description, String imgUrl, Date publishDate, boolean isDiscontinued, int newVersionId, String lecturer, Level level, Category category, int durationInSeconds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.publishDate = publishDate;
        this.isDiscontinued = isDiscontinued;
        this.newVersionId = newVersionId;
        this.lecturer = lecturer;
        this.level = level;
        this.category = category;
        this.durationInSeconds = durationInSeconds;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public boolean isIsDiscontinued() {
        return isDiscontinued;
    }

    public void setIsDiscontinued(boolean isDiscontinued) {
        this.isDiscontinued = isDiscontinued;
    }

    public int getNewVersionId() {
        return newVersionId;
    }

    public void setNewVersionId(int newVersionId) {
        this.newVersionId = newVersionId;
    }

    
    
    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", description=" + description + ", imgUrl=" + imgUrl + ", publishDate=" + publishDate + ", duration=" + durationInSeconds + ", lecturer=" + lecturer + ", level=" + level + ", category=" + category + '}';
    }

    
    
    
    
}

