package com.secretrepository.app.ui.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.util.CryptUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by chenguihua on 2016/6/16.
 */
public class EditActivity extends BaseActivity implements EditContract.View {

    private static int EDIT_TYPE_NEW = 0;
    private static int EDIT_TYPE_EDIT = 1;

    private int mEditType;
    private User mUser;

    @Inject
    EditContract.Presenter<EditContract.View> mPresenter;

    @BindView(R.id.edit_toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_title)
    EditText etHeadTitle;

    @BindView(R.id.input_username)
    EditText etUsername;

    @BindView(R.id.input_password)
    EditText etPassword;

    @BindView(R.id.input_more)
    EditText etMore;

    public static Intent getEntryIntent(Context context, User user) {
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra("user_info", user);
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

    void showEditInfo(User user) {
        etUsername.setText(user.getUsername());
        etPassword.setText(new CryptUtil().decrypt(loadSecretKey(), user.getPassword()));
        etHeadTitle.setText(user.getDescription());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUser = getIntent().getParcelableExtra("user_info");
        if (mUser != null) {
            showEditInfo(mUser);
            mEditType = EDIT_TYPE_EDIT;
        } else {
            mEditType = EDIT_TYPE_NEW;
        }
    }

    @OnClick(R.id.edit_cancel)
    void onBtnCancelClick(View view) {
        finish();
    }

    @OnClick(R.id.edit_done)
    void onBtnDoneClick(View view) {
        if (mUser == null) {
            mUser = new User();
        }
        mUser.setUsername(etUsername.getText().toString());
        mUser.setPassword(new CryptUtil().encrypt(loadSecretKey(), etPassword.getText().toString()));
        mUser.setDescription(etHeadTitle.getText().toString());
        if (mEditType == EDIT_TYPE_NEW) {
            mPresenter.editNew(loadSecretKey(), mUser);
        } else if (mEditType == EDIT_TYPE_EDIT) {
            mPresenter.editOld(loadSecretKey(), mUser);
        }
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
