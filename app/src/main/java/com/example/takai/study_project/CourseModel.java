package com.example.takai.study_project;

import android.graphics.Color;

import java.util.Date;

/**
 * Created by hephalump on 31/12/2015.
 */
public class CourseModel {

    private long courseID;
    private String code;
    private String name;

    private Color courseColor;
    private boolean active;
    private Date startDate;
    private Date endDate;

    public CourseModel(String code, String name, Color courseColor, boolean active, Date startDate, Date endDate){
        this.code = code;
        this.name = name;
        this.courseColor = courseColor;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CourseModel(String code, String name, Color courseColor, Date startDate){
        this.code = code;
        this.name = name;
        this.courseColor = courseColor;
        this.active = true;
        this.startDate = startDate;
    }
    public CourseModel(){
    }
    public void setId(long courseID){
        this.courseID = courseID;
    }
    public long getId(){
        return  courseID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getCourseColor() {
        return courseColor;
    }

    public void setCourseColor(Color courseColor) {
        this.courseColor = courseColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String toString(){
        return this.name;
    }
}
