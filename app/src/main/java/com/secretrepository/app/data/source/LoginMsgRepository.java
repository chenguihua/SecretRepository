package com.secretrepository.app.data.source;

import com.secretrepository.app.data.LoginMsg;
import com.secretrepository.app.data.source.local.LoginMsgLocalDataSource;

/**
 * Created by chenguihua on 2017/3/3.
 */

public class LoginMsgRepository implements LoginMsgDataSource {
    private static LoginMsgRepository mInstance = null;
    private final LoginMsgDataSource loginMsgLocalDataSource;

    private LoginMsgRepository(LoginMsgDataSource localDataSource) {
        loginMsgLocalDataSource = localDataSource;
    }

    public static LoginMsgRepository getInstance(LoginMsgDataSource localDataSource) {
        if (mInstance == null) {
            mInstance = new LoginMsgRepository(localDataSource);
        }
        return mInstance;
    }

    /**
     * Used to force to create a new instance next time it's called
     */
    public static void destroyInstance() {
        mInstance = null;
    }

    @Override
    public void getAllMsgTitle(LoadAllMsgTitleCallBack callBack) {
        //loginMsgLocalDataSource.getAllMsgTitle(callBack);
    }

    @Override
    public void getMsgDetail(String id, LoadMsgCallback callback) {
        loginMsgLocalDataSource.getMsgDetail(id, callback);
    }

    @Override
    public void insertLoginMsg(LoginMsg record) {
        loginMsgLocalDataSource.insertLoginMsg(record);
    }

    @Override
    public void deleteLoginMsg(String id) {
        loginMsgLocalDataSource.deleteLoginMsg(id);
    }
}
