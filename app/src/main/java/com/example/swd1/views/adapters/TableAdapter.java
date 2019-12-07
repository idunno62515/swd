package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Table;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<Table> listTable;
    private OnCallBack callBack;

    public TableAdapter(List<Table> listTable, OnCallBack listener) {
        this.listTable = listTable;
        this.callBack = listener;
    }

    @NonNull
    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.ViewHolder holder, int position) {
        final Table table = listTable.get(position);
        holder.txtTableName.setText(table.getText() + "\n" + (table.getStatus() ==1 ? "Có khách":"Trống"));
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onItemClick(table);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTable != null ? listTable.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTableName;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTableName = itemView.findViewById(R.id.txt_table_name);
            item = itemView.findViewById(R.id.table_item);
        }
    }

    public interface OnCallBack {
        void onItemClick(Table table);
    }

}
