package com.example.swd1.views.fragments;

import android.app.AlertDialog;
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
import com.example.swd1.models.remote.RetrofitConstants;
import com.example.swd1.utils.AppStatus;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;
import com.example.swd1.presenters.TablePresenter;
import com.example.swd1.views.TableViewListener;
import com.example.swd1.views.activities.MasterCategoryActivity;
import com.example.swd1.views.activities.OrderDisplayActivity;
import com.example.swd1.views.adapters.FloorAdapter;
import com.example.swd1.views.adapters.TableAdapter;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class TableFragment extends Fragment implements TableViewListener, TableAdapter.OnCallBack {

    private RecyclerView lvFloor;
    private TablePresenter presenter;
    private AlertDialog dialog;
    private HubConnection hubConnection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new TablePresenter(this, getActivity());

        hubConnection = HubConnectionBuilder.create(RetrofitConstants.HUB_URL).build();
        if (hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED) {
            hubConnection.start();
        }

        hubConnection.on("UpdateTable", (id)->{
            Toast.makeText(getActivity(), "Changed", Toast.LENGTH_SHORT).show();
        }, Integer.class);

        dialog = new SpotsDialog.Builder()
                .setContext(getActivity())
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build();

        lvFloor = getActivity().findViewById(R.id.lv_floor);
        lvFloor.setHasFixedSize(true);
//        lvTable.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        lvFloor.setLayoutManager(new LinearLayoutManager(getActivity()));
        dialog.show();
        presenter.loadTableList();

    }

    @Override
    public void displayTableList(List<Floor> list) {

        FloorAdapter floorAdapter = new FloorAdapter(list, this);
        lvFloor.setAdapter(floorAdapter);
        dialog.dismiss();
//        TableAdapter tableAdapter = new TableAdapter(list, (TableAdapter.OnCallBack) getActivity());
//        lvTable.setAdapter(tableAdapter);
    }

    @Override
    public void onConnecFailed() {
        Toast.makeText(getActivity(), R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void onItemClick(Table table) {

        SharedPreferences preferences = getActivity().getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        preferences.edit().putInt(CommonConstant.CURRENT_TABLE_ID, table.getId()).commit();

        if (table.getStatus() == AppStatus.TABLE_AVAILABLE) {
            startActivity(new Intent(getActivity(), MasterCategoryActivity.class));
        } else {
            startActivity(new Intent(getActivity(), OrderDisplayActivity.class));
        }


    }
}
