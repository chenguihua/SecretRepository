package com.secretrepository.app.ui.login;

import android.text.TextUtils;
import android.util.Log;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.di.PreActivity;
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
//        if (checkNotNull(username) || checkNotNull(password)) {
//            getView().showErrorLoginInfo("The username or password can't be null");
//            return;
//        }
//
//        boolean isFirstTimeLogin = TextUtils.isEmpty(mUsername);
//        // first time to login, check the name and the password whether is legal.
//        if (isFirstTimeLogin) {
//            LegalFormatCheck checker = new LegalFormatCheck();
//            int errorId = LegalFormatCheck.NO_ERROR;
//            if ((errorId = checker.check(username)) != LegalFormatCheck.NO_ERROR
//                    && (errorId = checker.check(password)) != LegalFormatCheck.NO_ERROR)
//            {
//                getView().showErrorLoginInfo(getErrorString(errorId));
//                return;
//            }
//            String serial = register(username, password);
//            Log.d(TAG, "doLogin: serial " + serial);
//            if (!TextUtils.isEmpty(serial)) {
//                getView().openMainActivity(serial);
//            }
//        } else {
//            String serial = login(username, password);
//            if (!TextUtils.isEmpty(serial)) {
//                Log.d(TAG, "doLogin: serial is " + serial);
//                getView().openMainActivity(serial);
//            } else {
//                getView().showErrorLoginInfo("incorrect login.");
//            }
//
//        }

    }

    String getErrorString(@LegalFormatCheck.CheckError int errId) {
        switch (errId) {
            case LegalFormatCheck.NO_ERROR:
                return null;
            case LegalFormatCheck.EMPTY_ERROR:
                return "The username or password can't be null";
            case LegalFormatCheck.LENGTH_LIMIT_ERROR:
                return "The username or password length is illegal";
            case LegalFormatCheck.ILLEGAL_CHAR_ERROR:
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

//    private native String register(String username, String password);
//    private native String login(String username, String password);
//
//    static {
//        System.loadLibrary("secret-lib");
//    }
}
