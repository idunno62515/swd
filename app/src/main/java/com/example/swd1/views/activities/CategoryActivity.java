package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;
import com.example.swd1.presenters.CategoryPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.CategoryViewListener;
import com.example.swd1.views.adapters.CategoryAdapter;
import com.example.swd1.views.adapters.ProductLinearAdapter;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryViewListener, ProductLinearAdapter.OnCallback {

    private RecyclerView lvCate;
    private CategoryPresenter presenter;
    private CategoryAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        presenter = new CategoryPresenter(this);

        initView();

        Intent intent = getIntent();
        int masterCate = intent.getIntExtra(CommonConstant.MASTER_CATE_ID, -1);


        presenter.loadCategory(masterCate);
        
    }

    private void initView() {
        lvCate = findViewById(R.id.lv_cate);

        lvCate.setHasFixedSize(true);
        lvCate.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayCategory(List<Category> list) {
        adapter = new CategoryAdapter(list, this);
        lvCate.setAdapter(adapter);
    }

    @Override
    public void displayError() {

    }

    @Override
    public void onItemClick(int productId) {

    }
}
