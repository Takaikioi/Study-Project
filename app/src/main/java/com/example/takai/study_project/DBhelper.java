package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by hephalump on 30/12/2015
 */
public class DBhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 24;
    public static final String DATABASE_NAME = "StudyDataBase";
    public static final String COURSE_TABLE_NAME = "Course";
    public static final String COLUMN_ID = "_id";
    public static final String COURSE_NAME_COLUMN = "Name";
    public static final String COURSE_CODE_COLUMN = "Code";
    public static final String COURSE_COLOUR_COLUMN = "Colour";
    public static final String COURSE_ACTIVE_COLUMN = "Active";
    public static final String COURSE_START_DATE_COLUMN = "StartDate";
    public static final String COURSE_END_DATE_COLUMN = "EndDate";
    public static final String MODULE_TABLE_NAME = "Module";
    public static final String COURSE_FOREIGN_ID = "CourseForeignKey";
    public static final String MODULE_NAME_COLUMN = "Name";
    public static final String MODULE_START_DATE_COLUMN = "StartDate";
    public static final String MODULE_END_DATE_COLUMN = "EndDate";

    private static final String DB_TABLE_CREATE_COURSE ="create table "
            + COURSE_TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COURSE_NAME_COLUMN
            + " text not null, " + COURSE_CODE_COLUMN + " text not null, " +COURSE_COLOUR_COLUMN+ " int not null, " +COURSE_ACTIVE_COLUMN+
            " bool not null, " + COURSE_START_DATE_COLUMN + "  text, "+COURSE_END_DATE_COLUMN+" text );";
    private static final String DB_TABLE_CREATE_MODULE ="create table "
            + MODULE_TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + MODULE_NAME_COLUMN
            + " text not null, " + COURSE_FOREIGN_ID + " integer not null, " + MODULE_START_DATE_COLUMN + "  text, "+MODULE_END_DATE_COLUMN+" text );";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_TABLE_CREATE_COURSE );
        db.execSQL(DB_TABLE_CREATE_MODULE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBhelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MODULE_TABLE_NAME);
        onCreate(db);
    }
    //TODO add extra crud methods
    public boolean insertCourseData(String name, String code, String courseColor, boolean active, Long startDate, Long endDate)
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

    public boolean insertModuleData(String courseID, String name, Long startDate, Long endDate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MODULE_NAME_COLUMN, name);
        contentValues.put(COURSE_FOREIGN_ID, courseID);
        contentValues.put(MODULE_START_DATE_COLUMN, startDate);
        contentValues.put(MODULE_END_DATE_COLUMN, endDate);
        db.insert(MODULE_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getCourseData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + COURSE_TABLE_NAME + "", null );
    }

    public Cursor getModuleData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + MODULE_TABLE_NAME + "", null );
    }

    public void open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
    }




}
