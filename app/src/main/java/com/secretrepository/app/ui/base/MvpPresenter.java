package com.secretrepository.app.ui.base;

/**
 * Created by chenguihua on 2017/6/16.
 */

public interface MvpPresenter <T extends MvpView> {

    void onAttach(T view);

    void onDetach();
}
