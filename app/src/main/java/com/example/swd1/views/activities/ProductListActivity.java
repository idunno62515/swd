package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.Product;
import com.example.swd1.presenters.ProductListPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.ProductViewListener;
import com.example.swd1.views.adapters.ProductLinearAdapter;
import com.example.swd1.views.adapters.ProductVerticalAdapter;
import com.example.swd1.views.fragments.ProductBottomSheetDialogFragment;

import java.util.ArrayList;
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

        if (cate != 0) {
            dialog.show();
            presenter.loadProductListByCate(cate);
        } else {
            adapter = new ProductVerticalAdapter(new ArrayList<>(), this);
            lvProductList.setAdapter(adapter);
            Toast.makeText(this, "Nhập để tìm kiếm", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_screen, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập để tìm kiếm");
        searchView.setIconifiedByDefault(false);

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
               dialog.show();
               presenter.loadProductListBySearching(s);
               return true;
           }
       });

        return super.onCreateOptionsMenu(menu);
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
        if(list.isEmpty()){
            Toast.makeText(this, "Danh sách trống", Toast.LENGTH_SHORT).show();
        }
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
