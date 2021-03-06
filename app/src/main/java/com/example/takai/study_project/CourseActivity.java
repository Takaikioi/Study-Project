package com.example.takai.study_project;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class CourseActivity extends AppCompatActivity implements CourseDialogFragmentAdd.UserNameListener, CourseDialogueFragmentEdit.UpdateDetails
, DeleteConfirmAlertDialog.DeleteCourse{
    SparseArray<Group> groups = new SparseArray<Group>();
    //array of strings for the names of the courses.
    ArrayList<String> myNameList=
            new ArrayList<String>();
    ArrayList<String> myCodeList =
            new ArrayList<String>();
    ArrayList<Integer> myColorList =
            new ArrayList<Integer>();
    // array of models for the data objects
    List<CourseModel> courseModels =
            new ArrayList<CourseModel>();
    // adapter for displaying the course content in the list
    CourseExpandableListAdapter courseListAdapter;
    ExpandableListView expandableListView;
    // database interface.
    private CourseDataSource dataSource;

    public interface ClickedUpateButton {
        void enableUpdateDialogue(CourseModel courseModel);
    }



    public void enableUpdateDialogue(CourseModel courseModel){

//        FragmentManager manager = getFragmentManager();
//        CourseDialogFragmentAdd editNameDialog = new CourseDialogFragmentAdd();
//        editNameDialog.show(manager, "fragment");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                CourseDialogFragmentAdd editNameDialog = new CourseDialogFragmentAdd();
                editNameDialog.show(manager, "fragment");
            }
        });

        //Add listener to FabSpeedDial object.
//        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.courseFabSpeedDial);
//        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
//            @Override
//            public boolean onMenuItemSelected(MenuItem menuItem) {
//                //Determine source of selection and react.
//                if (menuItem.getItemId() == R.id.action_course_add) {
//                    FragmentManager manager = getFragmentManager();
//                    CourseDialogFragmentAdd editNameDialog = new CourseDialogFragmentAdd();
//                    editNameDialog.show(manager, "fragment");
//                }
//                return false;
//            }
//        });

        dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // adding all the data items to a dataset so that the list can have values
        courseModels = dataSource.getAllDataItems();
        for(int i = 0; i < courseModels.size(); i++){
            Group group = new Group(courseModels.get(i).getName(), courseModels.get(i).getCourseColor(), courseModels.get(i).getCode());
            group.children.add(courseModels.get(i).getCode());
            group.colorchildren.add(courseModels.get(i).getCourseColor());
            groups.append(i, group);
        }
        // creating the expandable list and adding it to the screen
        courseListAdapter = new CourseExpandableListAdapter(this, groups);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(courseListAdapter);


    dataSource.close();
    }

    protected void onResume() {
        super.onResume();
        //dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseModels = dataSource.getAllDataItems();
        groups.clear();
        for(int i = 0; i < courseModels.size(); i++){
            Group group = new Group(courseModels.get(i).getName(), courseModels.get(i).getCourseColor(), courseModels.get(i).getCode());
            group.children.add(courseModels.get(i).getCode());
            group.colorchildren.add(courseModels.get(i).getCourseColor());
            groups.append(i, group);
        }
        dataSource.close();
        courseListAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    // transfer data from the add dialogue to the database.
    @Override
    public void onFinishUserDialog(String name, String code, int color, int position){
        updateObject(name, code, color, position);
    }
    //
    @Override
    public void onFinishUserDialog(int position){
        deleteObject(position);
    }
    @Override
    public void onFinishUserDialog(String courseName, String courseCode, int colour) {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // create a rudimentary date.
        Calendar  cal =  Calendar.getInstance(); // calendar instance so can get a current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());

        dataSource.createData(courseName, courseCode, colour, true, date, date);
        Group group = new Group(courseName, colour, courseCode);
        group.children.add(courseCode);
        group.colorchildren.add(colour);
        courseListAdapter.addObject(groups.size(), group);
        courseListAdapter.notifyDataSetChanged();
        dataSource.close();
    }

    public boolean updateObject(String name, String code, int color,int position){
        dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseModels = dataSource.getAllDataItems();
        courseModels.get(position).setName(name);
        courseModels.get(position).setCode(code);
        courseModels.get(position).setCourseColor(color);
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
        courseListAdapter.notifyDataSetChanged();

        return true;
    }

    public boolean deleteObject(int position){
        dataSource = new CourseDataSource(this); // a database helper
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
        courseListAdapter.notifyDataSetChanged(); // inform the view the dataset has changed
        return true;
    }

}

