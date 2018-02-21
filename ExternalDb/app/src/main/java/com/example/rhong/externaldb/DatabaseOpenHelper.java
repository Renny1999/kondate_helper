package com.example.rhong.externaldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rhong on 2/20/18.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE userfoodlist (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IS EXISTS userfoodlist";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "userlist.db";

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean addData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);

        long result = db.insert("userfoodlist", null, contentValues);
        db.close();
        if(result == -1){
            return false;
        }
        return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM userfoodlist", null);
        return data;
    }

}
