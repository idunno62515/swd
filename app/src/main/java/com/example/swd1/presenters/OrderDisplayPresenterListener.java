package com.example.swd1.presenters;

import com.example.swd1.models.entities.Order;

public interface OrderDisplayPresenterListener {
    void onGetListOrderSuccess(Order body);

    void onConnectFailed();

    void onRequestPaymentSuccess();
}
