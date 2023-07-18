/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LAPTOP
 */
public class Rate {

    private int ratingId;
    private int userId;
    private int courseId;
    private int rateNo;

    public int getRatingId() {
        return ratingId;
    }
    private Date rateDate;
    private String review;
    private boolean isReported;

    public Rate() {
    }

    public Rate(int ratingId, int userId, int courseId, int rateNo, Date rateDate, String review, boolean isReported) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.courseId = courseId;
        this.rateNo = rateNo;
        this.rateDate = rateDate;
        this.review = review;
        this.isReported = isReported;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public boolean isIsReported() {
        return isReported;
    }

    public void setIsReported(boolean isReported) {
        this.isReported = isReported;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRateNo() {
        return rateNo;
    }

    public void setRateNo(int rateNo) {
        this.rateNo = rateNo;
    }

}
