package com.example.swd1.presenters;

import com.example.swd1.models.CategoryRepository;
import com.example.swd1.models.entities.Category;
import com.example.swd1.views.CategoryViewListener;

import java.util.List;

public class CategoryPresenter implements CategoryPresenterListener{
    private CategoryViewListener callBack;
    private CategoryRepository repository;

    public CategoryPresenter(CategoryViewListener callBack) {
        this.callBack = callBack;
        this.repository = new CategoryRepository(this);
    }

    public void loadCategory() {
        repository.getListCategory();
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
