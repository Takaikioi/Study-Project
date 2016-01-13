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


public class GeneralArrayAdapter extends android.widget.ArrayAdapter<String> {
    private final Activity context;
    private final List<String> names;
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    public GeneralArrayAdapter(Activity context, List<String> names) {
        super(context, R.layout.rowlayout, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        AdapterResourceManager adapterResourceManager = new AdapterResourceManager(context, names);
        if (parent.getId() == R.id.settings_listview) {
            rowView = adapterResourceManager.settingsResourceAllocation(position, parent);
        } else if (parent.getId() == R.id.settings_miscellaneous_listview) {
            rowView = adapterResourceManager.settingsMiscellaneousResourceAllocation(position, parent);
        } else {
            rowView = null;
        }
        return rowView;
    }
}