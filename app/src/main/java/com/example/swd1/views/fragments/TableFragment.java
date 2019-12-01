package com.example.swd1.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Table;
import com.example.swd1.presenters.TablePresenter;
import com.example.swd1.views.adapters.TableAdapter;

import java.util.List;

public class TableFragment extends Fragment implements TableViewListener {

    private RecyclerView lvTable;
    private TablePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new TablePresenter(this);

        lvTable = getActivity().findViewById(R.id.lvTable);
        lvTable.setHasFixedSize(true);
        lvTable.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        presenter.loadTableList();

    }

    @Override
    public void displayTableList(List<Table> list) {
        TableAdapter tableAdapter = new TableAdapter(list, (TableAdapter.OnCallBack) getActivity());
        lvTable.setAdapter(tableAdapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(getActivity(), R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }
}
