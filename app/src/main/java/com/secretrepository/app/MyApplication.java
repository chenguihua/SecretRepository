package com.secretrepository.app;

import android.app.Application;

import com.secretrepository.app.di.Component.ApplicationComponent;
import com.secretrepository.app.di.Component.DaggerApplicationComponent;
import com.secretrepository.app.di.module.ApplicationModule;

/**
 * Created by chenguihua on 2017/6/29.
 */

public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    static {
        System.loadLibrary("secret-lib");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
