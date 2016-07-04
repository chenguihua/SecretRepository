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
        String USER_TABLE = "user_table";
        String ADDRESS_TABLE = "address_table";
    }

    public interface UserColumns {
        String _ID = "id";
        String LOGIN_NAME = "user_name";
        String LOGIN_PASSWORD = "password";
        String ADDRESS_ID = "address_id";
    }

    public interface AddressColumns {
        String _ID = "id";
        String ADDRESS_NAME = "address_name";
        String WEBSITE = "website";
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
