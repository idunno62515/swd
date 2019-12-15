package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.CartListProvider;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.views.CartListViewListener;

import java.util.List;

public class CartListPresenter implements CartListPresenterListener {

    private CartListViewListener callBack;
    private CartListProvider cartListProvider;

    public CartListPresenter(CartListViewListener callBack, Context context) {
        this.callBack = callBack;
        this.cartListProvider = new CartListProvider(this, context);
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

    @Override
    public void updateTotalPrice(Double aDouble) {
        callBack.updateTotalPrice(aDouble);
    }

    @Override
    public void finishCart() {
        callBack.finishCart();
    }



    public void getAllCart() {
        cartListProvider.getAllCart();
    }

    public void getCartByTableId(int tableId) {
        cartListProvider.getCartByTableId(tableId);
    }

    public void submitOrder(List<CartItem> listCart) {
        cartListProvider.submitOrder(listCart);
    }

    public void updateCart(CartItem cartItem) {
        cartListProvider.update(cartItem);
    }

    public void deleteCart(CartItem cartItem) {
        cartListProvider.delelte(cartItem);
    }


    public void updateTotalPrice(int tableid) {
        cartListProvider.totalPriceInTable(tableid);
    }
}
