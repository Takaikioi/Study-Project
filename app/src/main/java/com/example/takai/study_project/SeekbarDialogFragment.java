package com.example.takai.study_project;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Created by Takai on 12/02/2016
 */

interface SeekbarDialogFragmentDelegate {
    public void onSeekbarDialogClose(int Value);
}

public class SeekbarDialogFragment extends DialogFragment {

    protected SeekbarDialogFragmentDelegate delegate;
    private SeekBar seekbar;
    private int value;
    private int max;
    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button cancelButton;
        Button confirmButton;
        View view = inflater.inflate(R.layout.preference_dialog_seekbar, container);
        seekbar = (SeekBar) view.findViewById(R.id.seekBarPreferences);
        setupSeekbar();
        getDialog().setTitle("Please Select...");
        cancelButton = (Button) view.findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        confirmButton = (Button) view.findViewById(R.id.buttonConfirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = seekbar.getProgress();
                if (delegate != null) {
                    delegate.onSeekbarDialogClose(value);
                    getDialog().dismiss();
                }
            }
        });
        return view;
    }

    public void setValues(int max, int progress) {
        this.max = max;
        this.progress = progress;
        setupSeekbar();
    }

    public void setupSeekbar() {
        if (seekbar != null) {
            seekbar.setMax(max);
            seekbar.setProgress(progress);
        }
    }
}
