package com.secretrepository.app.di.module;

import com.secretrepository.app.di.PreActivity;
import com.secretrepository.app.ui.edit.EditContract;
import com.secretrepository.app.ui.edit.EditPresenter;
import com.secretrepository.app.ui.display.UserContract;
import com.secretrepository.app.ui.display.UserPresenter;
import com.secretrepository.app.ui.login.LoginContract;
import com.secretrepository.app.ui.login.LoginPresenter;
import com.secretrepository.app.ui.main.MainContract;
import com.secretrepository.app.ui.main.MainPresenter;
import com.secretrepository.app.ui.splash.SplashContract;
import com.secretrepository.app.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chenguihua on 2017/6/29.
 */

@Module
public class ActivityModule {

    @PreActivity
    @Provides
    SplashContract.Presenter<SplashContract.View> provideSplashPresenter(SplashPresenter<SplashContract.View> presenter) {
        return presenter;
    }

    @PreActivity
    @Provides
    LoginContract.Presenter<LoginContract.View> provideLoginPresenter(LoginPresenter<LoginContract.View> presenter) {
        return presenter;
    }

    @PreActivity
    @Provides
    MainContract.Presenter<MainContract.View> provideMainPresenter(MainPresenter<MainContract.View> presenter) {
        return presenter;
    }

    @PreActivity
    @Provides
    EditContract.Presenter<EditContract.View> provideEditPresenter(EditPresenter<EditContract.View> presenter) {
        return presenter;
    }

    @PreActivity
    @Provides
    UserContract.Presenter<UserContract.View> provideUserPresenter(UserPresenter<UserContract.View> presenter) {
        return presenter;
    }

}
