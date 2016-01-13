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
        if (view.getId() == R.id.account_cell_id) {
            intent = new Intent(source, SettingsAccountActivity.class);
        } else if (view.getId() == R.id.notification_cell_id) {
            intent = new Intent(source, SettingsNotificationActivity.class);
        } else if (view.getId() == R.id.course_cell_id) {
            intent = new Intent(source, SettingsCourseActivity.class);
        } else if (view.getId() == R.id.srs_cell_id) {
            intent = new Intent(source, SettingsSRSActivity.class);
        } else if (view.getId() == R.id.miscellaneous_cell_id) {
            intent = new Intent(source, SettingsMiscellaneousActivity.class);
        } else {
            intent = null;
        }
        return intent;
    }
}
