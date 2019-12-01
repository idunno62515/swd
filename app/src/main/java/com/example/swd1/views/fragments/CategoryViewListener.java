package com.example.swd1.views.fragments;

import com.example.swd1.models.entities.Category;

import java.util.List;

public interface CategoryViewListener {
    void displayCategory(List<Category> list);

    void displayError();
}
