package com.example.takai.study_project;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hephalump on 31/12/2015.
 */
public class CourseModel {

    private long courseID;
    private String code;
    private String name;
    private Integer courseColor;
    private boolean active;
    private Date startDate;
    private Date endDate;
    private SimpleDateFormat simpleStartDate = new SimpleDateFormat("yyyy-MM-dd");

    public CourseModel(String code, String name, int courseColor, boolean active, Date startDate, Date endDate){
        this.code = code;
        this.name = name;
        this.courseColor = courseColor;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CourseModel(String code, String name, int courseColor, Date startDate){
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

    public int getCourseColor() {
        return courseColor;
    }

    public void setCourseColor(int courseColor) {
        this.courseColor = courseColor;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStartDate() {
        return this.startDate.toString();
    }

    public void setStartDate(String startDate) {
        this.startDate = java.sql.Date.valueOf(startDate);
    }

    public String getEndDate() {
        return this.endDate.toString();
    }

    public void setEndDate(String endDate) {
        this.endDate = java.sql.Date.valueOf(endDate);
    }

    public String toString(){
        return this.name;
    }
}
