package com.example.swd1.models.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.*;
import java.util.Date;

public class Table implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("floor")
    @Expose
    private int floor;

    @SerializedName("currentOrderId")
    @Expose
    private Integer  currentOrderId;

    @SerializedName("currentOrderDate")
    @Expose
    private Date currentOrderDate;

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
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(Integer currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

//    public String getCurrentOrderDate() {
//        return currentOrderDate;
//    }
//
//    public void setCurrentOrderDate(String currentOrderDate) {
//        this.currentOrderDate = currentOrderDate;
//    }


    public Date getCurrentOrderDate() {
        return currentOrderDate;
    }

    public void setCurrentOrderDate(Date currentOrderDate) {
        this.currentOrderDate = currentOrderDate;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @NonNull
    @Override
    public String toString() {
        return this.text;
    }
}
