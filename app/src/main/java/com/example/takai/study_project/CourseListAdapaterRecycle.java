package com.example.takai.study_project;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 26/01/2016.
 */
public class CourseListAdapaterRecycle extends RecyclerView.Adapter<CourseListAdapaterRecycle.ViewHolder> {
    private ArrayList<String> mDataset;

    //private  Activity context;
    private  List<String> names;
    private  List<String> codes;
    private  List<Integer> colors;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.courseCode);
            txtFooter = (TextView) v.findViewById(R.id.courseName);
        }
    }
    public void add(int position, String name, String code) {
        names.add(position, name);
        codes.add(position, code);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = names.indexOf(item);
        names.remove(position);
        codes.remove(position);
        notifyItemRemoved(position);
        //// TODO: 9/02/2016 update in database as well
    }
    public CourseListAdapaterRecycle( List<String> names, List<String> codes, List<Integer> colors) {
        //super(context, R.layout.course_row_layout, names);
        //this.context = context;
        this.names = names;
        this.codes = codes;
        this.colors = colors;
    }

    // Provide a suitable constructor (depends on the kind of dataset)

    @Override
    public CourseListAdapaterRecycle.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = names.get(position);
        final String code = codes.get(position);
        holder.txtHeader.setText(names.get(position));
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(name);
            }
        });

       // holder.txtFooter.setText("Footer: " + codes.get(position));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
