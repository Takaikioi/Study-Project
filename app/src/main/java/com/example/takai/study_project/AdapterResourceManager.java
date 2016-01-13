package com.example.takai.study_project;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Takai on 9/01/2016
 */
public class AdapterResourceManager {
    private final Activity context;
    private final List<String> names;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;
    private LayoutInflater inflater;

    public AdapterResourceManager(Activity context, List<String> names) {
        preferences = context.getSharedPreferences(context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        prefEditor = preferences.edit();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.names = names;
    }

    public View settingsResourceAllocation(int position, ViewGroup parent) {
        int resource;
        String settingName = names.get(position);
        View rowView = inflater.inflate(R.layout.settings_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(names.get(position));
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        switch (settingName) {
            case "Account":         resource = R.drawable.ic_account_circle_black_24dp;
                                    rowView.setId(R.id.account_cell_id);
                                    break;
            case "Notification":    resource = R.drawable.ic_notifications_black_24dp;
                                    rowView.setId(R.id.notification_cell_id);
                                    break;
            case "Course":          resource = R.drawable.ic_school_black_24dp;
                                    rowView.setId(R.id.course_cell_id);
                                    break;
            case "SRS":             resource = R.drawable.ic_watch_later_black_24dp;
                                    rowView.setId(R.id.srs_cell_id);
                                    break;
            case "Miscellaneous":   resource = R.drawable.ic_settings_black_24dp;
                                    rowView.setId(R.id.miscellaneous_cell_id);
                                    break;
            default:                resource = R.drawable.ic_error_black_24dp;
                                    break;
        }
        imageView.setImageResource(resource);
        return rowView;
    }

    public View settingsMiscellaneousResourceAllocation(int position, ViewGroup parent) {
        int resource;
        String settingName = names.get(position);
        View rowView = inflater.inflate(R.layout.settings_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(settingName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        switch (settingName) {
            case "App Theme":           resource = R.drawable.ic_account_circle_black_24dp;
                                        rowView.setId(R.id.miscellaneous_app_theme);
                                        break;
            case "Silent Session":      resource = R.drawable.ic_notifications_black_24dp;
                                        rowView.setId(R.id.miscellaneous_silent_session);
                                        break;
            case "Locked Session":      resource = R.drawable.ic_school_black_24dp;
                                        rowView.setId(R.id.miscellaneous_locked_session);
                                        break;
            case "Screen Always On":    resource = R.drawable.ic_watch_later_black_24dp;
                                        rowView.setId(R.id.miscellaneous_screen_always_on);
                                        break;
            default:                    resource = R.drawable.ic_error_black_24dp;
                                        break;
        }
        imageView.setImageResource(resource);
        return rowView;
    }
}
