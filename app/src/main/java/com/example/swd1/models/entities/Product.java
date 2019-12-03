package com.example.swd1.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {

    @SerializedName("productId")
    @Expose
    private int productId;

    @SerializedName("proCode")
    @Expose
    private String proCode;

    @SerializedName("productName")
    @Expose
    private String productName;

    @SerializedName("proShortName")
    @Expose
    private String proShortName;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("picURL")
    @Expose
    private String picURL;

    @SerializedName("isAvailable")
    @Expose
    private boolean isAvailable;

    @SerializedName("discountPercent")
    @Expose
    private double discountPercent;

    @SerializedName("discountPrice")
    @Expose
    private double discountPrice;

    @SerializedName("att1")
    @Expose
    private String att1;

    @SerializedName("att2")
    @Expose
    private String att2;

    @SerializedName("att3")
    @Expose
    private String att3;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProShortName() {
        return proShortName;
    }

    public void setProShortName(String proShortName) {
        this.proShortName = proShortName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getAtt1() {
        return att1;
    }

    public void setAtt1(String att1) {
        this.att1 = att1;
    }

    public String getAtt2() {
        return att2;
    }

    public void setAtt2(String att2) {
        this.att2 = att2;
    }

    public String getAtt3() {
        return att3;
    }

    public void setAtt3(String att3) {
        this.att3 = att3;
    }
}
