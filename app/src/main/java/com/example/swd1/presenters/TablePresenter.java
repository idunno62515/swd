package com.example.swd1.presenters;

import android.content.Context;

import com.example.swd1.models.TableProvider;
import com.example.swd1.models.entities.Floor;
import com.example.swd1.views.TableViewListener;

import java.util.List;

public class TablePresenter implements TablePresenterListener {

    private TableViewListener callBack;
    private TableProvider provider;

    public TablePresenter(TableViewListener tableViewListener, Context context) {
        this.callBack = tableViewListener;
        provider = new TableProvider(this, context);
    }

    public void loadTableList() {
        provider.getListTable();
    }

    @Override
    public void onGetListTableSuccess(List<Floor> list) {
        callBack.displayTableList(list);
    }

    @Override
    public void onConnecFailed() {
        callBack.onConnecFailed();
    }
}
