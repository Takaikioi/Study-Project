package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 30/12/2015
 */
public class CourseDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBhelper dbHelper;
    private String[] allColumns = { DBhelper.COLUMN_ID,
            DBhelper.COURSE_NAME_COLUMN, DBhelper.COURSE_CODE_COLUMN, DBhelper.COURSE_COLOUR_COLUMN, DBhelper.COURSE_ACTIVE_COLUMN,
    DBhelper.COURSE_START_DATE_COLUMN, DBhelper.COURSE_END_DATE_COLUMN };

    public CourseDataSource(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CourseModel createData (String name, String code, int courseColor, boolean active, String startDate, String endDate) {
        ContentValues values = new ContentValues();
        values.put(DBhelper.COURSE_NAME_COLUMN, name);
        values.put(DBhelper.COURSE_CODE_COLUMN, code);
        values.put(DBhelper.COURSE_COLOUR_COLUMN, courseColor);
        values.put(DBhelper.COURSE_ACTIVE_COLUMN, active);
        values.put(DBhelper.COURSE_START_DATE_COLUMN, startDate);
        values.put(DBhelper.COURSE_END_DATE_COLUMN, endDate);

        long insertId = database.insert(DBhelper.COURSE_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DBhelper.COURSE_TABLE_NAME,
                allColumns, DBhelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CourseModel newCourseModel = cursorToCourseModel(cursor);
        cursor.close();
        return newCourseModel;
    }

    public void deleteData(CourseModel courseModel) {
        long id = courseModel.getId();
        System.out.println("Data deleted with id: " + id);
        database.delete(DBhelper.COURSE_TABLE_NAME, DBhelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<CourseModel> getAllDataItems() {
        List<CourseModel> courseModels = new ArrayList<CourseModel>();

        Cursor cursor = database.query(DBhelper.COURSE_TABLE_NAME,
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
        courseModel.setCourseColor(cursor.getInt(3));
        if(cursor.getString(4) == "true"){
            courseModel.setActive(true);
        }else if(cursor.getString(4) == "false"){
            courseModel.setActive(false);
        }
        courseModel.setStartDate(cursor.getString(5));
        courseModel.setEndDate(cursor.getString(6));

        return courseModel;
    }

    public int getNumberOfElements(){
        Cursor cursor = database.query(DBhelper.COURSE_TABLE_NAME,
                allColumns, null, null, null, null, null);
        int numRows = cursor.getColumnCount();
        return numRows;
    }
    public boolean updateElement(CourseModel courseModel){
        ContentValues values = new ContentValues();
        values.put(DBhelper.COURSE_NAME_COLUMN, courseModel.getName());
        values.put(DBhelper.COURSE_CODE_COLUMN, courseModel.getCode());
        values.put(DBhelper.COURSE_COLOUR_COLUMN, courseModel.getCourseColor());
        values.put(DBhelper.COURSE_ACTIVE_COLUMN, courseModel.isActive());
        values.put(DBhelper.COURSE_START_DATE_COLUMN, courseModel.getStartDate());
        values.put(DBhelper.COURSE_END_DATE_COLUMN, courseModel.getEndDate());

        database.update(DBhelper.COURSE_TABLE_NAME,values, DBhelper.COLUMN_ID
                + " = " + courseModel.getId(), null);
        return true;
    }
}
