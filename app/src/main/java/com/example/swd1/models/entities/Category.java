package com.example.swd1.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("cateCode")
    @Expose
    private Integer code;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("products")
    @Expose
    private List<Product> products;


    //    public Integer Type ;
    //
    //    public Integer DisplayOrder ;
    //
    //    public boolean IsDisplayed ;
    //
    //    public boolean IsUsed ;
    //
    //    public Integer IsExtra ;
    //
    //    public String AdjustmentNote ;
    //
    //    public String ImageFontAwsomeCss ;
    //
    //    public Integer ParentCateId ;
    //
    //    public Integer MasterCategory ;
    //
    //    public String ShortName ;

    public Category() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
