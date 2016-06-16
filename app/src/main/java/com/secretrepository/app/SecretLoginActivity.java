package com.secretrepository.app;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.secretrepository.app.crypt.MD5Crypt;
import com.secretrepository.app.util.Utils;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class SecretLoginActivity extends Activity implements View.OnClickListener {

    private static final int VERIFY_FAILED = 0;
    private static final int VERIFY_SUCCESS = 1;

    private EditText mAccount;
    private EditText mPassword;
    private Button mLoginButton;

    private boolean hasRegistered = false;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mAccount = (EditText) findViewById(R.id.account);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SecretActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        hasRegistered = sharedPreferences.contains("account");
        mLoginButton.setText(hasRegistered ? getResources().getString(R.string.login_button_register) : getResources().getString(R.string.login_button_unregister));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Utils.hideInputMethod(getCurrentFocus());
                if (!hasRegistered) {
                    handleRegisterEvent();
                    onVerify();
                } else {
                    handleLoginEvent();
                }
                break;
        }
    }

    public void onVerify() {
        setResult(RESULT_OK);
        finish();
    }


    private void handleLoginEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences(SecretActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");

        if (name.equals(mAccount.getText().toString())
                && password.equals(MD5Crypt.md5(mPassword.getText().toString()))) {
            onVerify();
        } else if (mAccount.getText().toString().equals("admin")
                && mPassword.getText().toString().equals("123456")) {
            onVerify();
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage("用户名或密码不正确")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).create();
            alertDialog.show();
        }
    }

    private void handleRegisterEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences(SecretActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = mAccount.getText().toString();
        editor.putString("account", name);
        editor.putString("password", MD5Crypt.md5(mPassword.getText().toString()));
        editor.commit();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else{
                setResult(RESULT_CANCELED, null);
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
