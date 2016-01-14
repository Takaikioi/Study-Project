package com.example.takai.study_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by Takai on 13/01/2016
 */
public class ColourDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder
               //.setView(inflater.inflate(R.layout.dialog_color_picker, null))
                .setTitle(R.string.settings_miscellaneous_colour_dialog_negative_button)
                .setPositiveButton(R.string.settings_miscellaneous_colour_dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.settings_miscellaneous_colour_dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ColourDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
