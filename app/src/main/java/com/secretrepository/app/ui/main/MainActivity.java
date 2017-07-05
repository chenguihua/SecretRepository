package com.secretrepository.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.Edit.EditActivity;
import com.secretrepository.app.ui.display.UserActivity;
import com.secretrepository.app.ui.setting.SettingsActivity;
import com.secretrepository.app.util.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {
    public final static String TAG = "MainActivity";

    @Inject
    MainPresenter<MainContract.View> mPresenter;

    UserAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static Intent getEntryActivity(Context context, String serial) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(AppConstants.APP_LOGIN_SERIAL, serial);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);
        mPresenter.onAttach(this);

        // set up the toolbar
        setSupportActionBar(mToolBar);
        initRecycleViewWithAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.load();
    }

    @OnClick(R.id.fab)
    void onFabButtonClick(View view) {
        mPresenter.onFabClick(view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                openSettingsActivity();
                break;
        }
        return true;
    }

    @Override
    public void openSettingsActivity() {
        Intent intent = SettingsActivity.getEntryIntent(this);
        startActivity(intent);
    }

    @Override
    public void openEditActivity() {
        Intent intent = EditActivity.getEntryIntent(this);
        startActivity(intent);
    }

    @Override
    public void openUserActivity(User user) {
        Intent intent = UserActivity.getEntryIntent(this, user, "");
        startActivity(intent);
    }

    @Override
    public void updateDisplayData(List<User> users) {
        mAdapter.setData(users);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    /**
     * Recycler View initialization.
     */
    void initRecycleViewWithAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter = new UserAdapter(this,
                new UserAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos, User user) {
                        mPresenter.showUserInformation(user);
                    }
                },
                new UserAdapter.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(View v, int pos, User user) {
                    }
                });
        mRecyclerView.setAdapter(mAdapter);
    }
}
