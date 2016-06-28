package com.example.takai.study_project;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
// a dataset object for use in the course page
public class Group {
    public  String string;
    public int colour;
    public String code;
    public final List<String> children = new ArrayList<String>();
    public final List<Integer> colorchildren = new ArrayList<Integer>();

    public Group(String string, int colour, String code) {
        this.string = string;
        this.colour = colour;
        this.code = code;
    }
    public Group(String string){
        this.string = string;
    }
}