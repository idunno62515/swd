package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.models.entities.Product;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.presenters.MasterCategoryPresenter;
import com.example.swd1.utils.SpaceItemDecoration;
import com.example.swd1.views.MasterCategoryViewListener;
import com.example.swd1.views.adapters.MasterCategoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.annotations.NonNull;

public class MasterCategoryActivity extends BaseActivity implements MasterCategoryViewListener, MasterCategoryAdapter.OnCallback {


    private RecyclerView lvMasterCate;
    private MasterCategoryPresenter presenter;
    private MasterCategoryAdapter adapter;
    private SharedPreferences preferences;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_category);

        initView();

        createToolbar();

        presenter = new MasterCategoryPresenter(this, this);


        dialog.show();
        presenter.loadMasterCate();

        preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        createSnackBar(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        presenter.countItemCart(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_screen, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            startActivity(new Intent(getApplicationContext(), ProductListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {


        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build();


        if (Build.VERSION.SDK_INT >= 21) {
            this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
//
//        fabCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
//                        .setAction("Action", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                startActivity(new Intent(getApplicationContext(), ListCartActivity.class));
//                            }
//                        }).show();
//            }
//        });


        lvMasterCate = findViewById(R.id.lv_master_category);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter != null) {
                    switch (adapter.getItemViewType(position)) {
                        case CommonConstant.DEFAULT_COLUMN_COUNT:
                            return 1;
                        case CommonConstant.FULL_WIDTH_COLUMN:
                            return 2;
                        default:
                            return -1;
                    }
                } else {
                    return -1;
                }
            }
        });

        lvMasterCate.setLayoutManager(layoutManager);
        lvMasterCate.addItemDecoration(new SpaceItemDecoration(8));
    }

    @Override
    public void displayMasterCategory(List<MasterCategory> list) {
        dialog.dismiss();
        adapter = new MasterCategoryAdapter(list, this);
        lvMasterCate.setAdapter(adapter);

    }

    @Override
    public void displayError() {
        dialog.dismiss();
        Toast.makeText(this, R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cartNotExist(Integer aInteger) {
        createSnackBar(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1), 0);
    }

    @Override
    public void cartExist(Integer aInteger) {
        createSnackBar(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1), aInteger);
    }

    @Override
    public void onItemClick(int code) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(CommonConstant.MASTER_CATE_ID, code);
        startActivity(intent);
    }


}
