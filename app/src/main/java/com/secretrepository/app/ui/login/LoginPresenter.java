package com.secretrepository.app.ui.login;

import android.text.TextUtils;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2017/6/29.
 */

public class LoginPresenter<V extends LoginContract.View> extends BasePresenter<V> implements LoginContract.Presenter<V> {
    private static final String TAG = "LoginPresenter";

    String mUsername;
    @Inject
    public LoginPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        mUsername = getDataManager().getLocalUsername();
        if (TextUtils.isEmpty(mUsername)) {
            getView().showFirstLoginTips();
        }
    }

    @Override
    public void doLogin(String username, String password) {
        if (checkNotNull(username) || checkNotNull(password)) {
            getView().showErrorLoginInfo("The username or password can't be null");
            return;
        }

        if (TextUtils.isEmpty(mUsername)) {
            LegalCheck checker = new LegalCheck();
            String u = getDataManager().getLocalUsername();
            int errorId;
            if ((errorId = checker.check(u)) != LegalCheck.NO_ERROR) {
                getView().showErrorLoginInfo(getErrorString(errorId));
                return;
            }
            String p = getDataManager().getLocalPassword();
            if ((errorId = checker.check(p)) != LegalCheck.NO_ERROR) {
                getView().showErrorLoginInfo(getErrorString(errorId));
                return;
            }
            getDataManager().setLocalUsername(username);
            getDataManager().setLocalPassword(password);
            getView().openMainActivity();
        } else {
            if (getDataManager().getLocalUsername().equals(username)
                    && getDataManager().getLocalPassword().equals(password)) {
                getView().openMainActivity();
            } else {
                getView().showErrorLoginInfo("incorrect username or password!");
            }
        }
    }

    String getErrorString(@LegalCheck.CheckError int errId) {
        switch (errId) {
            case LegalCheck.NO_ERROR:
                return null;
            case LegalCheck.EMPTY_ERROR:
                return "The username or password can't be null";
            case LegalCheck.LENGTH_LIMIT_ERROR:
                return "The username or password length is illegal";
            case LegalCheck.ILLEGAL_CHAR_ERROR:
                return "The username or password contains illegal character";
        }
        return "other error";
    }

    public boolean checkNotNull(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

}
