package com.secretrepository.app.ui.display;

import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/7/5.
 */

public interface UserContract {
    interface View extends MvpView {
        void showInfoOnUI(String title, String username, String password);
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {
        void show(String title, String username, String password, String code);
    }
}
