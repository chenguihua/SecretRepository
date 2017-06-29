package com.secretrepository.app.ui.base;

import com.secretrepository.app.data.DataManager;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2017/6/16.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mView;

    private final DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onAttach(V view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public V getView() {
        return mView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
