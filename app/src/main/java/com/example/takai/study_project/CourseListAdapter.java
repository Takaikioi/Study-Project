package com.example.takai.study_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hephalump on 5/01/2016.
 */

public class CourseListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> names;
    private final List<String> codes;
    private final List<String> colors;
//TODO port to the general adapter
//    static class ViewHolder {
//        public TextView text;
//        public ImageView image;
//    }

    public CourseListAdapter(Activity context, List<String> names, List<String> codes, List<String> colors) {
        super(context, R.layout.course_row_layout, names);
        this.context = context;
        this.names = names;
        this.codes = codes;
        this.colors = colors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.course_row_layout, parent, false);
        rowView.setBackgroundColor(Color.parseColor(colors.get(position)));
        TextView textViewName = (TextView) rowView.findViewById(R.id.courseNameText);
        TextView textViewCode = (TextView) rowView.findViewById(R.id.courseCodeText);
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textViewName.setText(names.get(position));
        textViewCode.setText(codes.get(position));


        return rowView;
    }
}