package com.secretrepository.app.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenguihua on 2017/3/3.
 */

public class LoginMsgDBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "login.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRY =
            "CREATE TABLE " + MsgPersistenceContract.MsgEntry.TABLE_NAME + " (" +
                    MsgPersistenceContract.MsgEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    MsgPersistenceContract.MsgEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    MsgPersistenceContract.MsgEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    MsgPersistenceContract.MsgEntry.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    MsgPersistenceContract.MsgEntry.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    MsgPersistenceContract.MsgEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE  +
                    " )";

    public LoginMsgDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
