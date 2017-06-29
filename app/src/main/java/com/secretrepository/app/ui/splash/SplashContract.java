package com.secretrepository.app.ui.splash;

import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/6/29.
 */

public interface SplashContract {

    interface View extends MvpView {

        void openLoginActivity();

        void openMainActivity();
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {

    }

}
