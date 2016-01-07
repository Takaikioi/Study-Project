package com.example.takai.study_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
        //Force compiler to stop whinging about the potential for a null pointer.
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = this.getSharedPreferences(getString(R.string.preferences_file_key), Context.MODE_PRIVATE);
        prefEditor = preferences.edit();

        //Retrieve settings array from resource file and convert to list.
        List<String> settingsTypes = Arrays.asList(getResources().getStringArray(R.array.settings_type_array));
        SettingsListAdapter settingsListAdapter = new SettingsListAdapter(this, settingsTypes);
        ListView listView = (ListView) findViewById(R.id.listView);
        //Set the listView's adapter to be a SettingsListAdapter.
        listView.setAdapter(settingsListAdapter);
    }

    public void savePreferences(View view) {

    }
}
