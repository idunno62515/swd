package com.example.swd1.models;

import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.TableService;
import com.example.swd1.presenters.TablePresenterListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableProvider {
    private Retrofit retrofitClient;
    private TablePresenterListener callBack;

    public TableProvider(TablePresenterListener presenter) {
        this.retrofitClient = RetrofitClient.getClient();
        callBack = presenter;
    }

    public void getListTable() {
        TableService callApi = retrofitClient.create(TableService.class);
        Call<List<Floor>> call = callApi.getListFloorAndTable();
        call.enqueue(new Callback<List<Floor>>() {
            @Override
            public void onResponse(Call<List<Floor>> call, Response<List<Floor>> response) {
                if (response.isSuccessful()) {
                    callBack.onGetListTableSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Floor>> call, Throwable t) {
                callBack.onGetListTableFail();
            }
        });
    }


}
