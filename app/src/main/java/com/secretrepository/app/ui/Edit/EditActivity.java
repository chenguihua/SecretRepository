package com.secretrepository.app.ui.Edit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by chenguihua on 2016/6/16.
 */
public class EditActivity extends BaseActivity implements EditContract.View {

    @Inject
    EditContract.Presenter<EditContract.View> mPresenter;

    @BindView(R.id.login_title)
    EditText etHeadTitle;

    @BindView(R.id.login_username)
    EditText etUsername;

    @BindView(R.id.login_password)
    EditText etPassword;

    public static Intent getEntryIntent(Context context) {
        Intent intent = new Intent(context, EditActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit)
    void onBtnFinishedClick(View view) {
        mPresenter.submit(
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                etHeadTitle.getText().toString());
    }

    @Override
    public void onEditFinished() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
