package com.secretrepository.app.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.secretrepository.app.di.ApplicationContext;
import com.secretrepository.app.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by chenguihua on 2017/6/29.
 */
@Singleton
public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_USER_NAME = "username";
    private static final String PREF_KEY_USER_PASS = "password";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context, @PreferenceInfo String filename) {
        mPrefs = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }

    @Override
    public String getLocalUsername() {
        return mPrefs.getString(PREF_KEY_USER_NAME, null);
    }

    @Override
    public void setLocalUsername(String name) {
        mPrefs.edit().putString(PREF_KEY_USER_NAME, name).apply();
    }

    @Override
    public String getLocalPassword() {
        return mPrefs.getString(PREF_KEY_USER_PASS, null);
    }

    @Override
    public void setLocalPassword(String name) {
        mPrefs.edit().putString(PREF_KEY_USER_PASS, name).apply();
    }
}
