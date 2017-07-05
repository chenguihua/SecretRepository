package com.secretrepository.app.ui.display;

import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/7/5.
 */

public interface UserContract {
    interface View extends MvpView {

    }

    interface Presenter<V extends View> extends MvpPresenter<V> {

    }
}
