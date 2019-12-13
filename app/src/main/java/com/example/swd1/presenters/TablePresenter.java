package com.example.swd1.presenters;

import com.example.swd1.models.TableProvider;
import com.example.swd1.models.entities.Floor;
import com.example.swd1.views.TableViewListener;

import java.util.List;

public class TablePresenter implements TablePresenterListener {

    private TableViewListener callBack;
    private TableProvider provider;

    public TablePresenter(TableViewListener tableViewListener) {
        this.callBack = tableViewListener;
        provider = new TableProvider(this);
    }

    public void loadTableList() {
        provider.getListTable();
    }

    @Override
    public void onGetListTableSuccess(List<Floor> list) {
        callBack.displayTableList(list);
    }

    @Override
    public void onGetListTableFail() {
        callBack.displayError();
    }
}
