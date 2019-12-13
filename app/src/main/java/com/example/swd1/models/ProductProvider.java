package com.example.swd1.models;

import android.content.Context;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.models.services.ProductService;
import com.example.swd1.presenters.ProductListPresenter;
import com.example.swd1.presenters.ProductListPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductProvider {
    private ProductService productService;
    private ProductListPresenterListener callback;

    public ProductProvider(ProductListPresenterListener callback, Context context) {
        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                .getString(CommonConstant.TOKEN, "");
        this.callback = callback;
        this.productService = RetrofitClient.getClient(token).create(ProductService.class);;
    }

    public void getListProductByCate(int cate) {
        Call<List<Product>> call = productService.getListProductByCate(cate);
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
