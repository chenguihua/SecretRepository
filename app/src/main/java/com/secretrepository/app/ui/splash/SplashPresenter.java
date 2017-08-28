package com.secretrepository.app.ui.splash;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.ui.base.BasePresenter;
import com.secretrepository.app.util.AppConstants;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2017/6/29.
 */

public class SplashPresenter<V extends SplashContract.View> extends BasePresenter<V> implements SplashContract.Presenter<V> {

    @Inject
    public SplashPresenter(DataManager mDataManager) {
        super(mDataManager);
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        if (!AppConstants.DEBUG_NO_LOGIN)
            getView().openLoginActivity();
        else
            getView().openMainActivity();
    }
}
