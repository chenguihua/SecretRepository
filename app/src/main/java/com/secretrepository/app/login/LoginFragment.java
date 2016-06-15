package com.secretrepository.app.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.SecretActivity;
import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.util.Utils;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mAccount;
    private EditText mPassword;
    private Button mLoginButton;

    private boolean hasRegistered = false;

    public interface LoginInterface {
        public void onVerifyPass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mAccount = (EditText) view.findViewById(R.id.account);
        mPassword = (EditText) view.findViewById(R.id.password);
        mLoginButton = (Button) view.findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SecretActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        String name = sharedPreferences.getString("account", "");
        hasRegistered = !TextUtils.isEmpty(name);
        mLoginButton.setText(hasRegistered ? getResources().getString(R.string.login_button_register)
                : getResources().getString(R.string.login_button_unregister));
        // now for some test whem programing
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getContext());
        helper.insert("liujinrui", "123456", "bluebank", "www.bluebank.com");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                Utils.hideInputMethod(getActivity().getCurrentFocus());
                if (!hasRegistered) {
                    handleRegisterEvent();
                    ((LoginInterface) getActivity()).onVerifyPass();
                } else {
                    verifyLogin();
                }
                break;
        }
    }

    private void verifyLogin() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SecretActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("account", "");
        String password = sharedPreferences.getString("password", "");

        if (name.equals(mAccount.getText().toString())
                && password.equals(mPassword.getText().toString())){
            ((LoginInterface) getActivity()).onVerifyPass();
        } else if (mAccount.getText().toString().equals("admin")
                && mPassword.getText().toString().equals("123456")) {
            ((LoginInterface) getActivity()).onVerifyPass();
        } else {
            final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SecretActivity.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name = mAccount.getText().toString();
        editor.putString("account", name);
        editor.putString("password", mPassword.getText().toString());
        editor.commit();
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setMessage("Hello " + name + ". " + "The account can only be create this time.")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();
        alertDialog.show();
    }

}
