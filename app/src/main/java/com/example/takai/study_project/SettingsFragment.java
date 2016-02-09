package com.example.takai.study_project;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.supportv7.app.AppCompatPreferenceActivity;

/**
 * Created by Takai on 14/01/2016
 */
public class SettingsFragment extends PreferenceFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Load preferences from XML preference files.
        String settings = getArguments().getString("settings");
        if (settings != null) {
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.activity_settings, container, false);
        if (layout != null) {
            AppCompatPreferenceActivity activity = (AppCompatPreferenceActivity) getActivity();
            Toolbar toolbar = (Toolbar) layout.findViewById(R.id.tool_bar);
            activity.setSupportActionBar(toolbar);

            ActionBar bar = activity.getSupportActionBar();
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            bar.setTitle(getPreferenceScreen().getTitle());
        }
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getView() != null) {
            View frame = (View) getView().getParent();
            if (frame != null)
                frame.setPadding(0, 0, 0, 0);
        }
    }
}
