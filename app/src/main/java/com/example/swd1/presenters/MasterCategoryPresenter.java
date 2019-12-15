package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.MasterCategoryProvider;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.views.MasterCategoryViewListener;

import java.util.List;

public class MasterCategoryPresenter implements MasterCategoryPresenterListener {


    private MasterCategoryProvider provider;
    private MasterCategoryViewListener callback;

    public MasterCategoryPresenter(MasterCategoryViewListener callback, Context context) {
        this.callback = callback;
        this.provider = new MasterCategoryProvider(this, context);
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

    @Override
    public void cartNotExist(Integer aInteger) {
        callback.cartNotExist(aInteger);
    }

    @Override
    public void cartExist(Integer aInteger) {
        callback.cartExist(aInteger);
    }

    public void countItemCart(int anInt) {
        provider.countItemCart(anInt);
    }
}
