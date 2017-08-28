package com.secretrepository.app.ui.display;

import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

/**
 * Created by chenguihua on 2017/7/5.
 */

public interface UserContract {
    interface View extends MvpView {
        void showInfoOnUI(String title, String username, String password);
        void onUserDelete();
        void openEditActivity(User user);
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {
        void show(Long id, String key);
        void delete(User user);
        void edit(User user);
    }
}
