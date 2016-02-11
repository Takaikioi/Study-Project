package com.example.takai.study_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hephalump on 10/02/2016
 */
public class CourseExpandableListAdapter extends  BaseExpandableListAdapter implements CourseActivity.ClickedUpateButton   {
    public Activity context;// the context of the application if came from
    public SparseArray<Group> groups;// the dataset
    public LayoutInflater inflater; // not sure, inflates something
    private CourseDataSource dataSource; // database helper
    List<CourseModel> courseModels = // list of data objects
            new ArrayList<CourseModel>();
    CourseActivity.ClickedUpateButton callback;

    public CourseExpandableListAdapter(Activity context, SparseArray<Group> groups) {
        inflater = context.getLayoutInflater();
        this.groups = groups;
        this.context = context;
    }


    public void enableUpdateDialogue(CourseModel courseModel){
        CourseDialogFragmentAdd editNameDialog = new CourseDialogFragmentAdd();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {


        return groups.get(groupPosition).children.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override // handles the expanded lists
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

    @Override // handles the main list objects
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.course_listitem_row, null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView); // an image
        Group group = (Group) getGroup(groupPosition); // a data object
        TextView textView = (TextView) convertView.findViewById(R.id.courseNameText); // a textview to hold text
        TextView textView1 = (TextView) convertView.findViewById(R.id.courseCodeText);
        textView.setText(group.string); // adding the stupid course name to the textfield
        textView1.setText(group.code);
        imageView.setBackgroundColor(group.colour); // set the color of the background
        final FrameLayout menuButton = (FrameLayout) convertView.findViewById(R.id.overflowButtonBounds);
        // add a menu button
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
                        else if(item.getItemId() == R.id.action_course_update){
                            dataSource = new CourseDataSource(context);
                            try {
                                dataSource.open();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            courseModels = dataSource.getAllDataItems();
                            dataSource.close();
                            callback.enableUpdateDialogue(courseModels.get(groupPosition));

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

    // method for deleting the objects and making the screen update afterwards
    public boolean deleteObject(int position){
        dataSource = new CourseDataSource(context); // a database helper
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseModels = dataSource.getAllDataItems(); // add all the data items to a list
        dataSource.deleteData(courseModels.get(position)); // select the object from the list to delete
        courseModels.remove(position); // take the object out as the list hasn't been updated
        groups.clear(); // renew the dataset
        for(int i = 0; i < courseModels.size(); i++){
            Group group = new Group(courseModels.get(i).getName(), courseModels.get(i).getCourseColor(), courseModels.get(i).getCode());
            group.children.add(courseModels.get(i).getCode());
            group.colorchildren.add(courseModels.get(i).getCourseColor());
            groups.append(i, group);
        }
        dataSource.close();
        notifyDataSetChanged(); // inform the view the dataset has changed
        return true;
    }
    // a method for updating the objects in the view and the database.
    public boolean updateObject(int position){
        dataSource = new CourseDataSource(context);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseModels = dataSource.getAllDataItems();
        dataSource.updateElement(courseModels.get(position));
        courseModels = dataSource.getAllDataItems();
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