package com.example.takai.study_project;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
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
 * Created by hephalump on 13/02/2016.
 */
public class ModuleDialogFragmentAdd  extends DialogFragment {
    private EditText courseName;
    private Button submitButton;
    private Button cancelButton;
    private Bundle data;

    interface AddModuleElement {
        void onFinishUserDialog(String name, int courseID);
    }

    public ModuleDialogFragmentAdd(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.course_add_dialog_layout, container);
        data = getArguments();
        courseName = (EditText) view.findViewById(R.id.courseName);
        submitButton = (Button) view.findViewById(R.id.buttonSubmit);
        cancelButton = (Button) view.findViewById(R.id.buttonCancel);


        // have the button be a submit button

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddModuleElement activity = (AddModuleElement) getActivity();
                activity.onFinishUserDialog(courseName.getText().toString(), data.getInt("courseID") );
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
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Enter Course Details");

        return view;
    }

    //TODO port to the fragment manager

}
