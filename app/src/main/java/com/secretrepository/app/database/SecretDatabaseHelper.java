package com.secretrepository.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;

import com.secretrepository.app.database.SecretDatabaseOpenHelper.Tables;
import com.secretrepository.app.database.SecretDatabaseOpenHelper.UserColumns;
import com.secretrepository.app.database.SecretDatabaseOpenHelper.AddressColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class SecretDatabaseHelper {
    public static SecretDatabaseHelper sSingleton = null;
    private SQLiteDatabase db;

    public synchronized static SecretDatabaseHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new SecretDatabaseHelper(context);
        }
        return sSingleton;
    }

    private SecretDatabaseHelper(Context context) {
        SecretDatabaseOpenHelper dbHelper = new SecretDatabaseOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 插入一条完整的数据
     * @param userName
     * @param password
     * @param address
     * @param website
     */
    public void insert(String userName, String password, String address, String website) {
        long addressId = addressContains(address);
        if (addressId == -1) {
            addressId = addressInsert(address, website);
        }
        userInsert(userName, password, (int) addressId);
    }

    public void deleteByAddress(int id) {
        List<UserBean> users = userFindByAddressId(id);
        for (UserBean user : users) {
            userDelete(user.id);
        }
        addressDelete(id);
    }


    private long userInsert(String userName, String password, int addressId) {
        ContentValues values = new ContentValues();
        values.put(UserColumns.LOGIN_NAME, userName);
        values.put(UserColumns.LOGIN_PASSWORD, password);
        values.put(UserColumns.ADDRESS_ID, addressId);
        return db.insert(Tables.USER_TABLE, null, values);
    }

    private void userDelete(int id) {
        String[] args = {String.valueOf(id)};
        db.delete(Tables.USER_TABLE, "id=?", args);
    }

    public List<UserBean> userFindByAddressId(int addressId) {
        List<UserBean> users = new ArrayList<>();
        String selection = UserColumns.ADDRESS_ID + " = ?";
        Cursor cursor = db.query(Tables.USER_TABLE, null, selection,
                                new String[] {String.valueOf(addressId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                UserBean bean = new UserBean();
                bean.id = cursor.getInt(cursor.getColumnIndex(UserColumns._ID));
                bean.username = cursor.getString(cursor.getColumnIndex(UserColumns.LOGIN_NAME));
                bean.password = cursor.getString(cursor.getColumnIndex(UserColumns.LOGIN_PASSWORD));
                users.add(bean);
            } while (cursor.moveToNext());
        }
        return users;
    }

    private long addressInsert(String addressName, String website) {
        ContentValues values = new ContentValues();
        values.put(AddressColumns.ADDRESS_NAME, addressName);
        values.put(AddressColumns.WEBSITE, website);
        return db.insert(Tables.ADDRESS_TABLE, null, values);
    }

    public void addressDelete(int id) {
        String[] args = {String.valueOf(id)};
        db.delete(Tables.ADDRESS_TABLE, "id=?", args);
    }

    public List<AddressBean> addressFindAll() {
        List<AddressBean> addresses = new ArrayList<AddressBean>();
        Cursor cursor = db.query(Tables.ADDRESS_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                AddressBean bean = new AddressBean();
                bean.id = cursor.getInt(cursor.getColumnIndex(AddressColumns._ID));
                bean.address = cursor.getString(cursor.getColumnIndex(AddressColumns.ADDRESS_NAME));
                bean.website = cursor.getString(cursor.getColumnIndex(AddressColumns.WEBSITE));
                addresses.add(bean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return addresses;
    }

    private long addressContains(String address) {
        long result = -1;
        String selections = AddressColumns.ADDRESS_NAME + " = ?";
        Cursor cursor = db.query(Tables.ADDRESS_TABLE,
                new String[]{AddressColumns._ID},
                selections,
                new String[]{address},
                null, null, null);
        if (cursor.moveToFirst()) {
            result = cursor.getInt(cursor.getColumnIndex(AddressColumns._ID));
        }
        cursor.close();
        return result;
    }



    public final class UserBean {
        public int id;
        public String username;
        public String password;
    }

    public final class AddressBean {
        public int id;
        public String address;
        public String website;
    }

}
