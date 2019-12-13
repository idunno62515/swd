package com.example.swd1.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;
import com.example.swd1.presenters.TablePresenter;
import com.example.swd1.views.TableViewListener;
import com.example.swd1.views.activities.MasterCategoryActivity;
import com.example.swd1.views.adapters.FloorAdapter;
import com.example.swd1.views.adapters.TableAdapter;

import java.util.List;

public class TableFragment extends Fragment implements TableViewListener, TableAdapter.OnCallBack {

    private RecyclerView lvFloor;
    private TablePresenter presenter;
//    private ExpandableListView lvTable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new TablePresenter(this);

        lvFloor = getActivity().findViewById(R.id.lv_floor);
        lvFloor.setHasFixedSize(true);
//        lvTable.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        lvFloor.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.loadTableList();

    }

    @Override
    public void displayTableList(List<Floor> list) {

        FloorAdapter floorAdapter = new FloorAdapter(list, this);
        lvFloor.setAdapter(floorAdapter);
//        TableAdapter tableAdapter = new TableAdapter(list, (TableAdapter.OnCallBack) getActivity());
//        lvTable.setAdapter(tableAdapter);
    }

    @Override
    public void displayError() {
        Toast.makeText(getActivity(), R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Table table) {
        Intent intent = new Intent();
        intent.putExtra(CommonConstant.TABLE_ID, table.getId());

        SharedPreferences preferences = getActivity().getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        preferences.edit().putInt(CommonConstant.CURRENT_TABLE_ID, table.getId()).commit();

        startActivity(new Intent(getActivity(), MasterCategoryActivity.class));
    }
}
