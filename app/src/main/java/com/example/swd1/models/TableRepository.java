package com.example.swd1.models;

import com.example.swd1.models.entities.Table;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.TableService;
import com.example.swd1.presenters.TablePresenterListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableRepository {
    private Retrofit retrofitClient;
    private TablePresenterListener callBack;

    public TableRepository(TablePresenterListener presenter) {
        this.retrofitClient = RetrofitClient.getClient();
        callBack = presenter;
    }

    public void getListTable() {
        TableService callApi = retrofitClient.create(TableService.class);
        Call<List<Table>> call = callApi.getListTable();
        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                if (response.isSuccessful()) {
                    callBack.onGetListTableSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {
                callBack.onGetListTableFail();
            }
        });
    }


}
