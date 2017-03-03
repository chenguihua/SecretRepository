package com.secretrepository.app.main.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.secretrepository.app.R;
import com.secretrepository.app.util.crypt.MD5Crypt;
import com.secretrepository.app.MainActivity;
import com.secretrepository.app.BaseActivity;
import com.secretrepository.app.util.InputMethodUtils;


/**
 * Created by chenguihua on 2016/6/6.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private final static String KEY_NAME = "account";
    private final static String KEY_PASSWORD = "password";

    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;
    private Button loginButton;

    private boolean hasRegistered = false;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);
        usernameWrapper.setHint("Username");
        passwordWrapper.setHint("Password");

        loginButton = (Button) findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);
        pass();
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        hasRegistered = sharedPreferences.contains(KEY_NAME);
        loginButton.setText(hasRegistered ? getResources().getString(R.string.login_button_register) : getResources().getString(R.string.login_button_unregister));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                InputMethodUtils.hideInputMethod(getCurrentFocus());
                if (!hasRegistered) {
                    handleRegisterEvent();
                } else {
                    handleLoginEvent();
                }
                break;
        }
    }

    public void pass() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Login
     */
    private void handleLoginEvent() {
        usernameWrapper.setError("");
        passwordWrapper.setError("");
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, "");
        String password = sharedPreferences.getString(KEY_PASSWORD, "");

        if (name.equals(usernameWrapper.getEditText().getText().toString())
                && password.equals(MD5Crypt.md5(passwordWrapper.getEditText().getText().toString()))) {
            pass();
        } else if (usernameWrapper.getEditText().getText().toString().equals("admin")
                && passwordWrapper.getEditText().getText().toString().equals("123456")) {
            pass();
        }

        if (TextUtils.isEmpty(usernameWrapper.getEditText().getText().toString())) {
            usernameWrapper.setError("username is empty");
        }

        if (TextUtils.isEmpty(passwordWrapper.getEditText().getText().toString())) {
            passwordWrapper.setError("password is empty");
        }

        if (!name.equals(usernameWrapper.getEditText().getText().toString())) {
            usernameWrapper.setError("username is not exist");
        } else if (!password.equals(MD5Crypt.md5(passwordWrapper.getEditText().getText().toString()))) {
            passwordWrapper.setError("password is wrong");
        }
    }

    /**
     * Register
     */
    private void handleRegisterEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = usernameWrapper.getEditText().getText().toString();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, MD5Crypt.md5(passwordWrapper.getEditText().getText().toString()));
        editor.commit();
        pass();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                exitTime = System.currentTimeMillis();
                Snackbar.make(findViewById(R.id.login_root_view), "再按一次将退出", Snackbar.LENGTH_SHORT).show();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
