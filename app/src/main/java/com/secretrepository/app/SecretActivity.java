package com.secretrepository.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.secretrepository.app.secret.SecretFragment;

public class SecretActivity extends AppCompatActivity {
    private static final String TAG = "SecretActivity";
    private static final boolean DEBUG = true;

    private static final int LOGIN_REQUEST_CODE = 1;

    public static final String PREFERENCE_NAME = "account";
    private static final String TAG_MAIN_FRAGMENT = "main";

    SecretFragment mMainFragment;

    private boolean isUserLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content, new SecretFragment(), TAG_MAIN_FRAGMENT)
                .commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SecretFragment) {
            mMainFragment = (SecretFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isUserLogin) {
            Intent intent = new Intent(SecretActivity.this, SecretLoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOGIN_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    isUserLogin = true;
                }
                if (resultCode == RESULT_CANCELED) {
                    finish();
                }
                break;
        }
    }

}
