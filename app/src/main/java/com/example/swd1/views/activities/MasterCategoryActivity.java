package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.presenters.MasterCategoryPresenter;
import com.example.swd1.utils.SpaceItemDecoration;
import com.example.swd1.views.MasterCategoryViewListener;
import com.example.swd1.views.adapters.MasterCategoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MasterCategoryActivity extends BaseActivity implements MasterCategoryViewListener, MasterCategoryAdapter.OnCallback {


    private RecyclerView lvMasterCate;
    private MasterCategoryPresenter presenter;
    private MasterCategoryAdapter adapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_category);

        initView();

        presenter = new MasterCategoryPresenter(this);

        presenter.loadMasterCate();

        preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        createSnackBar(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        createSnackBar(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1), 0);
    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ban so 15");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
        adapter = new MasterCategoryAdapter(list, this);
        lvMasterCate.setAdapter(adapter);

    }

    @Override
    public void displayError() {
        Toast.makeText(this, R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int code) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra(CommonConstant.MASTER_CATE_ID, code);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
