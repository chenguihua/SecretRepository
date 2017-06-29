package com.secretrepository.app.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.ui.base.BaseActivity;

import java.util.List;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class DetailActivity extends BaseActivity {
    private static final String TAG = "DetailActivity";

    List<CardView> mCardList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
//        LoginMsg msg = (LoginMsg)intent.getSerializableExtra("login");
//
//        setDetail(R.id.detail_username, msg.getUsername());
//        setDetail(R.id.detail_password, msg.getPassword());
    }

    public void setDetail(int resId, String text) {
        TextView view = (TextView) findViewById(resId);
        view.setText(text);
    }

}
