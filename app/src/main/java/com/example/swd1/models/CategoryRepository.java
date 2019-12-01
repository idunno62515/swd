package com.example.swd1.models;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Table;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.models.services.TableService;
import com.example.swd1.presenters.CategoryPresenterListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryRepository {

    private CategoryPresenterListener callBack;

    private Retrofit retrofitClient;

    public CategoryRepository(CategoryPresenterListener callBack) {
        this.callBack = callBack;
        this.retrofitClient = RetrofitClient.getClient();
    }

    public void getListCategory() {
        CategoryService callApi = retrofitClient.create(CategoryService.class);
        Call<List<Category>> call = callApi.getListCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    callBack.onGetListCateSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                callBack.onGetListCateFailed();
            }
        });
    }
}
