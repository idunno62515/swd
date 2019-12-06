package com.example.swd1.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Floor;
import com.example.swd1.models.entities.Table;

import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.ViewHolder> {


    private List<Floor> list;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private Fragment fragment;

    public FloorAdapter(List<Floor> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.floor_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Floor paren = list.get(position);
        holder.txtFloorName.setText("Tầng " + (paren.getFloor() == 0 ? "Trệt" : paren.getFloor()));
        List<Table> listTable = paren.getTables();
        TableAdapter tableAdapter = new TableAdapter(listTable, (TableAdapter.OnCallBack) this.fragment);
        holder.lvTable.setHasFixedSize(true);
        holder.lvTable.setLayoutManager(new GridLayoutManager(this.fragment.getActivity(), 3));
        holder.lvTable.setAdapter(tableAdapter);
        holder.lvTable.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtFloorName;
        RecyclerView lvTable;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFloorName = itemView.findViewById(R.id.txt_floorname);
            lvTable = itemView.findViewById(R.id.lv_table_child);
        }
    }


}
