package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.swd1.R;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.presenters.MasterCategoryPresenter;
import com.example.swd1.utils.SpaceItemDecoration;
import com.example.swd1.views.MasterCategoryViewListener;
import com.example.swd1.views.adapters.MasterCategoryAdapter;

public class MasterCategoryActivity extends AppCompatActivity implements MasterCategoryViewListener, MasterCategoryAdapter.OnCallback {


    private RecyclerView lvMasterCate;
    private MasterCategoryPresenter presenter;
    private MasterCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_category);

        presenter = new MasterCategoryPresenter(this);


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

        presenter.loadMasterCate();
    }

    @Override
    public void displayMasterCategory() {
    }

    @Override
    public void displayError() {

    }

    @Override
    public void onItemClick(int code) {

    }
}
