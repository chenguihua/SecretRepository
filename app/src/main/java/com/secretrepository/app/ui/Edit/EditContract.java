package com.secretrepository.app.ui.Edit;

import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/7/5.
 */

public interface EditContract {

    interface View extends MvpView {
        void onEditFinished();
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {
        void submit(String username, String password, String description);
    }
}
