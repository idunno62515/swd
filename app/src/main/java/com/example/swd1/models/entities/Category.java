package com.example.swd1.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id ;

    @SerializedName("Code")
    @Expose
    private Integer code ;

    @SerializedName("Name")
    @Expose
    private String name ;

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

}
