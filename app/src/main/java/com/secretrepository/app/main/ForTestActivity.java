package com.secretrepository.app.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.secretrepository.app.R;
import com.secretrepository.app.database.SecretDatabaseHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenguihua on 2016/9/12.
 */
public class ForTestActivity extends Activity {
    @Bind(R.id.input_1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortest);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.input_1)
    public void click_1() {
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            String a = i + "";
            helper.insert(a, a, a, a);
        }
    }

}
