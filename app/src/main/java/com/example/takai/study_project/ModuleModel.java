package com.example.takai.study_project;

/**
 * Created by hephalump on 31/12/2015.
 */
public class ModuleModel {

    private static final int MODULEID = 0;
    private String name;
    private int understanding;
    private CourseModel course;

    public ModuleModel(String name) {
        this.name = name;
        this.understanding = 0;

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

    public static int getMODULEID() {
        return MODULEID;
    }
}
