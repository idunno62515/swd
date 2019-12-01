package com.example.swd1.models.services;

import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Table;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    @GET("categories")
    Call<List<Category>> getListCategory();
}
