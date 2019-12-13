package com.example.swd1.models;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.swd1.models.database.CartDao;
import com.example.swd1.models.database.CartDatabase;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.models.entities.Order;
import com.example.swd1.models.entities.OrderDetail;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.OrderService;
import com.example.swd1.presenters.CartListPresenterListener;
import com.example.swd1.presenters.CartPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListProvider {


    private CartListPresenterListener callBack;

    private CartDao cartDao;
    private List<CartItem> listCart;
    private OrderService orderService;

    public CartListProvider(CartListPresenterListener callBack, @NonNull Context context) {
        this.callBack = callBack;

        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                .getString(CommonConstant.TOKEN, "");

        CartDatabase database = CartDatabase.getInstance(context);
        cartDao = database.cartDao();
        orderService = RetrofitClient.getClient(token).create(OrderService.class);


    }

    public void insert(CartItem cartItem) {
        new InserCartAsync(cartDao).execute(cartItem);
        callBack.onInsertCartSuccess();
    }

    public void update(CartItem cartItem) {

    }

    public void delelte(CartItem cartItem) {

    }

    public void getCartByTableId(int tableId) {
        new getCartByTableIdAsync(cartDao).execute(tableId);
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

    public void submitOrder(final List<CartItem> listCart) {

        Order order = new Order();
        order.setDetails(new ArrayList<OrderDetail>() {
        });
        order.setTableId(listCart.get(0).getTableId());
        order.setServedPerson(listCart.get(0).getServedStaff());
        OrderDetail detail = null;
        double totalPrice = 0;

        for (CartItem item : listCart) {
            totalPrice += item.getTotalPrice();

            detail = new OrderDetail();
            detail.setNotes(item.getProNote());
            detail.setProductCode("some code"); //TODO: code hardcode
            detail.setProductId(item.getProId());
            detail.setProductName(item.getProName());
            detail.setQuantity(item.getQuantity());
            detail.setTotalAmount(item.getTotalPrice());
            detail.setUnitPrice(item.getProPrice());
            order.getDetails().add(detail);

        }

        order.setTotalAmount(totalPrice);

        Call<Boolean> call = orderService.SubmitOrder(order);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    //delete trong sqlite
                    new DeleteAllCartByTableIdAsync(cartDao).execute(listCart.get(0).getTableId());
                    callBack.onSubmitOrderSuccess();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callBack.onConnectFailed();
            }
        });
    }


    private class InserCartAsync extends AsyncTask<CartItem, Void, Void> {
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

    private class DeleteAllCartByTableIdAsync extends AsyncTask<Integer, Void, Void> {

        private CartDao cartDao;

        public DeleteAllCartByTableIdAsync(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            cartDao.clearCartInTable(integers[0]);
            return null;
        }
    }


    private class getCartByTableIdAsync extends AsyncTask<Integer, Void, List<CartItem>> {

        private CartDao cartDao;

        public getCartByTableIdAsync(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected List<CartItem> doInBackground(Integer... integers) {
            return cartDao.getCartsByTableId(integers[0]);
        }

        @Override
        protected void onPostExecute(List<CartItem> cartItems) {
            callBack.displayCartList(cartItems);
        }
    }


}
