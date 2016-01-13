package com.example.takai.study_project;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hephalump on 12/01/2016.
 */
public class CourseDialogFragmentAdd extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText courseName;
    private EditText courseCode;
    private EditText courseActive;

    interface UserNameListener {
        void onFinishUserDialog(String name, String code);
    }

    public CourseDialogFragmentAdd(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.course_add_dialog_layout, container);
        courseCode = (EditText) view.findViewById(R.id.courseCode);
        courseName = (EditText) view.findViewById(R.id.courseName);


        // set this instance as callback for editor action
        courseCode.setOnEditorActionListener(this);
        courseName.setOnEditorActionListener(this);
        courseCode.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter your details");

        return view;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        // return input text to activity
        UserNameListener activity = (UserNameListener) getActivity();
        activity.onFinishUserDialog(courseCode.getText().toString(), courseName.getText().toString());
        this.dismiss();
        return true;
    }
}
