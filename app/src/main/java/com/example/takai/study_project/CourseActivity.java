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

    //array of strings for the names of the courses.
    ArrayList<String> myArrayList=
            new ArrayList<String>();
    // array of models for the data objects
    List<CourseModel> courseModels = new ArrayList<CourseModel>();
    // adapter for displaying the course content in the list
    CourseListAdapter courseListAdapter;
    ListView listView;
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
        // add some dates to db(just for manual testing)
//        Calendar  cal =  Calendar.getInstance(); // calendar instance so can get a current date
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = dateFormat.format(cal.getTime());


       // dataSource.createData("sad", "2304ICT", "RED", true, date, date );

        courseModels = dataSource.getAllDataItems();
        for(int i = 0; i < courseModels.size(); i++){
            myArrayList.add(courseModels.get(i).getName());
        }
         courseListAdapter = new CourseListAdapter(this, myArrayList);
        listView = (ListView) findViewById(R.id.listView);
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
    public void onFinishUserDialog(String courseName, String courseCode) {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Calendar  cal =  Calendar.getInstance(); // calendar instance so can get a current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());

        dataSource.createData(courseName, courseCode, "RED", true, date, date);
        courseListAdapter.clear();
        courseListAdapter.notifyDataSetChanged();
        courseModels = dataSource.getAllDataItems();
        for(int i = 0; i < courseModels.size(); i++){
           // myArrayList.add(courseModels.get(i).getName());
            courseListAdapter.add(courseModels.get(i).getName());
        }

//        courseListAdapter.clear();
//        courseListAdapter.notifyDataSetChanged();
//        courseListAdapter.addAll(myArrayList);
        // courseListAdapter.notifyDataSetChanged();
        //courseListAdapter.notifyDataSetChanged();
        dataSource.close();
    }
}

