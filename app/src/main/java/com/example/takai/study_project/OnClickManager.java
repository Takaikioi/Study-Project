package com.example.takai.study_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Takai on 9/01/2016
 */
public class OnClickManager {

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    public OnClickManager(Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        prefEditor = preferences.edit();
    }

    public Intent settingsResponse(View view, AppCompatActivity source) {
        Intent intent;
        if (view.getId() == preferences.getInt("account_cell_id", 0)) {
            intent = new Intent(source, SettingsAccountActivity.class);
        } else if (view.getId() == preferences.getInt("notification_cell_id", 0)) {
            intent = new Intent(source, SettingsNotificationActivity.class);
        } else if (view.getId() == preferences.getInt("course_cell_id", 0)) {
            intent = new Intent(source, SettingsCourseActivity.class);
        } else if (view.getId() == preferences.getInt("srs_cell_id", 0)) {
            intent = new Intent(source, SettingsSRSActivity.class);
        } else if (view.getId() == preferences.getInt("miscellaneous_cell_id", 0)) {
            intent = new Intent(source, SettingsMiscellaneousActivity.class);
        } else {
            intent = null;
        }
        return intent;
    }
}
