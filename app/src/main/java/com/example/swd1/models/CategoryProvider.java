package com.example.swd1.models;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Table;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.models.services.TableService;
import com.example.swd1.presenters.CategoryPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryProvider {

    private CategoryPresenterListener callBack;

    private CategoryService categoryService;

    public CategoryProvider(CategoryPresenterListener callBack, Context context) {

        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                .getString(CommonConstant.TOKEN, "");
        this.callBack = callBack;

        this.categoryService = RetrofitClient.getClient(token).create(CategoryService.class);
    }

    public void getListCategory(int mastecate) {
        Call<List<Category>> call = categoryService.getListCategory(mastecate);
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
