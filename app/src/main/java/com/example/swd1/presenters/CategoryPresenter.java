package com.example.swd1.presenters;

import com.example.swd1.models.CategoryProvider;
import com.example.swd1.models.entities.Category;
import com.example.swd1.views.CategoryViewListener;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterListener{
    private CategoryViewListener callBack;
    private CategoryProvider provider;

    public CategoryPresenter(CategoryViewListener callBack) {
        this.callBack = callBack;
        this.provider = new CategoryProvider(this);
    }

    public void loadCategory(int masteCate) {
        provider.getListCategory(masteCate);
    }

    @Override
    public void onGetListCateSuccess(List<Category> list) {
        callBack.displayCategory(list);
    }

    @Override
    public void onGetListCateFailed() {
        callBack.displayError();
    }
}
