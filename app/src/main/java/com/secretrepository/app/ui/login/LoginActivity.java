package com.secretrepository.app.ui.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.data.AppDataManager;
import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.di.Component.DaggerActivityComponent;
import com.secretrepository.app.di.module.ActivityModule;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.main.MainActivity;
import com.secretrepository.app.util.CryptUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


/**
 * Created by chenguihua on 2016/6/6.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @Inject
    LoginContract.Presenter<LoginContract.View> mPresenter;

    @BindView(R.id.et_username)
    TextInputEditText mEtUsername;

    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;

    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;

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
    public void showFirstLoginTips() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Tips")
                .setMessage("The code will be the first you use to enter the app.")
                .setPositiveButton("get it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        dialog.show();
    }


    @Override
    public void showErrorLoginInfo(CharSequence error) {
        mTilPassword.setError(error);
    }

    @Override
    public void openMainActivity(String serial) {
        Intent intent = MainActivity.getEntryActivity(this);
        startActivity(intent);
        finish();
    }

    @OnTextChanged(value = {R.id.et_username, R.id.et_password})
    void onEditTextChanged() {
        if (!TextUtils.isEmpty(mTilPassword.getError())){
            mTilPassword.setErrorEnabled(false);
        }

    }

}
