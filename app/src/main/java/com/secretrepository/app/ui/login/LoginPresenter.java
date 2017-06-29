package com.secretrepository.app.ui.login;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2017/6/29.
 */

public class LoginPresenter<V extends LoginContract.View> extends BasePresenter<V> implements LoginContract.Presenter<V> {

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void doLogin(String username, String password) {

        String prefName = getDataManager().getLocalUsername();


        if (true) {
            getView().openMainActivity();
        } else {

        }
    }

}
