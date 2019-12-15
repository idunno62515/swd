package com.example.swd1.presenters;

import com.example.swd1.models.entities.MasterCategory;

import java.util.List;

public interface MasterCategoryPresenterListener {
    void onGetListMasterCateSuccess(List<MasterCategory> list);
    void onGetListMasterCateFailed();

    void cartNotExist(Integer aInteger);

    void cartExist(Integer aInteger);
}
