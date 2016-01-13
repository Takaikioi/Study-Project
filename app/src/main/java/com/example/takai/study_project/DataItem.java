package com.example.takai.study_project;

/**
 * Created by hephalump on 30/12/2015
 */
public class DataItem {

    private long id;
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Will be used by the GeneralArrayAdapter in the ListView
    @Override
    public String toString() {
        return data;
    }

}
