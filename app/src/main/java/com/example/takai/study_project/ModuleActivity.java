package com.example.takai.study_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleActivity extends AppCompatActivity {
    ArrayList<String> myNameList=
            new ArrayList<String>();
    List<ModuleModel> moduleModels =
            new ArrayList<ModuleModel>();
    ModuleListAdapter moduleListAdapter;
    private ModuleDataSource dataSource;
    public int courseID;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
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
        dataSource = new ModuleDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        courseID =
        // adding all the data items to a dataset so that the list can have values
        moduleModels = dataSource.getAllDataItems();
        for (int i = 0;i < moduleModels.size(); i ++){
            myNameList.add(moduleModels.get(i).getName());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = (ListView) findViewById(R.id.moduleListView);
        moduleListAdapter = new ModuleListAdapter(this, myNameList);

        listView.setAdapter(moduleListAdapter);
    }

}
