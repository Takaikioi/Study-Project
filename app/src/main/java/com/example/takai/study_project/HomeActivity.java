package com.example.takai.study_project;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private static final int YES_NO_CALL = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new YesNoDialog();
                Bundle args = new Bundle();
                args.putString("title", "harry potter");
                args.putString("message", "get out");
                dialog.setArguments(args);
                //dialog.setTargetFragment(this, YES_NO_CALL);
                dialog.show(getFragmentManager(), "tag");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToCoursePageFromHome(View view){
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
    }

    public void goToAssessmentPageFromHome(View view){
        Intent intent = new Intent(this, AssessmentActivity.class);
        startActivity(intent);
    }

    public void goToReviewPageFromHome(View view){
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void goToCalendarPageFromHome(View view){
        Intent intent = new Intent(this, TimetableActivity.class);
        startActivity(intent);
    }

    public void goToSettingsPageFromHome(MenuItem menuItem) {
        Intent intent = new Intent(this, ApplicationSettings.class);
        startActivity(intent);
    }
}
