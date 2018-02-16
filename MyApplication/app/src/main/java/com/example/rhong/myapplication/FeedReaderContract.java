package com.example.rhong.myapplication;

import android.provider.BaseColumns;

/**
 * Created by rhong on 2/16/18.
 */

public class FeedReaderContract {
    private FeedReaderContract(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "userlist.db";
        public static final String COLUMN_NAME_TITLE = "name";
    }
}
