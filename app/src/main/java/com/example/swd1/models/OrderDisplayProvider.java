package com.example.swd1.models;

import android.content.Context;

import com.example.swd1.models.entities.Order;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.OrderService;
import com.example.swd1.presenters.OrderDisplayPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDisplayProvider {

    private OrderDisplayPresenterListener callback;
    private OrderService orderService;

    public OrderDisplayProvider(OrderDisplayPresenterListener callback, Context context) {
        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                .getString(CommonConstant.TOKEN, "");
        this.callback = callback;
        this.orderService = RetrofitClient.getClient(token).create(OrderService.class);
    }

    public void loadListOrderByTable(int tableId) {
        Call<Order> call = orderService.GetOrderInTable(tableId);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    callback.onGetListOrderSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                callback.onConnectFailed();
            }
        });
    }

    public void requestPayment(int orderId) {
        orderService.ChangeOrderStatus(orderId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if (response.body()) {
                        callback.onRequestPaymentSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onConnectFailed();
            }
        });

    }
}
