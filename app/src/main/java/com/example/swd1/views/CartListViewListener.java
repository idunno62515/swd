package com.example.swd1.views;

import com.example.swd1.models.database.CartItem;

import java.util.List;

public interface CartListViewListener {

    void onInsertCartSuccess();

    void displayCartList(List<CartItem> cartItems);

    void onConnecFailed();

    void onSubmitOrderSuccess();

    void updateTotalPrice(Double aDouble);

    void finishCart();


}
