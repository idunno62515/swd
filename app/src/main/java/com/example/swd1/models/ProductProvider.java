package com.example.swd1.models;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.models.services.ProductService;
import com.example.swd1.presenters.ProductListPresenter;
import com.example.swd1.presenters.ProductListPresenterListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductProvider {
    private Retrofit retrofitClient;
    private ProductListPresenterListener callback;

    public ProductProvider(ProductListPresenterListener callback) {
        this.callback = callback;
        retrofitClient = RetrofitClient.getClient();
    }

    public void getListProductByCate(int cate) {
        ProductService callApi = retrofitClient.create(ProductService.class);
        Call<List<Product>> call = callApi.getListProductByCate(cate);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    callback.onGetListProductSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callback.onGetListProductFailed();
            }
        });
    }
}
