package com.secretrepository.app.di.Component;

import com.secretrepository.app.di.PreActivity;
import com.secretrepository.app.di.module.ActivityModule;
import com.secretrepository.app.ui.edit.EditActivity;
import com.secretrepository.app.ui.display.UserActivity;
import com.secretrepository.app.ui.login.LoginActivity;
import com.secretrepository.app.ui.main.MainActivity;
import com.secretrepository.app.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by chenguihua on 2017/6/29.
 */

@PreActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(MainActivity activity);

    void inject(EditActivity activity);

    void inject(UserActivity activity);
}
