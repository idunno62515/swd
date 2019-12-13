package com.example.swd1.models;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.swd1.models.database.CartDao;
import com.example.swd1.models.database.CartDatabase;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.presenters.CartPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

public class CartRepository {


    private CartPresenterListener callBack;

    private CartDao cartDao;
    private List<CartItem> listCart;
    private Context context;

    public CartRepository(CartPresenterListener callBack, @NonNull Context context) {
        this.callBack = callBack;
        this.context = context;

        CartDatabase database = CartDatabase.getInstance(context);
        cartDao = database.cartDao();

    }

    public void insert(CartItem cartItem) {
        new InserCartAsync(cartDao).execute(cartItem);
        callBack.onInsertCartSuccess();
    }

    public void update(CartItem cartItem) {

    }

    public void delelte(CartItem cartItem) {

    }

    public List<CartItem> getAllCart() {
        new AsyncTask<Void, Void, List<CartItem>>() {
            @Override
            protected List<CartItem> doInBackground(Void... voids) {
                return cartDao.getAllCart();
            }

            @Override
            protected void onPostExecute(List<CartItem> cartItems) {
                listCart = cartItems;
            }
        }.execute();

        return listCart;
    }

    private static class InserCartAsync extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public InserCartAsync(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {
            cartDao.insertOrReplaceAll(cartItems[0]);
            return null;
        }

    }


}
