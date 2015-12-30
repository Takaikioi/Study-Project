package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by hephalump on 30/12/2015.
 */
public class DBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DatabaseName";
    public static final String TABLE_NAME = "MyTableName";
    public static final String PRIMARY_KEY = "_id integer primary key";
    public static final String COLUMN_NAME = "MyColumn";
    public static final String COLUMN_TYPE_TEXT = "text";
    private static final String DB_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    PRIMARY_KEY  +
                    COLUMN_NAME + "" + COLUMN_TYPE_TEXT + ");";



    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBhelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, data);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME + "", null );
        return res;
    }
    public void open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
    }




}
