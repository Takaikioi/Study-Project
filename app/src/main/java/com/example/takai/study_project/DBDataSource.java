package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;

/**
 * Created by hephalump on 30/12/2015.
 */
public class DBDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DBhelper dbHelper;
    private String[] allColumns = { DBhelper.COLUMN_ID,
            DBhelper.COLUMN_NAME };

    public DBDataSource(Context context) {
        dbHelper = new DBhelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public DataItem createData (String data) {
        ContentValues values = new ContentValues();
        values.put(DBhelper.COLUMN_NAME, data);
        long insertId = database.insert(DBhelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DBhelper.TABLE_NAME,
                allColumns, DBhelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DataItem newDataItem = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataItem dataItem = cursorToDataItem(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private DataItem cursorToDataItem(Cursor cursor) {
        DataItem dataItem = new DataItem();
        dataItem.setId(cursor.getLong(0));
        dataItem.setData(cursor.getString(1));
        return dataItem;
    }
}
