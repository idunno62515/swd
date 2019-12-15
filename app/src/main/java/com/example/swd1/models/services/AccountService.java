package com.example.swd1.models.services;

import com.example.swd1.models.entities.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountService {

    @POST("accounts/login")
    Call<String> login(@Body Login login);
}
