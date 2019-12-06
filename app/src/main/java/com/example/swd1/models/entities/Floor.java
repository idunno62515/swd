package com.example.swd1.models.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Floor implements Serializable {

    @SerializedName("floorNum")
    @Expose
    private int floor;

    @SerializedName("tables")
    @Expose
    private List<Table> tables;

    public Floor() {
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
