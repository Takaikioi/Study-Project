package com.example.takai.study_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 13/02/2016.
 */
public class ModuleDataSource {
    // Database fields
    private SQLiteDatabase database;
    private ModuleDBhelper dbHelper;
    private String[] allColumns = { ModuleDBhelper.COLUMN_ID,ModuleDBhelper.COURSE_FOREIGN_ID,
            ModuleDBhelper.MODULE_NAME_COLUMN, ModuleDBhelper.MODULE_START_DATE_COLUMN,
            ModuleDBhelper.MODULE_END_DATE_COLUMN };

    public ModuleDataSource(Context context) {
        dbHelper = new ModuleDBhelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ModuleModel createData (String name, String courseID, String startDate, String endDate) {
        ContentValues values = new ContentValues();
        values.put(ModuleDBhelper.MODULE_NAME_COLUMN, name);
        values.put(ModuleDBhelper.COURSE_FOREIGN_ID, courseID);
        values.put(ModuleDBhelper.MODULE_START_DATE_COLUMN, startDate);
        values.put(ModuleDBhelper.MODULE_END_DATE_COLUMN, endDate);

        long insertId = database.insert(ModuleDBhelper.MODULE_TABLE_NAME, null,
                values);
        Cursor cursor = database.query(ModuleDBhelper.MODULE_TABLE_NAME,
                allColumns, ModuleDBhelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ModuleModel newModuleModel = cursorToModuleModel(cursor);
        cursor.close();
        return newModuleModel;
    }

    public void deleteData(ModuleModel moduleModel) {
        long id = moduleModel.getId();
        System.out.println("Data deleted with id: " + id);
        database.delete(ModuleDBhelper.MODULE_TABLE_NAME, ModuleDBhelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<ModuleModel> getAllDataItems(int courseID) {
        List<ModuleModel> moduleModels = new ArrayList<ModuleModel>();

        Cursor cursor = database.query(ModuleDBhelper.MODULE_TABLE_NAME, allColumns,ModuleDBhelper.COURSE_FOREIGN_ID
        +" = " + courseID, null,null,null,null );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ModuleModel moduleModel = cursorToModuleModel(cursor);
            moduleModels.add(moduleModel);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return moduleModels;
    }

    //TODO revise data access methods.
    private ModuleModel cursorToModuleModel(Cursor cursor) {
        int numCol = cursor.getColumnCount();
        int numRow = cursor.getCount();
        ModuleModel moduleModel = new ModuleModel();
        moduleModel.setId(cursor.getLong(0));
        moduleModel.setCourseId(cursor.getLong(1));
        moduleModel.setName(cursor.getString(2));

        return moduleModel;
    }

    public int getNumberOfElements(int courseID){
        Cursor cursor = database.query(ModuleDBhelper.MODULE_TABLE_NAME,
                allColumns, ModuleDBhelper.COURSE_FOREIGN_ID
                        +" = " + courseID, null, null, null, null);
        int numRows = cursor.getColumnCount();
        return numRows;
    }
    public boolean updateElement(ModuleModel moduleModel){
        ContentValues values = new ContentValues();
        values.put(ModuleDBhelper.MODULE_NAME_COLUMN, moduleModel.getName());
        database.update(ModuleDBhelper.MODULE_TABLE_NAME,values,ModuleDBhelper.COLUMN_ID
                + " = " + moduleModel.getId(), null);
        return true;
    }
}
