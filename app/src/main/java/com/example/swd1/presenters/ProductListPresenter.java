package com.example.swd1.presenters;

import com.example.swd1.models.ProductRepository;
import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;
import com.example.swd1.views.ProductViewListener;
import com.example.swd1.views.adapters.ProductLinearAdapter;

import java.util.List;

public class ProductListPresenter implements ProductListPresenterListener {

    private ProductRepository repo;
    private ProductViewListener callback;

    public ProductListPresenter(ProductViewListener callback) {

        this.callback = callback;
        this.repo = new ProductRepository(this);
    }

    public void loadProductListByCate(int cateId) {
        repo.getListProductByCate(cateId);
    }

    public void loadProductListBySearching(String condition) {

    }

    @Override
    public void onGetListProductSuccess(List<Product> list) {
        callback.displayProduct(list);
    }

    @Override
    public void onGetListProductFailed() {
        callback.displayError();
    }
}
