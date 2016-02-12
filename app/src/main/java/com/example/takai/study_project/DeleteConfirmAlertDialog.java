package com.example.takai.study_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 12/02/2016
 */
public class DeleteConfirmAlertDialog extends DialogFragment {

    private CourseDataSource dataSource; // database helper
    private Bundle arguments;
    List<CourseModel> courseModels = // list of data objects
            new ArrayList<CourseModel>();
    public interface DeleteCourse {
        void onFinishUserDialog(int position);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        arguments = getArguments();
        return new AlertDialog.Builder(getActivity())
                // set dialog icon
                .setIcon(android.R.drawable.stat_notify_error)
                        // set Dialog Title
                .setTitle("Delete")
                        // Set Dialog Message
                .setMessage("Are you sure you want to delete this course?")
                        // positive button
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteCourse activity = (DeleteCourse) getActivity();
                        activity.onFinishUserDialog(arguments.getInt("position"));
                        Toast.makeText(getActivity(), "Pressed DELETE", Toast.LENGTH_SHORT).show();
                    }
                })
                        // negative button
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}
