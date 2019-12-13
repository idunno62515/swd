package com.example.swd1.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    @SerializedName("totalAmount")
    @Expose
    private double totalAmount;

    @SerializedName("tableId")
    @Expose
    private int tableId;

    @SerializedName("servedPerson")
    @Expose
    private String servedPerson;

    @SerializedName("details")
    @Expose
    private List<OrderDetail> details;


    public Order() {
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getServedPerson() {
        return servedPerson;
    }

    public void setServedPerson(String servedPerson) {
        this.servedPerson = servedPerson;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
}
