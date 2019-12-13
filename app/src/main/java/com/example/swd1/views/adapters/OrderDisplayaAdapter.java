package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.OrderDetail;

import java.util.List;

public class OrderDisplayaAdapter extends RecyclerView.Adapter<OrderDisplayaAdapter.ViewHolder> {

    private List<OrderDetail> list;
    private OnCallback listener;

    public OrderDisplayaAdapter(List<OrderDetail> list, OnCallback listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        return new OrderDisplayaAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail detail = list.get(position);

        holder.txtName.setText(detail.getProductName());
        holder.txtPrice.setText(detail.getUnitPrice()+"");
        holder.txtQuantity.setText(detail.getQuantity()+"");
        holder.txtStatus.setText(getStatus(detail.getStatus()));
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    private String getStatus(int statusInt) {
        switch (statusInt) {
            case 0: return "Chờ";
            case 1: return "Đang nấu";
            case 2: return "Xong";
            default:
                return "";
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice, txtQuantity, txtStatus;
        ImageButton btnCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_order_name);
            txtPrice = itemView.findViewById(R.id.txt_order_price);
            txtQuantity = itemView.findViewById(R.id.txt_order_quantity);
            txtStatus = itemView.findViewById(R.id.txt_order_status);
            btnCancel = itemView.findViewById(R.id.btn_order_cancel);
        }
    }

    public interface OnCallback{
        void onItemClick();
    }
}
