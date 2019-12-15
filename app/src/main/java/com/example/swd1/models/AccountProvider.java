package com.example.swd1.models;

import android.content.Context;

import com.example.swd1.models.entities.Login;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.AccountService;
import com.example.swd1.presenters.LoginPresenter;
import com.example.swd1.presenters.LoginPresenterListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountProvider {

    private  AccountService accountService;
    private LoginPresenterListener callback;

    public AccountProvider(LoginPresenterListener callback, Context context) {
        this.callback = callback;
        this.accountService = RetrofitClient.getClientForLogin().create(AccountService.class);
    }

    public void login(String username, String password) {
        Login login  = new Login();
        login.setUsername(username);
        login.setPassword(password);
        accountService.login(login).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    callback.onLoginSuccess(response.body());
                }else if(response.code() == 404){
                    callback.conLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callback.onConnectFailed();
            }
        });
    }
}
