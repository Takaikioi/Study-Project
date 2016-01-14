package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 30/12/2015.
 */
public class CourseDataSource {

    // Database fields
    private SQLiteDatabase database;
    private CourseDBhelper dbHelper;
    private String[] allColumns = { CourseDBhelper.COLUMN_ID,
            CourseDBhelper.COURSE_NAME_COLUMN, CourseDBhelper.COURSE_CODE_COLUMN, CourseDBhelper.COURSE_COLOUR_COLUMN, CourseDBhelper.COURSE_ACTIVE_COLUMN,
    CourseDBhelper.COURSE_START_DATE_COLUMN,CourseDBhelper.COURSE_END_DATE_COLUMN };

    public CourseDataSource(Context context) {
        dbHelper = new CourseDBhelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CourseModel createData (String name, String code, String courseColor, boolean active, String startDate, String endDate) {
        ContentValues values = new ContentValues();
        values.put(CourseDBhelper.COURSE_NAME_COLUMN, name);
        values.put(CourseDBhelper.COURSE_CODE_COLUMN, code);
        values.put(CourseDBhelper.COURSE_COLOUR_COLUMN, courseColor);
        values.put(CourseDBhelper.COURSE_ACTIVE_COLUMN, active);
        values.put(CourseDBhelper.COURSE_START_DATE_COLUMN, startDate);
        values.put(CourseDBhelper.COURSE_END_DATE_COLUMN, endDate);
        long insertId = database.insert(CourseDBhelper.COURSE_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(CourseDBhelper.COURSE_TABLE_NAME,
                allColumns, CourseDBhelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CourseModel newCourseModel = cursorToCourseModel(cursor);
        cursor.close();
        return newCourseModel;
    }

    public void deleteData(CourseModel courseModel) {
        long id = courseModel.getId();
        System.out.println("Data deleted with id: " + id);
        database.delete(CourseDBhelper.COURSE_TABLE_NAME, CourseDBhelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<CourseModel> getAllDataItems() {
        List<CourseModel> courseModels = new ArrayList<CourseModel>();

        Cursor cursor = database.query(CourseDBhelper.COURSE_TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CourseModel courseModel = cursorToCourseModel(cursor);
            courseModels.add(courseModel);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return courseModels;
    }
    //TODO revise data access methods.
    private CourseModel cursorToCourseModel(Cursor cursor) {
        int numCol = cursor.getColumnCount();
        int numRow = cursor.getCount();
        CourseModel courseModel = new CourseModel();
        courseModel.setId(cursor.getLong(0));
        courseModel.setName(cursor.getString(1));
        courseModel.setCode(cursor.getString(2));
        courseModel.setCourseColor(cursor.getString(3));
        if(cursor.getString(4) == "true"){
            courseModel.setActive(true);
        }else if(cursor.getString(4) == "false"){
            courseModel.setActive(false);
        }
        courseModel.setStartDate(cursor.getString(5));
        courseModel.setEndDate(cursor.getString(6));

        return courseModel;
    }
}
