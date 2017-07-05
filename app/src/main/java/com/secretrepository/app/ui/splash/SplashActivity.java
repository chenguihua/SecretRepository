package com.secretrepository.app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.secretrepository.app.R;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.login.LoginActivity;
import com.secretrepository.app.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by chenguihua on 2017/6/16.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter<SplashContract.View> mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.onAttach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getEntryIntent(this);
        startActivity(intent);
        finish();
    }

    @VisibleForTesting
    public void openMainActivity() {
        Intent intent = MainActivity.getEntryActivity(this, null);
        startActivity(intent);
        finish();
    }
}
