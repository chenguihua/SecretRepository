package com.secretrepository.app.ui.main;

import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.MvpPresenter;
import com.secretrepository.app.ui.base.MvpView;

import java.util.List;

/**
 * Created by chenguihua on 2017/7/4.
 */

public interface MainContract {

    interface View extends MvpView {

        void openSettingsActivity();

        void openEditActivity();

        void openUserActivity(User user);

        void updateLoadedData(List<User> users);

    }

    interface Presenter<V extends View> extends MvpPresenter<V> {

        void load();

        void onFabClick(android.view.View view);

        void showUserInformation(User user);
    }

}
