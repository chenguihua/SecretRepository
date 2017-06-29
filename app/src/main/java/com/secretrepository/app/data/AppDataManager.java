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
        return null;
    }

    @Override
    public void setLocalUsername(String name) {

    }

    @Override
    public String getLocalPassword() {
        return null;
    }

    @Override
    public void setLocalPassword(String name) {

    }

    @Override
    public Observable<List<User>> getAllUser() {
        return mDbHelper.getAllUser();
    }
}
