package com.example.swd1.models.services;

import com.example.swd1.models.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductService {

    @GET("products?")
    Call<List<Product>> getListProductByCate(@Query("cate") int cate);
    @GET("products?")
    Call<List<Product>> getListProductBySearching(@Query("condition") String condition);

}
