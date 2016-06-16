package com.secretrepository.app.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.database.SecretDatabaseHelper.UserBean;

import java.util.List;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class SecretSingleActivity extends Activity {
    private static final String TAG = "tag";
    TextView mUsernameTextView;
    TextView mPasswordTextView;
    TextView mAddressTextView;
    TextView mWebsiteTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_fragment);
        mUsernameTextView = (TextView)findViewById(R.id.single_username);
        mPasswordTextView = (TextView)findViewById(R.id.single_password);
        mAddressTextView = (TextView)findViewById(R.id.single_address);
        mWebsiteTextView = (TextView)findViewById(R.id.single_website);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String address = intent.getStringExtra("address");
        String website = intent.getStringExtra("website");
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getApplicationContext());
        if (id == 0) {
            Log.e(TAG, "address id not found");
            return;
        }
        List<UserBean> beans = helper.userFindByAddressId(id);
        mUsernameTextView.setText(beans.get(0).username);
        mPasswordTextView.setText(beans.get(0).password);
        mAddressTextView.setText(address);
        mWebsiteTextView.setText(website);
    }
}
