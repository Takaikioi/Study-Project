package com.example.takai.study_project;

/**
 * Created by Takai on 7/01/2016
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class GeneralArrayAdapter extends android.widget.ArrayAdapter<String> {
    private final Activity context;
    private final List<String> names;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    public GeneralArrayAdapter(Activity context, List<String> names) {
        super(context, R.layout.course_row_layout, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        AdapterResourceManager adapterResourceManager = new AdapterResourceManager(context, names);
        if (parent.getId() == R.id.settings_miscellaneous_listview) {
            rowView = adapterResourceManager.settingsMiscellaneousResourceAllocation(position, parent);
        } else {
            rowView = null;
        }
        return rowView;
    }
}