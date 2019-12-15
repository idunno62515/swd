package com.example.swd1.presenters;

public interface LoginPresenterListener {
    void onConnectFailed();

    void onLoginSuccess(String token);

    void conLoginFailed();
}
