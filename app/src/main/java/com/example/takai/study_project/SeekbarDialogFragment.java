package com.example.takai.study_project;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Takai on 12/02/2016
 */

interface SeekbarDialogFragmentDelegate {
    public void onSeekbarDialogClose(int Value);
}

public class SeekbarDialogFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

    protected SeekbarDialogFragmentDelegate delegate;
    private View view;
    private SeekBar seekbar;
    private int value;
    private int max;
    private int progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Button cancelButton;
        Button confirmButton;
        view = inflater.inflate(R.layout.preference_dialog_seekbar, container);
        seekbar = (SeekBar) view.findViewById(R.id.seekBarPreferences);
        setupSeekbar();
        TextView minView = (TextView) view.findViewById(R.id.minValue);
        TextView maxView = (TextView) view.findViewById(R.id.maxValue);
        TextView currentView = (TextView) view.findViewById(R.id.currentValue);
        minView.setText("0");
        maxView.setText(String.valueOf(max));
        currentView.setText(String.valueOf(progress));
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
            seekbar.setOnSeekBarChangeListener(this);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar != null) {
            int current = seekBar.getProgress();
            TextView currentView = (TextView) view.findViewById(R.id.currentValue);
            currentView.setText(String.valueOf(current));
        }
    }
}
