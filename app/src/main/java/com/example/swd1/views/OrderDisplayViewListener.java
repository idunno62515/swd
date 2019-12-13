package com.example.swd1.views;

import com.example.swd1.models.entities.Order;

public interface OrderDisplayViewListener {
    void onGetListOrderSuccess(Order body);

    void onConnectFailed();

    void onRequestPaymentSuccess();
}
