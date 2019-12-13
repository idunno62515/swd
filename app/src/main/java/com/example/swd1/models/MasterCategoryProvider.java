package com.example.swd1.models;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.presenters.MasterCategoryPresenter;
import com.example.swd1.presenters.MasterCategoryPresenterListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MasterCategoryProvider {
    private Retrofit retrofitClient;
    private MasterCategoryPresenterListener callback;


    public MasterCategoryProvider(MasterCategoryPresenterListener callback) {
        this.callback = callback;
        this.retrofitClient = RetrofitClient.getClient();
    }

    public void getListMasterCategory() {
        CategoryService callApi = retrofitClient.create(CategoryService.class);
        Call<List<MasterCategory>> call = callApi.getListMasterCategory();
        call.enqueue(new Callback<List<MasterCategory>>() {
            @Override
            public void onResponse(Call<List<MasterCategory>> call, Response<List<MasterCategory>> response) {
                if (response.isSuccessful()) {
                    callback.onGetListMasterCateSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MasterCategory>> call, Throwable t) {
                callback.onGetListMasterCateFailed();
            }
        });
    }
}
