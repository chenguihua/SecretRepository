package com.secretrepository.app.data.source;

import com.secretrepository.app.data.LoginMsg;

import java.util.List;

/**
 * Created by chenguihua on 2017/3/3.
 */

public interface LoginMsgDataSource {

    interface LoadAllMsgTitleCallBack {
        void onDataLoaded(List<LoginMsg> msgList);
        void onDataNotAvailable();
    };

    interface LoadMsgCallback {
        void onDataLoaded(LoginMsg msg);
        void onDataNotAvailable();
    };

    void getAllMsgTitle(LoadAllMsgTitleCallBack callBack);
    void getMsgDetail(String id, LoadMsgCallback callback);

    void insertLoginMsg(LoginMsg record);
    void deleteLoginMsg(String id);
}
