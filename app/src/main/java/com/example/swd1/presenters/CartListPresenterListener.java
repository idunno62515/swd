package com.example.swd1.presenters;

import com.example.swd1.models.database.CartItem;

import java.util.List;

public interface CartListPresenterListener {
    void onInsertCartSuccess();

    void displayCartList(List<CartItem> cartItems);

    void onConnectFailed();

    void onSubmitOrderSuccess();
}
