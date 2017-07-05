package com.secretrepository.app.ui.display;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2017/7/5.
 */

public class UserPresenter<V extends UserContract.View> extends BasePresenter<V> implements UserContract.Presenter<V> {

    @Inject
    public UserPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
