package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;
import com.example.swd1.presenters.CategoryPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.CategoryViewListener;
import com.example.swd1.views.adapters.CategoryAdapter;
import com.example.swd1.views.adapters.ProductLinearAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryViewListener, ProductLinearAdapter.OnCallback {

    private RecyclerView lvCate;
    private CategoryPresenter presenter;
    private CategoryAdapter adapter;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetDialogView;

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
        bottomSheetDialog = new BottomSheetDialog(CategoryActivity.this);
        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
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

    @Override
    public void onAddToCartClick(Product product) {
        TextView txtName = bottomSheetDialogView.findViewById(R.id.txt_modal_product_name);
        txtName.setText(product.getProductName());
        TextView txtPrice = bottomSheetDialogView.findViewById(R.id.txt_modal_product_price);
        txtPrice.setText(product.getPrice() + "");
        bottomSheetDialog.show();
    }
}
