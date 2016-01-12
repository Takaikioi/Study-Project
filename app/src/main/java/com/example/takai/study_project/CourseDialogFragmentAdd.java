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
    private EditText mEditText;

    interface UserNameListener {
        void onFinishUserDialog(String user);
    }

    public CourseDialogFragmentAdd(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.course_add_dialog_layout, container);
        mEditText = (EditText) view.findViewById(R.id.courseCode);

        // set this instance as callback for editor action
        mEditText.setOnEditorActionListener(this);
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter your details");

        return view;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        // return input text to activity
        UserNameListener activity = (UserNameListener) getActivity();
        activity.onFinishUserDialog(mEditText.getText().toString());
        this.dismiss();
        return true;
    }
}
