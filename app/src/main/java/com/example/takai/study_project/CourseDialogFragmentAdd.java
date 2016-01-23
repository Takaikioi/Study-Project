package com.example.takai.study_project;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import uz.shift.colorpicker.LineColorPicker;
import uz.shift.colorpicker.OnColorChangedListener;


/**
 * Created by hephalump on 12/01/2016.
 */
public class CourseDialogFragmentAdd extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText courseName;
    private EditText courseCode;
    private EditText courseActive;
    private Button submitButton;
    private LineColorPicker colorPicker;
   // private LobsterShadeSlider shadeSlider;
   // private String colourString;
    interface UserNameListener {
        void onFinishUserDialog(String name, String code, int color);
    }

    public CourseDialogFragmentAdd(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.course_add_dialog_layout, container);
        courseCode = (EditText) view.findViewById(R.id.courseCode);
        courseName = (EditText) view.findViewById(R.id.courseName);
        submitButton = (Button) view.findViewById(R.id.buttonSubmit);
        colorPicker = (LineColorPicker) view.findViewById(R.id.picker);
        // set color palette
        colorPicker.setColors(new int[] {Color.parseColor("#F44336"),Color.parseColor("#673AB7"),Color.parseColor("#2196F3"),
                Color.parseColor("#E91E63"),Color.parseColor("#FFEB3B")});
        colorPicker.setSelectedColor(Color.parseColor("#F44336"));

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
                UserNameListener activity = (UserNameListener) getActivity();
                activity.onFinishUserDialog(courseCode.getText().toString(), courseName.getText().toString(), colorPicker.getColor());
                getDialog().dismiss();
            }
        });
        // set this instance as callback for editor action
        courseCode.setOnEditorActionListener(this);
        courseName.setOnEditorActionListener(this);
        courseCode.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter your details");

        return view;
    }

    //TODO port to the fragment manager



    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        // return input text to activity
        UserNameListener activity = (UserNameListener) getActivity();
        activity.onFinishUserDialog(courseCode.getText().toString(), courseName.getText().toString(), colorPicker.getColor() );
        this.dismiss();
        return true;
    }
}
