package com.example.swd1.models.services;

import com.example.swd1.models.entities.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {

    @POST("orders")
    Call<Boolean> SubmitOrder(@Body Order order);

    @GET("orders?")
    Call<Order> GetOrderInTable(@Query("tableId") int tableId);

    @PATCH("orders?")
    Call<Void> ChangeOrderStatus(@Query("id") int id);

}
