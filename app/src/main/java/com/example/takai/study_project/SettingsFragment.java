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
        //Load preferences from XML preference files.
        String settings = getArguments().getString("settings");
        if ("account".equals(settings)) {
            addPreferencesFromResource(R.xml.account_preferences);
        } else if ("notifications".equals(settings)) {
            addPreferencesFromResource(R.xml.notifications_preferences);
        } else if ("course".equals(settings)) {
            addPreferencesFromResource(R.xml.course_preferences);
        } else if ("srs".equals(settings)) {
            addPreferencesFromResource(R.xml.srs_preferences);
        } else if ("miscellaneous".equals(settings)) {
            addPreferencesFromResource(R.xml.miscellaneous_preferences);
        }

    }

}
