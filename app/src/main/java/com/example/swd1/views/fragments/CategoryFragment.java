package com.example.swd1.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;
import com.example.swd1.presenters.CategoryPresenter;
import com.example.swd1.views.CategoryViewListener;
import com.example.swd1.views.adapters.CategoryAdapter;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryViewListener {

    private RecyclerView lvCate;
    private CategoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new CategoryPresenter(this);

        lvCate = getActivity().findViewById(R.id.lvCategory);
        lvCate.setHasFixedSize(true);
        lvCate.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter.loadCategory();

    }

    @Override
    public void displayCategory(List<Category> list) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(list, (CategoryAdapter.OnCallBack) getActivity());
        lvCate.setAdapter(categoryAdapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(getActivity(), R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }
}
