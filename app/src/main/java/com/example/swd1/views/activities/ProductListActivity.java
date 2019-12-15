package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.Product;
import com.example.swd1.presenters.ProductListPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.ProductViewListener;
import com.example.swd1.views.adapters.ProductLinearAdapter;
import com.example.swd1.views.adapters.ProductVerticalAdapter;
import com.example.swd1.views.fragments.ProductBottomSheetDialogFragment;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class ProductListActivity extends BaseActivity implements ProductViewListener, ProductVerticalAdapter.OnCallback {

    private RecyclerView lvProductList;
    private ProductListPresenter presenter;
    private ProductVerticalAdapter adapter;
    private ProductBottomSheetDialogFragment sheetDialogFragment;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        presenter = new ProductListPresenter(this, this);

        initView();

        Intent intent = getIntent();
        int cate = intent.getIntExtra(CommonConstant.CATE_ID, 0);

        dialog.show();
        presenter.loadProductListByCate(cate);

    }

    private void initView() {

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build();

        createToolbar();

        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        lvProductList = findViewById(R.id.lv_product_list);
        lvProductList.setHasFixedSize(true);
        lvProductList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayProduct(List<Product> list) {
        dialog.dismiss();
        adapter = new ProductVerticalAdapter(list, this);
        lvProductList.setAdapter(adapter);
    }

    @Override
    public void displayError() {
        dialog.dismiss();
        Toast.makeText(this, R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRODUCT", product);

        sheetDialogFragment = new ProductBottomSheetDialogFragment();
        sheetDialogFragment.setArguments(bundle);
        sheetDialogFragment.show(getSupportFragmentManager(), sheetDialogFragment.getTag());

    }
}
