package com.example.takai.study_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class SettingsMiscellaneousActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_miscellaneous);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> miscellaneousSettings = Arrays.asList(getResources().getStringArray(R.array.settings_miscellaneous_array));
        GeneralArrayAdapter arrayAdapter = new GeneralArrayAdapter(this, miscellaneousSettings);
        ListView listView = (ListView) findViewById(R.id.settings_miscellaneous_listview);
        listView.setAdapter(arrayAdapter);
    }

    public void cellClicked(View view) {

    }
}
