package com.example.swd1.views;

public interface LoginViewListener {
    void onConnectFailed();

    void onLoginSuccess(String token);

    void conLoginFailed();
}
