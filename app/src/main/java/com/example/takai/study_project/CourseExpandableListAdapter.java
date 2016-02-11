package com.example.takai.study_project;

import android.app.Activity;
import android.media.Image;
import android.support.v7.view.menu.MenuBuilder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 10/02/2016
 */
public class CourseExpandableListAdapter extends BaseExpandableListAdapter {
    public Activity context;
    public SparseArray<Group> groups;
    public LayoutInflater inflater;
    public PopupMenu popupMenu;
    private CourseDataSource dataSource;
    List<CourseModel> courseModels =
            new ArrayList<CourseModel>();

    public CourseExpandableListAdapter(Activity context, SparseArray<Group> groups) {
        inflater = context.getLayoutInflater();
        this.groups = groups;
        this.context = context;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String children = (String) getChild(groupPosition, childPosition);
        TextView text = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.course_detail_view, null);
        }
        text = (TextView) convertView.findViewById(R.id.courseDetail);
        text.setText(children);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, children,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    public void addObject(int size,Group group){
    groups.append(size, group);
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.course_listitem_row, null);

        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Group group = (Group) getGroup(groupPosition);
        TextView textView = (TextView) convertView.findViewById(R.id.courseNameText);
        TextView textView1 = (TextView) convertView.findViewById(R.id.courseCodeText);
        textView.setText(group.string);
        textView1.setText(group.code);
        imageView.setBackgroundColor(group.colour);
        final ImageView menuButton = (ImageView) convertView.findViewById(R.id.overflowButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(v.getContext(), menuButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.course_overflow, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId() == R.id.action_course_remove){
                            deleteObject(groupPosition);
                        }
                        Toast.makeText(
                                v.getContext(),
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method
//

//        ((CheckedTextView) convertView).setText(group.string);
//        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public boolean deleteObject(int position){
        dataSource = new CourseDataSource(context);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseModels = dataSource.getAllDataItems();
        dataSource.deleteData(courseModels.get(position));
        courseModels.remove(position);
        groups.clear();
        for(int i = 0; i < courseModels.size(); i++){
            Group group = new Group(courseModels.get(i).getName(), courseModels.get(i).getCourseColor(), courseModels.get(i).getCode());
            group.children.add(courseModels.get(i).getCode());
            group.colorchildren.add(courseModels.get(i).getCourseColor());
            groups.append(i, group);
        }
        dataSource.close();
        notifyDataSetChanged();
        return true;
    }
}