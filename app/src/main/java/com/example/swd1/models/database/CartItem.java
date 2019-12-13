package com.example.swd1.models.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CartItem", primaryKeys = {"tableId", "proId"})
public class CartItem {

//    @PrimaryKey(autoGenerate = true)
//    private int id;

    private int tableId;

    private int proId;

    private int quantity;

    private String proImage;

    private String proName;

    private double proPrice;

    private String proNote;

    private double totalPrice;

    private String servedStaff;

    public CartItem() {
    }



    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public String getProNote() {
        return proNote;
    }

    public void setProNote(String proNote) {
        this.proNote = proNote;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getServedStaff() {
        return servedStaff;
    }

    public void setServedStaff(String servedStaff) {
        this.servedStaff = servedStaff;
    }
}


