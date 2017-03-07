package com.secretrepository.app.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;

import com.secretrepository.app.data.LoginMsg;
import com.secretrepository.app.data.source.LoginMsgDataSource;
import com.secretrepository.app.data.source.local.MsgPersistenceContract.MsgEntry;
import com.secretrepository.app.util.crypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2017/3/3.
 */

public class LoginMsgLocalDataSource implements LoginMsgDataSource {

    private static LoginMsgLocalDataSource mInstance;
    private LoginMsgDBHelper mDbHelper;
    String IMEI = null;

    private LoginMsgLocalDataSource(Context context) {
        mDbHelper = new LoginMsgDBHelper(context);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = tm.getDeviceId();
    }

    public static LoginMsgLocalDataSource getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoginMsgLocalDataSource(context);
        }
        return mInstance;
    }

    @Override
    public void getAllMsgTitle(LoadAllMsgTitleCallBack callBack) {
        List<LoginMsg> msgList = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                MsgEntry.COLUMN_NAME_ENTRY_ID,
                MsgEntry.COLUMN_NAME_TITLE,
                MsgEntry.COLUMN_NAME_USERNAME,
                MsgEntry.COLUMN_NAME_DESCRIPTION,
        };

        Cursor c = db.query(MsgEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String entryId = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_ENTRY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_TITLE));
                String username = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_USERNAME));
                String description = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_DESCRIPTION));
                LoginMsg msg = new LoginMsg(entryId, title, username, "", description);
                msgList.add(msg);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        if (msgList.isEmpty()) {
            callBack.onDataNotAvailable();
        } else {
            callBack.onDataLoaded(msgList);
        }
    }

    @Override
    public void getMsgDetail(String id, LoadMsgCallback callback) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                MsgEntry.COLUMN_NAME_ENTRY_ID,
                MsgEntry.COLUMN_NAME_TITLE,
                MsgEntry.COLUMN_NAME_USERNAME,
                MsgEntry.COLUMN_NAME_PASSWORD,
                MsgEntry.COLUMN_NAME_DESCRIPTION
        };

        String selection = MsgEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor c = db.query(MsgEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        LoginMsg msg = null;
        try {
            if (c != null && c.getCount() > 0) {
                c.moveToFirst();
                String entryId = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_ENTRY_ID));
                String title = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_TITLE));
                String username = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_USERNAME));
                String password = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_PASSWORD));
                String description = c.getString(c.getColumnIndexOrThrow(MsgEntry.COLUMN_NAME_DESCRIPTION));
                msg = new LoginMsg(entryId, title, username, AESCrypt.decrypt(IMEI, password), description);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            db.close();
        }

        if (msg != null) {
            callback.onDataLoaded(msg);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void insertLoginMsg(@NonNull LoginMsg record) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(MsgEntry.COLUMN_NAME_ENTRY_ID, record.getId());
            values.put(MsgEntry.COLUMN_NAME_TITLE, record.getTitle());
            values.put(MsgEntry.COLUMN_NAME_USERNAME, record.getUsername());
            values.put(MsgEntry.COLUMN_NAME_PASSWORD, AESCrypt.encrypt(IMEI, record.getPassword()));
            values.put(MsgEntry.COLUMN_NAME_DESCRIPTION, record.getDescription());
            db.insert(MsgEntry.TABLE_NAME, null, values);

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @Override
    public void deleteLoginMsg(String id) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selection = MsgEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = {id};
        db.delete(MsgEntry.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
}
