package com.example.takai.study_project;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Takai on 9/02/2016
 */
public class PreferencesFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
