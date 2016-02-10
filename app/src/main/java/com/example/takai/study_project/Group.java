package com.example.takai.study_project;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public  String string;
    public int colour;
    public final List<String> children = new ArrayList<String>();
    public final List<Integer> colorchildren = new ArrayList<Integer>();

    public Group(String string, int colour) {
        this.string = string;
        this.colour = colour;
    }
}