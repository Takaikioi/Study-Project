package com.example.takai.study_project;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public  String string;
    public int colour;
    public String code;
    public boolean deleteHidden;
    public final List<String> children = new ArrayList<String>();
    public final List<Integer> colorchildren = new ArrayList<Integer>();

    public Group(String string, int colour, String code, boolean deleteHidden) {
        this.string = string;
        this.colour = colour;
        this.code = code;
        this.deleteHidden = deleteHidden;
    }
}