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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class CourseActivity extends AppCompatActivity implements CourseDialogFragmentAdd.UserNameListener {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // a button for to do the adding of course content
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                CourseDialogFragmentAdd editNameDialog = new CourseDialogFragmentAdd();
                editNameDialog.show(manager, "fragment");
            }
        });

        dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        courseModels = dataSource.getAllDataItems();
        for(int i = 0; i < courseModels.size(); i++){
            Group group = new Group(courseModels.get(i).getName());
            group.children.add(courseModels.get(i).getCode());
            //group.children.add(courseModels.get(i).getCourseColor());
            groups.append(i, group);
        }

        courseListAdapter = new CourseExpandableListAdapter(this, groups);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);
        listView.setAdapter(courseListAdapter);


    dataSource.close();
    }

    //TODO add a proper layout and better display of resources, update fab to add extra options

    protected void onResume() {
        super.onResume();
        //dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    @Override
    public void onFinishUserDialog(String courseName, String courseCode, int colour) {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Calendar  cal =  Calendar.getInstance(); // calendar instance so can get a current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());

        dataSource.createData(courseName, courseCode, colour, true, date, date);
        Group group = new Group(courseName);
        group.children.add(courseCode);
        groups.append(dataSource.getNumberOfElements(),group);

        courseListAdapter.notifyDataSetChanged();

        dataSource.close();
    }
}

