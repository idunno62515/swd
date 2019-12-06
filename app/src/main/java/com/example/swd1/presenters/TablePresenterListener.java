package com.example.swd1.presenters;

import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;

import java.util.List;

public interface TablePresenterListener {
    void onGetListTableSuccess(List<Floor> list);
    void onGetListTableFail();
}
