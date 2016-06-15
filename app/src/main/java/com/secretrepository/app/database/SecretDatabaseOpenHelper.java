package com.secretrepository.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chenguihua on 2016/6/14.
 */
public class SecretDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "secret.db";

    public interface Tables {
        static final String USER_TABLE = "user_table";
        static final String ADDRESS_TABLE = "address_table";
    }

    public interface UserColumns {
        static final String _ID = "id";
        static final String LOGIN_NAME = "user_name";
        static final String LOGIN_PASSWORD = "password";
        static final String ADDRESS_ID = "address_id";
    }

    public interface AddressColumns {
        static final String _ID = "id";
        static final String ADDRESS_NAME = "address_name";
        static final String WEBSITE = "website";
    }

    public SecretDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       setupTables(db);
    }

    private void setupTables(SQLiteDatabase db) {
        dropTables(db);
        db.execSQL("CREATE TABLE " + Tables.ADDRESS_TABLE + " (" +
                AddressColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AddressColumns.ADDRESS_NAME + " TEXT," +
                AddressColumns.WEBSITE + " TEXT" +
                ");");

        db.execSQL("CREATE TABLE " + Tables.USER_TABLE + " (" +
                UserColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserColumns.LOGIN_NAME + " TEXT," +
                UserColumns.LOGIN_PASSWORD + " TEXT," +
                UserColumns.ADDRESS_ID + " INTEGER" +
                ");");
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.ADDRESS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
