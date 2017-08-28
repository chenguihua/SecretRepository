package com.secretrepository.app.data;

import com.secretrepository.app.data.db.DbHelper;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.data.prefs.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by chenguihua on 2017/6/29.
 */
@Singleton
public class AppDataManager implements DataManager{

    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.mDbHelper = dbHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public String getLocalUsername() {
        return mPreferencesHelper.getLocalUsername();
    }

    @Override
    public void setLocalUsername(String name) {
        mPreferencesHelper.setLocalUsername(name);
    }

    @Override
    public String getLocalPassword() {
        return mPreferencesHelper.getLocalPassword();
    }

    @Override
    public void setLocalPassword(String name) {
        mPreferencesHelper.setLocalPassword(name);
    }

    @Override
    public Observable<List<User>> queryUsers() {
        return mDbHelper.queryUsers();
    }

    @Override
    public Observable<User> queryUser(Long id) {
        return mDbHelper.queryUser(id);
    }

    @Override
    public Observable<Long> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<Boolean> deleteUser(User user) {
        return mDbHelper.deleteUser(user);
    }

    @Override
    public Observable<Boolean> updateUser(User user) {
        return mDbHelper.updateUser(user);
    }
}
