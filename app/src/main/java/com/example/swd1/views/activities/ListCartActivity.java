package com.example.swd1.views.activities;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.IntentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.presenters.CartListPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.CartListViewListener;
import com.example.swd1.views.adapters.CartListAdapter;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ListCartActivity extends BaseActivity implements
        CartListViewListener,
        CartListAdapter.OnCallback {

    private RecyclerView lvCartList;

    private Button btnCompleteOrder;

    private TextView txtCartTotal;

    private CartListAdapter adapter;
    private CartListPresenter presenter;
    private SharedPreferences preferences;

    private List<CartItem> listCart;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart);

        presenter = new CartListPresenter(this, this);
        initView();

        preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        dialog.show();
        presenter.getCartByTableId(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1));

        updateTotalPrice();

    }

    private void initView() {

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build();

        createToolbar();

        txtCartTotal = findViewById(R.id.txt_cart_total);


        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        lvCartList = findViewById(R.id.lv_cart_list);
        lvCartList.setHasFixedSize(true);
        lvCartList.setLayoutManager(new LinearLayoutManager(this));


        btnCompleteOrder = findViewById(R.id.btn_complete_order);

        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                presenter.submitOrder(listCart);
            }
        });
    }


    @Override
    public void onInsertCartSuccess() {

    }

    @Override
    public void displayCartList(List<CartItem> cartItems) {
        dialog.dismiss();
        listCart = cartItems.size() > 0 ? cartItems : new ArrayList<CartItem>();
        adapter = new CartListAdapter(cartItems, this);

        lvCartList.setAdapter(adapter);
    }

    @Override
    public void onConnecFailed() {
        dialog.dismiss();
        Toast.makeText(this, R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitOrderSuccess() {
        dialog.dismiss();
        Toast.makeText(this, R.string.complete_order, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void updateTotalPrice(Double aDouble) {
        txtCartTotal.setText("Tổng : "+ CommonConstant.currencyFormat(aDouble));
    }

    @Override
    public void finishCart() {
        Toast.makeText(this, "Không có món ăn được lưu!", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onItemClick() {

    }

    @Override
    public void onPlusCartClicked(int position, CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        listCart.set(position, cartItem);
        presenter.updateCart(cartItem);
        adapter.notifyItemChanged(position);
        updateTotalPrice();
    }

    @Override
    public void onMinusCartClicked(int position, CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() -1);
        listCart.set(position, cartItem);
        presenter.updateCart(cartItem);
        adapter.notifyItemChanged(position);
        updateTotalPrice();
    }

    @Override
    public void onDeleteCart(int position, CartItem cartItem) {
        listCart.remove(position);
        adapter.notifyItemRemoved(position);

        adapter.notifyItemRangeChanged(position, listCart.size());

        presenter.deleteCart(cartItem);

        updateTotalPrice();
    }

    private void updateTotalPrice(){
        presenter.updateTotalPrice(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1));
    }
}
