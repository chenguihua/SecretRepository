package com.secretrepository.app.ui.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.edit.EditActivity;

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
    @BindView(R.id.user_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_password)
    TextView tv_password;

    User mUser;

    @Inject
    UserContract.Presenter<UserContract.View> mPresenter;


    public static Intent getEntryIntent(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra("user_info", user);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);

        setSupportActionBar(mToolBar);
        Intent intent = getIntent();
        mUser = (User)intent.getParcelableExtra("user_info");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.show(mUser.getId(), loadSecretKey());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user ,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                mPresenter.delete(mUser);
                break;
            case R.id.edit:
                mPresenter.edit(mUser);
                break;

        }
        return true;
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

    @Override
    public void onUserDelete() {
        finish();
    }

    @Override
    public void openEditActivity(User user) {
        Intent intent = EditActivity.getEntryIntent(this, user);
        startActivity(intent);
    }
}
