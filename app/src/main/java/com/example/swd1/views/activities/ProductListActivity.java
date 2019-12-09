package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.swd1.R;
import com.example.swd1.models.entities.Product;
import com.example.swd1.presenters.ProductListPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.ProductViewListener;
import com.example.swd1.views.adapters.ProductLinearAdapter;
import com.example.swd1.views.adapters.ProductVerticalAdapter;

import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductViewListener, ProductVerticalAdapter.OnCallback {

    private RecyclerView lvProductList;
    private ProductListPresenter presenter;
    private ProductVerticalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        presenter = new ProductListPresenter(this);
        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        initView();

        Intent intent = getIntent();
        int cate = intent.getIntExtra(CommonConstant.CATE_ID, 0);

        presenter.loadProductListByCate(cate);

    }

    private void initView() {
        lvProductList = findViewById(R.id.lv_product_list);

        lvProductList.setHasFixedSize(true);
        lvProductList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayProduct(List<Product> list) {
        adapter = new ProductVerticalAdapter(list, this);
        lvProductList.setAdapter(adapter);
    }

    @Override
    public void displayError() {

    }

    @Override
    public void onItemClick() {

    }
}
