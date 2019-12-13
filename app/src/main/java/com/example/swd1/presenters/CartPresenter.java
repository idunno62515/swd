package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.CartProvider;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.views.CartViewListener;

public class CartPresenter implements CartPresenterListener {

    private CartViewListener callBack;
    private CartProvider cartProvider;

    public CartPresenter(CartViewListener callBack, Context context) {
        this.callBack = callBack;
        this.cartProvider = new CartProvider(this, context);
    }

    public void insertCart(CartItem cartItem) {
        cartProvider.insert(cartItem);
    }

    @Override
    public void onInsertCartSuccess() {
        callBack.onInsertCartSuccess();
    }

    public void getAllCart() {
        cartProvider.getAllCart();
    }
}
