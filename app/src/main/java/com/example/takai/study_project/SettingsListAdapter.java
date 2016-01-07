package com.example.takai.study_project;

/**
 * Created by Takai on 7/01/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
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
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        textView.setText(settingNames.get(position));
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        int resource;
        String settingName = settingNames.get(position);
        switch (settingName) {
            case "Account":         resource = R.drawable.ic_account_circle_black_24dp;
                                    break;
            case "Notification":    resource = R.drawable.ic_notifications_black_24dp;
                                    break;
            case "Course":          resource = R.drawable.ic_school_black_24dp;
                                    break;
            case "SRS":             resource = R.drawable.ic_watch_later_black_24dp;
                                    break;
            case "Miscellaneous":   resource = R.drawable.ic_settings_black_24dp;
                                    break;
            default:                resource = R.drawable.ic_error_black_24dp;
                                    break;
        }
        imageView.setImageResource(resource);
        return rowView;
    }
}