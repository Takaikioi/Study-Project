package com.example.takai.study_project;

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

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    ArrayList<String> myArrayList=
            new ArrayList<String>();
    List<CourseModel> courseModels = new ArrayList<CourseModel>();
    long startDate;
    long endDate;
    private CourseDataSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataSource = new CourseDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        startDate = 33333333;
        endDate = 33333344;
        dataSource.createData("sad", "2304ICT", "RED", true, startDate, endDate );
        courseModels = dataSource.getAllDataItems();
        for(int i = 0; i < courseModels.size(); i++){
            myArrayList.add(courseModels.get(i).getName());
        }
        CourseListAdapter courseListAdapter = new CourseListAdapter(this, myArrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(courseListAdapter);


    }


    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}

