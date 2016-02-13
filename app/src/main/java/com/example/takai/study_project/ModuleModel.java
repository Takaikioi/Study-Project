package com.example.takai.study_project;

/**
 * Created by hephalump on 31/12/2015.
 */
public class ModuleModel {

    private long moduleID;
    private String name;
    private int understanding;
    private CourseModel course;
    private long courseID;

    public ModuleModel(String name) {
        this.name = name;
        this.understanding = 0;

    }
    public ModuleModel(){

    }
    public int getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(int understanding) {
        this.understanding = understanding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long moduleID){
        this.moduleID = moduleID;
    }
    public long getId(){
        return  moduleID;
    }

    public void setCourseId(long courseID){
        this.courseID = courseID;
    }
    public long getCourseId(){
        return  courseID;
    }
}
