package com.example.swd1.presenters;

import com.example.swd1.models.MasterCategoryProvider;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.views.MasterCategoryViewListener;

import java.util.List;

public class MasterCategoryPresenter implements MasterCategoryPresenterListener {


    private MasterCategoryProvider provider;
    private MasterCategoryViewListener callback;

    public MasterCategoryPresenter(MasterCategoryViewListener callback) {
        this.callback = callback;
        this.provider = new MasterCategoryProvider(this);
    }

    public void loadMasterCate() {
        provider.getListMasterCategory();
    }

    @Override
    public void onGetListMasterCateSuccess(List<MasterCategory> list) {
        callback.displayMasterCategory(list);
    }

    @Override
    public void onGetListMasterCateFailed() {
        callback.displayError();
    }
}
