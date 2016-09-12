package com.secretrepository.app.main.Input;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.main.BaseActivity;
import com.secretrepository.app.database.SecretDatabaseHelper;

/**
 * Created by chenguihua on 2016/6/16.
 */
public class EditActivity extends BaseActivity implements View.OnClickListener {
    private EditText mUserNameEdit;
    private EditText mPasswordEdit;
    private EditText mAddressEdit;
    private EditText mWebsiteEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        findViewById(R.id.submit).setOnClickListener(this);
        mUserNameEdit = (EditText) findViewById(R.id.username);
        mPasswordEdit = (EditText) findViewById(R.id.password);
        mAddressEdit = (EditText) findViewById(R.id.address);
        mWebsiteEdit = (EditText) findViewById(R.id.website);
    }

    public void submit() {
        String username = mUserNameEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();
        String address = mAddressEdit.getText().toString();
        String website = mWebsiteEdit.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(address)) {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("用户名、密码、门户不能为空")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).create();
            alertDialog.show();
        } else {
            SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getApplicationContext());
            helper.insert(username, password, address, website);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                submit();
                break;
        }
    }
}
