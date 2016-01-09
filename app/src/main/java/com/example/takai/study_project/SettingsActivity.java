package com.example.takai.study_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Force compiler to stop whinging about the potential for a null pointer.
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Retrieve settings array from resource file and convert to list.
        List<String> settingsTypes = Arrays.asList(getResources().getStringArray(R.array.settings_type_array));
        GeneralArrayAdapter generalArrayAdapter = new GeneralArrayAdapter(this, settingsTypes);
        ListView listView = (ListView) findViewById(R.id.settings_listview);
        //Set the listView's adapter to be a GeneralArrayAdapter.
        listView.setAdapter(generalArrayAdapter);
    }

    public void cellClicked(View view) {
        OnClickManager manager = new OnClickManager(this);
        Intent intent = manager.settingsResponse(view, this);
        if (intent != null) {
            startActivity(intent);
        }
    }
}
