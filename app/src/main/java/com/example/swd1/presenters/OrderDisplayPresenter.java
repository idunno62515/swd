package com.example.swd1.presenters;

import com.example.swd1.models.OrderDisplayProvider;
import com.example.swd1.models.entities.Order;
import com.example.swd1.views.OrderDisplayViewListener;

public class OrderDisplayPresenter implements  OrderDisplayPresenterListener {

    private final OrderDisplayProvider provider;
    private OrderDisplayViewListener callback;

    public OrderDisplayPresenter(OrderDisplayViewListener callback) {
        this.callback = callback;
        this.provider = new OrderDisplayProvider(this);
    }

    public void loadListOrderByTable(int tableId) {
        provider.loadListOrderByTable(tableId);
    }

    @Override
    public void onGetListOrderSuccess(Order body) {
        callback.onGetListOrderSuccess(body);
    }

    @Override
    public void onConnectFailed() {
        callback.onConnectFailed();
    }

    public void requestPayment(int orderId) {
        provider.requestPayment(orderId);
    }
}
