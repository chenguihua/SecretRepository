package com.secretrepository.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.secretrepository.app.login.LoginFragment;
import com.secretrepository.app.secret.SecretFragment;

public class SecretActivity extends AppCompatActivity implements LoginFragment.LoginInterface {
    private static final String TAG = "SecretActivity";
    private static final boolean DEBUG = true;

    private static final String TAG_LOGIN_FRAGMENT = "login";
    private static final String TAG_MAIN_FRAGMENT = "main";

    LoginFragment mLoginFragment;
    SecretFragment mMainFragment;

    private boolean hasLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showActionBar(false);
        getSupportFragmentManager().beginTransaction().add(R.id.content, new LoginFragment(), TAG_LOGIN_FRAGMENT).commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof LoginFragment) {
            mLoginFragment = (LoginFragment) fragment;
        }

        if (fragment instanceof SecretFragment) {
            mMainFragment = (SecretFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void enterSecretView() {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.remove(mLoginFragment);
        transition.add(R.id.content, new SecretFragment(), TAG_MAIN_FRAGMENT);

        SecretFragment fragment = (SecretFragment) getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT);
        if (fragment == null) {
            transition.add(R.id.content, new SecretFragment(), TAG_MAIN_FRAGMENT);
        } else {
            transition.show(fragment);
        }
        transition.commit();

        showActionBar(true);
    }

    @Override
    public void onVerifyPass() {
        hasLogin = true;
        enterSecretView();
    }

    public void showActionBar(boolean show) {
        if (show && !getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        } else if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
    }
}
