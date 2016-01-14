package com.example.takai.study_project;

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

/**
 * Created by hephalump on 5/01/2016.
 */

public class CourseListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> names;
//TODO port to the general adapter
//    static class ViewHolder {
//        public TextView text;
//        public ImageView image;
//    }

    public CourseListAdapter(Activity context, List<String> names) {
        super(context, R.layout.rowlayout, names);
        this.context = context;
        this.names = names;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(names.get(position));
        return rowView;
    }
}