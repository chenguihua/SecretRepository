package com.secretrepository.app.ui.edit;

import com.secretrepository.app.data.db.model.User;
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
        void editNew(String key, User user);
        void editOld(String key, User user);
    }
}
