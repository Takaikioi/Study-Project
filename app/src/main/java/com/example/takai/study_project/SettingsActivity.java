package com.example.takai.study_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends PreferenceActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
//        View content = root.getChildAt(0);
//        LinearLayout toolBarContainer = (LinearLayout) View.inflate(this, R.layout., null);
//        root.removeAllViews();
//        toolBarContainer.addView(content);
//        root.addView(toolBarContainer);
//
//        Toolbar mToolBar = (Toolbar) toolBarContainer.findViewById(R.id.tool_bar);
//        mToolBar.setTitle(getTitle());
//        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


//        setContentView(R.layout.activity_settings);

//        //Retrieve settings array from resource file and convert to list.
//        List<String> settingsTypes = Arrays.asList(getResources().getStringArray(R.array.settings_type_array));
//        GeneralArrayAdapter generalArrayAdapter = new GeneralArrayAdapter(this, settingsTypes);
//        ListView listView = (ListView) findViewById(R.id.settings_listview);
//        //Set the listView's adapter to be a GeneralArrayAdapter.
//        listView.setAdapter(generalArrayAdapter);
//    }
//
//    public void cellClicked(View view) {
//        OnClickManager manager = new OnClickManager(this);
//        Intent intent = manager.settingsResponse(view, this);
//        if (intent != null) {
//            startActivity(intent);
//        }
//    }
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return SettingsFragment.class.getName().equals(fragmentName);
    }
}
