package com.example.swd1.models.services;

import com.example.swd1.models.entities.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderService {

    @POST("orders")
    Call<Boolean> SubmitOrder(@Body Order order);

}
