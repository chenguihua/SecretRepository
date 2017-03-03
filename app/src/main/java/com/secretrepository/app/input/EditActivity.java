package com.secretrepository.app.input;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.BaseActivity;
import com.secretrepository.app.data.LoginMsg;
import com.secretrepository.app.data.source.LoginMsgRepository;
import com.secretrepository.app.data.source.local.LoginMsgLocalDataSource;

import java.io.Serializable;

/**
 * Created by chenguihua on 2016/6/16.
 */
public class EditActivity extends BaseActivity {
    public static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }


    public void submit() {
        String title = getTextByViewId(R.id.login_title);
        String username = getTextByViewId(R.id.login_username);
        String password = getTextByViewId(R.id.login_password);
        String description = getTextByViewId(R.id.login_des);

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(title)) {
            alert();
        } else {
            LoginMsg msg = new LoginMsg(title, username, password, description);
            Intent intent = new Intent();
            intent.putExtra("LoginMsg", msg);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private String getTextByViewId(int resId) {
        EditText edit = (EditText) findViewById(resId);
        return edit.getText().toString();
    }

    private void alert() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("用户名、密码、标题不能为空")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();
        alertDialog.show();
    }
}
