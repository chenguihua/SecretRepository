package com.secretrepository.app.di.module;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.secretrepository.app.data.AppDataManager;
import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.data.db.AppDbHelper;
import com.secretrepository.app.data.db.DbHelper;
import com.secretrepository.app.data.prefs.AppPreferencesHelper;
import com.secretrepository.app.data.prefs.PreferencesHelper;
import com.secretrepository.app.di.ApplicationContext;
import com.secretrepository.app.di.DatabaseInfo;
import com.secretrepository.app.di.PreferenceInfo;
import com.secretrepository.app.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenguihua on 2017/6/29.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @ApplicationContext
    @Provides
    Context provideContext() {
        return mApplication;
    }

    @DatabaseInfo
    @Provides
    String provideDatabaseName() {
        return AppConstants.DATABASE_NAME;
    }

    @PreferenceInfo
    @Provides
    String providePrefName() {
        return AppConstants.PREFERENCE_NAME;
    }

    @Singleton
    @Provides
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Singleton
    @Provides
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Singleton
    @Provides
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
