package com.example.takai.study_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hephalump on 12/02/2016.
 */
public class ModuleListAdapter extends ArrayAdapter<String> {
    private  Activity context;
    private  List<String> names;



    public ModuleListAdapter(Activity context, List<String> names) {
        super(context, R.layout.course_row_layout, names);
        this.names = names;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.content_module_list_row, parent, false);
        //rowView.setBackgroundColor(colors.get(position));
        TextView textViewName = (TextView) rowView.findViewById(R.id.moduleName);
        ImageView colorDisplay = (ImageView) rowView.findViewById(R.id.criticalColor);
        //colorDisplay.setBackgroundColor(colors.get(position));
        // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textViewName.setText(names.get(position));


        return rowView;
    }
}
