package com.example.rhong.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
