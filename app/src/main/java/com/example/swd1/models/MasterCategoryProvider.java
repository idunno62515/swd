package com.example.swd1.models;

import android.content.Context;
import android.os.AsyncTask;

import com.example.swd1.models.database.CartDao;
import com.example.swd1.models.database.CartDatabase;
import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.CategoryService;
import com.example.swd1.presenters.MasterCategoryPresenter;
import com.example.swd1.presenters.MasterCategoryPresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MasterCategoryProvider {
    private final CartDao cartDao;
    private CategoryService categoryService;
    private MasterCategoryPresenterListener callback;


    public MasterCategoryProvider(MasterCategoryPresenterListener callback, Context context) {

        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                .getString(CommonConstant.TOKEN, "");
        this.callback = callback;
        this.categoryService = RetrofitClient.getClient(token).create(CategoryService.class);
        CartDatabase database = CartDatabase.getInstance(context);
        cartDao = database.cartDao();
    }

    public void getListMasterCategory() {
        Call<List<MasterCategory>> call = categoryService.getListMasterCategory();
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

    public void countItemCart(int anInt) {
        new AsyncTask<Integer, Void, Integer>() {

            @Override
            protected Integer doInBackground(Integer... integers) {
                return cartDao.countItemCartInTable(integers[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {

                if (aInteger == 0) {
                    callback.cartNotExist(aInteger);
                } else {

                    callback.cartExist(aInteger);
                }


            }
        }.execute(anInt);
    }
}
