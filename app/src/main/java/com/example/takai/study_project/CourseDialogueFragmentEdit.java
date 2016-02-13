package com.example.takai.study_project;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;

/**
 * Created by hephalump on 11/02/2016
 */
public class CourseDialogueFragmentEdit extends DialogFragment{
    private EditText courseName;
    private EditText courseCode;
    private EditText courseActive;
    private Button submitButton;
    private Button cancelButton;
    private LineColorPicker colorPicker;
    private Bundle arguments;
    private CourseDataSource dataSource; // database helper
    List<CourseModel> courseModels = // list of data objects
            new ArrayList<CourseModel>();


    public CourseDialogueFragmentEdit(){

    }

    interface UpdateDetails {
        void onFinishUserDialog(String name, String code, int color, int position);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        arguments = getArguments();
        View view = inflater.inflate(R.layout.course_add_dialog_layout, container);
        courseCode = (EditText) view.findViewById(R.id.courseCode);
        courseCode.setText(arguments.getString("code"));
        courseName = (EditText) view.findViewById(R.id.courseName);
        courseName.setText(arguments.getString("name"));
        submitButton = (Button) view.findViewById(R.id.buttonSubmit);
        submitButton.setText("SAVE");
        cancelButton = (Button) view.findViewById(R.id.buttonCancel);
        cancelButton.setText("CANCEL");
        colorPicker = (LineColorPicker) view.findViewById(R.id.picker);
        // set color palette
        colorPicker.setColors(new int[] {Color.parseColor("#E91E63"), Color.parseColor("#F44336"), Color.parseColor("#FF5722"),
                Color.parseColor("#FF9800"), Color.parseColor("#FFC107"), Color.parseColor("#8BC34A"), Color.parseColor("#4CAF50"),
                Color.parseColor("#009688"), Color.parseColor("#00BCD4"), Color.parseColor("#03A9F4"), Color.parseColor("#2196F3"),
                Color.parseColor("#3F51B5"), Color.parseColor("#673AB7"), Color.parseColor("#9C27B0")});
        colorPicker.setSelectedColor(arguments.getInt("color"));

        // set on change listener
        colorPicker.setOnColorChangedListener(new OnColorChangedListener() {
            @Override
            public void onColorChanged(int c) {
                //Log.d(this, "Selected color " + Integer.toHexString(c));
            }
        });

        // get selected color
        // have the button be a submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDetails activity = (UpdateDetails) getActivity();
                activity.onFinishUserDialog(courseCode.getText().toString(), courseName.getText().toString(), colorPicker.getColor(), arguments.getInt("position"));
                //updateElement(courseName.getText().toString(), courseCode.getText().toString(), colorPicker.getColor(), arguments.getInt("position"));
                getDialog().dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        // set this instance as callback for editor action
        courseCode.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Enter Course Details");

        return view;
    }


    //TODO port to the fragment manager

}
