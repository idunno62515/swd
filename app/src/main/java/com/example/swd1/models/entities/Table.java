package com.example.swd1.models.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Table implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("Number")
    @Expose
    private String number;

    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Status")
    @Expose
    private Integer Status;

    @SerializedName("CurrentOrderId")
    @Expose
    private Integer  currentOrderId;

    @SerializedName("CurrentOrderDate")
    @Expose
    private String currentOrderDate;

    public Table() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(Integer currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public String getCurrentOrderDate() {
        return currentOrderDate;
    }

    public void setCurrentOrderDate(String currentOrderDate) {
        this.currentOrderDate = currentOrderDate;
    }

    @NonNull
    @Override
    public String toString() {
        return this.text;
    }
}
