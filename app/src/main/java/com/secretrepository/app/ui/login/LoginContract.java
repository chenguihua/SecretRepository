package com.secretrepository.app.ui.login;

import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/6/29.
 */

public interface LoginContract {

    interface View extends MvpView {

        void showFirstLoginTips();

        void showErrorLoginInfo(CharSequence str);

        void openMainActivity(String serial);
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {

        void doLogin(String username, String password);
    }

}
