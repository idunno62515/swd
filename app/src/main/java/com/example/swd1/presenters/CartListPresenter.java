package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.CartListRepository;
import com.example.swd1.models.CartRepository;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.views.CartListViewListener;
import com.example.swd1.views.CartViewListener;

import java.util.List;

public class CartListPresenter implements CartListPresenterListener{

    private CartListViewListener callBack;
    private CartListRepository cartListRepository;

    public CartListPresenter(CartListViewListener callBack, Context context) {
        this.callBack = callBack;
        this.cartListRepository = new CartListRepository(this, context);
    }


    @Override
    public void onInsertCartSuccess() {
        callBack.onInsertCartSuccess();
    }

    @Override
    public void displayCartList(List<CartItem> cartItems) {
        callBack.displayCartList(cartItems);
    }

    @Override
    public void onConnectFailed() {
        callBack.onConnecFailed();
    }

    @Override
    public void onSubmitOrderSuccess() {
        callBack.onSubmitOrderSuccess();
    }

    public void getAllCart() {
        cartListRepository.getAllCart();
    }

    public void getCartByTableId(int tableId) {
        cartListRepository.getCartByTableId(tableId);
    }

    public void submitOrder(List<CartItem> listCart) {
        cartListRepository.submitOrder(listCart);
    }
}
