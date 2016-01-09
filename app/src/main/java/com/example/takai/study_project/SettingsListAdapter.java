package com.example.takai.study_project;

/**
 * Created by Takai on 7/01/2016
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SettingsListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> settingNames;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    public SettingsListAdapter(Activity context, List<String> names) {
        super(context, R.layout.rowlayout, names);
        this.context = context;
        this.settingNames = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.settings_row_layout, parent, false);
        int id = Utilities.generateViewId();
        rowView.setId(id);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(settingNames.get(position));
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        int resource;
        String settingName = settingNames.get(position);
        preferences = context.getSharedPreferences(context.getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        prefEditor = preferences.edit();
        //Generate an icon resource for each cell.
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