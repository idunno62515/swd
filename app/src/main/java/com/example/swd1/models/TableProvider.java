package com.example.swd1.models;

import android.content.Context;

import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;
import com.example.swd1.models.remote.RetrofitClient;
import com.example.swd1.models.services.TableService;
import com.example.swd1.presenters.TablePresenterListener;
import com.example.swd1.utils.CommonConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TableProvider {
    private TableService tableService;
    private TablePresenterListener callBack;


    public TableProvider(TablePresenterListener presenter, Context context) {
        String token = context.getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE)
                        .getString(CommonConstant.TOKEN, "");
        this.tableService = RetrofitClient.getClient("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdHJpbmciLCJqdGkiOiI4NTY4MTMzZS1hMTFiLTQwOTctOWUyYy03MDg5YTkyZjIzNWEiLCJlbWFpbCI6InN0cmluZyIsIm5iZiI6MTU3NjI3ODY3MSwiZXhwIjoxNTc2NTM3ODcxLCJpYXQiOjE1NzYyNzg2NzF9.-asELUe3j8DXHNTOe6QqwpcE3R0zDT2GZDnZ5VdwiHw").create(TableService.class);
        callBack = presenter;
    }

    public void getListTable() {
        Call<List<Floor>> call = tableService.getListFloorAndTable();
        call.enqueue(new Callback<List<Floor>>() {
            @Override
            public void onResponse(Call<List<Floor>> call, Response<List<Floor>> response) {
                if (response.isSuccessful()) {
                    callBack.onGetListTableSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Floor>> call, Throwable t) {
                callBack.onConnecFailed();
            }
        });
    }


}
