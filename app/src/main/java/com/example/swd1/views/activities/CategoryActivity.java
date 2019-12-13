package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
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
import com.example.swd1.views.fragments.ProductBottomSheetDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CategoryViewListener, ProductLinearAdapter.OnCallback {

    private RecyclerView lvCate;
    private CategoryPresenter presenter;
    private CategoryAdapter adapter;
    private ProductBottomSheetDialogFragment sheetDialogFragment;
//    private BottomSheetDialog bottomSheetDialog;
//    private View bottomSheetDialogView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        initView();

        presenter = new CategoryPresenter(this);

        Intent intent = getIntent();
        int masterCate = intent.getIntExtra(CommonConstant.MASTER_CATE_ID, -1);

        presenter.loadCategory(masterCate);

    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

//        bottomSheetDialog = new BottomSheetDialog(CategoryActivity.this);
//        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
//        bottomSheetDialog.setContentView(bottomSheetDialogView);




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
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRODUCT",product);

        sheetDialogFragment = new ProductBottomSheetDialogFragment();
        sheetDialogFragment.setArguments(bundle);
        sheetDialogFragment.show(getSupportFragmentManager(), sheetDialogFragment.getTag());

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}
