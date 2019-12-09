package com.example.swd1.models.services;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.MasterCategory;
import com.example.swd1.models.entities.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface    CategoryService {
    @GET("categories?")
    Call<List<Category>> getListCategory(@Query("mastercate") int mastercate);

    @GET("master-categories")
    Call<List<MasterCategory>> getListMasterCategory();
}
