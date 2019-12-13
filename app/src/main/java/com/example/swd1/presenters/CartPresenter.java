package com.example.swd1.presenters;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.swd1.models.CartRepository;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.views.CartViewListener;

public class CartPresenter implements CartPresenterListener {

    private CartViewListener callBack;
    private CartRepository cartRepository;

    public CartPresenter(CartViewListener callBack, Context context) {
        this.callBack = callBack;
        this.cartRepository = new CartRepository(this, context);
    }

    public void insertCart(CartItem cartItem) {
        cartRepository.insert(cartItem);
    }

    @Override
    public void onInsertCartSuccess() {
        callBack.onInsertCartSuccess();
    }

    public void getAllCart() {
        cartRepository.getAllCart();
    }
}
