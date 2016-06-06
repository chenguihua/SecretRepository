package com.secretrepository.app.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.secretrepository.app.R;
import com.secretrepository.app.util.Utils;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mAccount;
    private EditText mPassword;
    private Button mLoginButton;

    public interface LoginInterface {
        public void onVerifyPass();
        //public void onVerifyFail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mAccount = (EditText) view.findViewById(R.id.account);
        mPassword = (EditText) view.findViewById(R.id.password);
        mLoginButton = (Button) view.findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Utils.hideInputMethod(getActivity().getCurrentFocus());
                verifyLogin();
                break;
        }
    }

    private void verifyLogin() {
        if (mAccount.getText().toString().equals("admin")
                && mPassword.getText().toString().equals("123456")) {
            ((LoginInterface)getActivity()).onVerifyPass();
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

}
