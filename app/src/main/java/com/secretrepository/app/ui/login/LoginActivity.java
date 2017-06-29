package com.secretrepository.app.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.secretrepository.app.R;
import com.secretrepository.app.data.AppDataManager;
import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.di.Component.DaggerActivityComponent;
import com.secretrepository.app.di.module.ActivityModule;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by chenguihua on 2016/6/6.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter<LoginContract.View> mPresenter;

    @BindView(R.id.et_username)
    TextInputEditText mEtUsername;

    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;

    public static Intent getEntryIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.onAttach(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.btn_login)
    void onLoginBtnClick(View view) {
        mPresenter.doLogin(
                mEtUsername.getText().toString(),
                mEtPassword.getText().toString());
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getEntryActivity(this);
        startActivity(intent);
        finish();
    }
}
