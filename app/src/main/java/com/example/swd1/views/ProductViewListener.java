package com.example.swd1.views;

import com.example.swd1.models.entities.Product;

import java.util.List;

public interface ProductViewListener {
    void displayProduct(List<Product> list);
    void displayError();

}
