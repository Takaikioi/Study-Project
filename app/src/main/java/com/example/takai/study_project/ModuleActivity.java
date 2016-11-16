package com.example.takai.study_project;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ModuleActivity extends AppCompatActivity implements ModuleDialogFragmentAdd.AddModuleElement {
    ArrayList<String> myNameList=
            new ArrayList<String>();
    SparseArray<Group> groups = new SparseArray<Group>();
    //array of strings for the names of the courses.
    List<ModuleModel> moduleModels =
            new ArrayList<ModuleModel>();
    ModuleExplandableListAdapter moduleListAdapter;
    private ModuleDataSource dataSource;
    public int courseID;
    ExpandableListView listView;
    Bundle extras;
    Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        extras = getIntent().getExtras();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                ModuleDialogFragmentAdd editNameDialog = new ModuleDialogFragmentAdd();
                editNameDialog.setArguments(extras);
                editNameDialog.show(manager, "fragment");
            }
        });
        dataSource = new ModuleDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       //` courseID = 1;

        // adding all the data items to a dataset so that the list can have values
        moduleModels = dataSource.getAllDataItems(extras.getInt("courseID"));
        for (int i = 0;i < moduleModels.size(); i ++){
            Group group = new Group(moduleModels.get(i).getName());
            groups.append(i, group);
            myNameList.add(moduleModels.get(i).getName());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        moduleListAdapter = new ModuleExplandableListAdapter(this, groups);

        listView.setAdapter(moduleListAdapter); // this is the error TODO: fix this error and it will work.
    }
    // add a module element to the database and update the screen
    @Override
    public void onFinishUserDialog(String courseName, int course) {
        // open DB
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // create a rudimentary date.
        Calendar cal =  Calendar.getInstance(); // calendar instance so can get a current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());
        // create a module element
        moduleModels.clear();
        dataSource.createData(courseName, course, date, date);
        moduleModels = dataSource.getAllDataItems(course);
//        myNameList.clear();
//        for (int i = 0;i < moduleModels.size(); i ++){
//            myNameList.add(moduleModels.get(i).getName());
//        }
        moduleListAdapter.addObject(dataSource.getNumberOfElements(course) , groups.get(moduleModels.size()) );
        dataSource.close();
        moduleListAdapter.notifyDataSetChanged();
    }
}
