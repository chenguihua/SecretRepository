package com.secretrepository.app.ui.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class UserActivity extends BaseActivity implements UserContract.View{
    private static final String TAG = "UserActivity";
    public static final String TITLE = "title";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_password)
    TextView tv_password;

    @Inject
    UserContract.Presenter<UserContract.View> mPresenter;


    public static Intent getEntryIntent(Context context, User user, String code) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(TITLE, user.getDescription());
        intent.putExtra(USERNAME, user.getUsername());
        intent.putExtra(PASSWORD, user.getPassword());
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String title = intent.getStringExtra(TITLE);
        String username = intent.getStringExtra(USERNAME);
        String password = intent.getStringExtra(PASSWORD);
        mPresenter.show(title, username, password, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void showInfoOnUI(String title, String username, String password) {
        collapsingToolbarLayout.setTitle(title);
        tv_username.setText(username);
        tv_password.setText(password);
    }
}
