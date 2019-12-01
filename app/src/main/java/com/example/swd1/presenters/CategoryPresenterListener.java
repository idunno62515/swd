package com.example.swd1.presenters;

import com.example.swd1.models.entities.Category;

import java.util.List;

public interface CategoryPresenterListener {
    void onGetListCateSuccess(List<Category> list);
    void onGetListCateFailed();
}
