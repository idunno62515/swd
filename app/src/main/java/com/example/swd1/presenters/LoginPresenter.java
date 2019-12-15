package com.example.swd1.presenters;

import android.content.Context;
import android.text.Editable;

import com.example.swd1.models.AccountProvider;
import com.example.swd1.views.LoginViewListener;

public class LoginPresenter implements LoginPresenterListener {
    private LoginViewListener callback;

    private AccountProvider accountProvider;

    public LoginPresenter(LoginViewListener callback, Context context) {
        this.callback = callback;
        accountProvider = new AccountProvider(this, context);
    }


    public void login(String username, String password) {
        accountProvider.login(username, password);
    }


    @Override
    public void onConnectFailed() {
        callback.onConnectFailed();
    }

    @Override
    public void onLoginSuccess(String token) {
        callback.onLoginSuccess(token);
    }

    @Override
    public void conLoginFailed() {
        callback.conLoginFailed();

    }
}
