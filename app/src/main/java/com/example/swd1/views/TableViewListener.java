package com.example.swd1.views;

import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;

import java.util.List;

public interface TableViewListener {

    void displayTableList(List<Floor> list);

    void onConnecFailed();
}
