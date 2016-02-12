package com.example.takai.study_project;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.SeekBar;

/**
 * Created by Takai on 12/02/2016
 */
public class SeekBarPreference extends Preference implements SeekbarDialogFragmentDelegate {

    private int max;
    private int progress;

    public SeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        max = attrs.getAttributeResourceValue(R.attr.defaultProgress, 10);
        progress = attrs.getAttributeResourceValue(R.attr.max, 6);
    }

    @Override
    protected void onClick() {
        Activity context = (Activity) getContext();
        FragmentManager manager = context.getFragmentManager();
        SeekbarDialogFragment seekbarDialog = new SeekbarDialogFragment();
        seekbarDialog.show(manager, "fragment");
        seekbarDialog.delegate = this;
        seekbarDialog.setValues(max, progress);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if (restorePersistedValue) {
            progress = this.getPersistedInt(progress);
        }
    }

    @Override
    public void onSeekbarDialogClose(int value) {
        persistInt(value);
        progress = value;
    }
}
