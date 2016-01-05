package com.example.takai.study_project;

import java.util.Date;

/**
 * Created by hephalump on 31/12/2015.
 */
public abstract class TimetableElementModel {

    protected int TTEID;
    private String name;
    private boolean recurring;
    private Date startTime;
    private Date endTime;

    public TimetableElementModel(String name, int TTEID) {
        this.name = name;
        this.TTEID = TTEID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
