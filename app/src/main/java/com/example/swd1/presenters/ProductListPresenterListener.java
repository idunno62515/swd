package com.example.swd1.presenters;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;

import java.util.List;

public interface ProductListPresenterListener {
    void onGetListProductSuccess(List<Product> list);
    void onGetListProductFailed();
}
