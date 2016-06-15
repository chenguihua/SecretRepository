package com.secretrepository.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.secretrepository.app.login.LoginFragment;
import com.secretrepository.app.secret.PersonFragment;
import com.secretrepository.app.secret.SecretFragment;

public class SecretActivity extends AppCompatActivity implements
        LoginFragment.LoginInterface, SecretFragment.HostInterface {
    private static final String TAG = "SecretActivity";


    private static final boolean DEBUG = true;

    public static final String PREFERENCE_NAME = "account";

    private static final String TAG_LOGIN_FRAGMENT = "login";
    private static final String TAG_MAIN_FRAGMENT = "main";
    private static final String TAG_PERSON_FRAGMENT = "person";

    LoginFragment mLoginFragment;
    SecretFragment mMainFragment;
    PersonFragment mPersonFragment;

    private boolean isInPersonFragment = false;
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

        if (fragment instanceof PersonFragment) {
            mPersonFragment = (PersonFragment) fragment;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void enterSecretListView() {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.remove(mLoginFragment);

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
    public void enterPersonalView() {

        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(mMainFragment);

        PersonFragment fragment = (PersonFragment) getSupportFragmentManager().findFragmentByTag(TAG_PERSON_FRAGMENT);
        if (fragment == null) {
            transition.add(R.id.content, new PersonFragment(), TAG_PERSON_FRAGMENT);
        } else {
            transition.show(fragment);
        }
        transition.commit();

        isInPersonFragment = true;
    }

    public void exitPersonView() {
        isInPersonFragment = false;
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.hide(mPersonFragment);
        transition.show(mMainFragment);
        transition.commit();
    }

    @Override
    public void onBackPressed() {
        if (isInPersonFragment) {
            exitPersonView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onVerifyPass() {
        hasLogin = true;
        enterSecretListView();
    }

    public void showActionBar(boolean show) {
        if (show && !getSupportActionBar().isShowing()) {
            getSupportActionBar().show();
        } else if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
    }
}
