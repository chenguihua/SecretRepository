package com.secretrepository.app.ui.display;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class UserActivity extends BaseActivity implements UserContract.View{
    private static final String TAG = "UserActivity";

    @Inject
    UserContract.Presenter<UserContract.View> mPresenter;

    List<CardView> mCardList;

    public static Intent getEntryIntent(Context context, User user, String code) {
        Intent intent = new Intent(context, UserActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        Intent intent = getIntent();
    }

    public void setDetail(int resId, String text) {
        TextView view = (TextView) findViewById(resId);
        view.setText(text);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }
}
