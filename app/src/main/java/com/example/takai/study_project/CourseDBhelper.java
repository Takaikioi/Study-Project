package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by hephalump on 30/12/2015.
 */
public class CourseDBhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 17;
    public static final String DATABASE_NAME = "CourseDatabase";
    public static final String COURSE_TABLE_NAME = "Course";
    public static final String COLUMN_ID = "_id";
    public static final String COURSE_NAME_COLUMN = "Name";
    public static final String COURSE_CODE_COLUMN = "Code";
    public static final String COURSE_COLOUR_COLUMN = "Colour";
    public static final String COURSE_ACTIVE_COLUMN = "Active";
    public static final String COURSE_START_DATE_COLUMN = "StartDate";
    public static final String COURSE_END_DATE_COLUMN = "EndDate";

    private static final String DB_TABLE_CREATE ="create table "
            + COURSE_TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COURSE_NAME_COLUMN
            + " text not null, " + COURSE_CODE_COLUMN + " text not null, " +COURSE_COLOUR_COLUMN+ " int not null, " +COURSE_ACTIVE_COLUMN+
            " bool not null, " + COURSE_START_DATE_COLUMN + "  text, "+COURSE_END_DATE_COLUMN+" text );";



    public CourseDBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CourseDBhelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        onCreate(db);
    }
    //TODO add extra crud methods
    public boolean insertData(String name, String code, String courseColor, boolean active, Long startDate, Long endDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME_COLUMN, name);
        contentValues.put(COURSE_CODE_COLUMN, code);
        contentValues.put(COURSE_COLOUR_COLUMN, courseColor);
        contentValues.put(COURSE_ACTIVE_COLUMN, active);
        contentValues.put(COURSE_START_DATE_COLUMN, startDate);
        contentValues.put(COURSE_END_DATE_COLUMN, endDate);
        db.insert(COURSE_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + COURSE_TABLE_NAME + "", null );
    }
    public void open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
    }




}
