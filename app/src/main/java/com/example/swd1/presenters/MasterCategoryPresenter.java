package com.example.swd1.presenters;

import com.example.swd1.models.MasterCategoryRepository;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.views.MasterCategoryViewListener;

import java.util.List;

public class MasterCategoryPresenter implements MasterCategoryPresenterListener {


    private MasterCategoryRepository repo;
    private MasterCategoryViewListener callback;

    public MasterCategoryPresenter(MasterCategoryViewListener callback) {
        this.callback = callback;
        this.repo = new MasterCategoryRepository(this);
    }

    public void loadMasterCate() {
        repo.getListMasterCategory();
    }

    @Override
    public void onGetListMasterCateSuccess(List<MasterCategory> list) {
        callback.displayMasterCategory();
    }

    @Override
    public void onGetListMasterCateFailed() {
        callback.displayError();
    }
}
