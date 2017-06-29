package com.secretrepository.app.di.Component;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by chenguihua on 2017/6/29.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    DataManager getDataManager();
}
