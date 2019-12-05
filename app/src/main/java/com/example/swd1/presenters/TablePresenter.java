package com.example.swd1.presenters;

import com.example.swd1.models.TableRepository;
import com.example.swd1.models.entities.Table;
import com.example.swd1.views.TableViewListener;

import java.util.List;

public class TablePresenter implements TablePresenterListener {

    private TableViewListener callBack;
    private TableRepository repository;

    public TablePresenter(TableViewListener tableViewListener) {
        this.callBack = tableViewListener;
        repository = new TableRepository(this);
    }

    public void loadTableList() {
        repository.getListTable();
    }

    @Override
    public void onGetListTableSuccess(List<Table> list) {
        callBack.displayTableList(list);
    }

    @Override
    public void onGetListTableFail() {
        callBack.displayError();
    }
}
