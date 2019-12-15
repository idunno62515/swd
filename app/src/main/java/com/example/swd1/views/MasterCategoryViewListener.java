package com.example.swd1.views;

import com.example.swd1.models.entities.MasterCategory;

import java.util.List;

public interface MasterCategoryViewListener {
    void displayMasterCategory(List<MasterCategory> list);

    void displayError();

    void cartNotExist(Integer aInteger);

    void cartExist(Integer aInteger);
}
