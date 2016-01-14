package com.example.takai.study_project;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Takai on 14/01/2016
 */
public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load preferences from preferences.xml
        addPreferencesFromResource(R.xml.preferences);
    }

}
