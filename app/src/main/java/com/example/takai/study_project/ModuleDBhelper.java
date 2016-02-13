package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by hephalump on 13/02/2016.
 */
public class ModuleDBhelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 0;
    public static final String DATABASE_NAME = "ModuleDatabase";
    public static final String MODULE_TABLE_NAME = "Module";
    public static final String COLUMN_ID = "_id";
    public static final String COURSE_FOREIGN_ID = "CourseForeignKey";
    public static final String MODULE_NAME_COLUMN = "Name";
    public static final String MODULE_START_DATE_COLUMN = "StartDate";
    public static final String MODULE_END_DATE_COLUMN = "EndDate";

    private static final String DB_TABLE_CREATE ="create table "
            + MODULE_TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + MODULE_NAME_COLUMN
            + " text not null, " + COURSE_FOREIGN_ID + " integer not null, " + MODULE_START_DATE_COLUMN + "  text, "+MODULE_END_DATE_COLUMN+" text );";



    public ModuleDBhelper(Context context) {
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
        db.execSQL("DROP TABLE IF EXISTS " + MODULE_TABLE_NAME);
        onCreate(db);
    }
    //TODO add extra crud methods
    public boolean insertData(String courseID, String name, Long startDate, Long endDate)
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

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from " + MODULE_TABLE_NAME + "", null );
    }
    public void open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
    }

}
