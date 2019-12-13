package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.CategoryProvider;
import com.example.swd1.models.entities.Category;
import com.example.swd1.views.CategoryViewListener;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterListener{
    private CategoryViewListener callBack;
    private CategoryProvider provider;

    public CategoryPresenter(CategoryViewListener callBack, Context context) {
        this.callBack = callBack;
        this.provider = new CategoryProvider(this, context);
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
