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
    private DBhelper dbHelper;
    private String[] allColumns = { DBhelper.COLUMN_ID,
            DBhelper.COLUMN_NAME };

    public CourseDataSource(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CourseModel createData (String name) {
        ContentValues values = new ContentValues();
        values.put(DBhelper.COLUMN_NAME, name);
        long insertId = database.insert(DBhelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DBhelper.TABLE_NAME,
                allColumns, DBhelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CourseModel newCourseModel = cursorToCourseModel(cursor);
        cursor.close();
        return newCourseModel;
    }

    public void deleteData(DataItem dataItem) {
        long id = dataItem.getId();
        System.out.println("Data deleted with id: " + id);
        database.delete(DBhelper.TABLE_NAME, DBhelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<CourseModel> getAllDataItems() {
        List<CourseModel> courseModels = new ArrayList<CourseModel>();

        Cursor cursor = database.query(DBhelper.TABLE_NAME,
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

    private CourseModel cursorToCourseModel(Cursor cursor) {
        CourseModel courseModel = new CourseModel();
        courseModel.setId(cursor.getLong(0));
        courseModel.setName(cursor.getString(1));
        return courseModel;
    }
}
