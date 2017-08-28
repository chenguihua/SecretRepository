package com.secretrepository.app.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.secretrepository.app.MyApplication;
import com.secretrepository.app.di.Component.ActivityComponent;
import com.secretrepository.app.di.Component.DaggerActivityComponent;
import com.secretrepository.app.di.module.ActivityModule;
import com.secretrepository.app.service.KeyCache;

/**
 * Created by chenguihua on 2017/6/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;
    KeyCache mKeyCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .applicationComponent(((MyApplication)getApplication()).getApplicationComponent())
                .build();
        mKeyCache = KeyCache.getInstance();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void saveSecretKey(String key) {
        mKeyCache.setKey(key);
    }

    public String loadSecretKey() {
        return mKeyCache.getKey();
    }

}
