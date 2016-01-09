package com.example.takai.study_project;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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
        int id = Utilities.generateViewId();
        rowView.setId(id);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(names.get(position));
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        switch (settingName) {
            case "Account":         resource = R.drawable.ic_account_circle_black_24dp;
                                    prefEditor.putInt("account_cell_id", id);
                                    break;
            case "Notification":    resource = R.drawable.ic_notifications_black_24dp;
                                    prefEditor.putInt("notification_cell_id", id);
                                    break;
            case "Course":          resource = R.drawable.ic_school_black_24dp;
                                    prefEditor.putInt("course_cell_id", id);
                                    break;
            case "SRS":             resource = R.drawable.ic_watch_later_black_24dp;
                                    prefEditor.putInt("srs_cell_id", id);
                                    break;
            case "Miscellaneous":   resource = R.drawable.ic_settings_black_24dp;
                                    prefEditor.putInt("miscellaneous_cell_id", id);
                                    break;
            default:                resource = R.drawable.ic_error_black_24dp;
                                    break;
        }
        imageView.setImageResource(resource);
        prefEditor.commit();
        return rowView;
    }
}
