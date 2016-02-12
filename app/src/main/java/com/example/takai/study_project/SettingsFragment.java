package com.example.takai.study_project;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.example.android.supportv7.app.AppCompatPreferenceActivity;
import com.rarepebble.colorpicker.ColorPreference;

/**
 * Created by Takai on 14/01/2016
 */
public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
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
            final Drawable backArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            assert backArrow != null;
            backArrow.setColorFilter(getResources().getColor(R.color.materialcolorpicker__white), PorterDuff.Mode.SRC_ATOP);
            bar.setHomeAsUpIndicator(backArrow);
            bar.setTitle(getPreferenceScreen().getTitle());
            PreferenceScreen screen = this.getPreferenceScreen();
            if (screen.getTitle().equals("Miscellaneous")) {

            }
        }
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            View frame = (View) getView().getParent();
            if (frame != null)
                frame.setPadding(0, 0, 0, 0);
        }
        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); ++i) {
            Preference preference = getPreferenceScreen().getPreference(i);
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup = (PreferenceGroup) preference;
                for (int j = 0; j < preferenceGroup.getPreferenceCount(); ++j) {
                    Preference singlePref = preferenceGroup.getPreference(j);
                    updatePreference(singlePref, singlePref.getKey());
                }
            } else {
                updatePreference(preference, preference.getKey());
            }
        }
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(findPreference(key), key);
    }

    private void updatePreference(Preference preference, String key) {
        if (preference == null) {

        } else if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            listPreference.setSummary(listPreference.getEntry());
        } else if (preference instanceof EditTextPreference) {
            SharedPreferences sharedPrefs = getPreferenceManager().getSharedPreferences();
            String newSummary = sharedPrefs.getString(key, "Default");
            if (!"Default".equals(newSummary)) {
                preference.setSummary(newSummary);
            }
        } else {

        }
    }
}
