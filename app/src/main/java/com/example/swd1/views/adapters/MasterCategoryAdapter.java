package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.models.entities.MasterCategory;

import java.util.List;

public class MasterCategoryAdapter extends RecyclerView.Adapter<MasterCategoryAdapter.ViewHolder>{

    private List<MasterCategory> list;
    private OnCallback listener;

    public MasterCategoryAdapter(List<MasterCategory> list, OnCallback listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_master_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MasterCategory masterCate = list.get(position);
        holder.txtMastercateName.setText(masterCate.getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(masterCate.getCode());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMastercateName;
        View item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMastercateName = itemView.findViewById(R.id.txt_mastercate_name);
            item = itemView.findViewById(R.id.item_mastercate);
        }
    }

    public interface  OnCallback{
        void onItemClick(int code);
    }

    @Override
    public int getItemViewType(int position) {
        if (list.size() == 1) {
            return CommonConstant.DEFAULT_COLUMN_COUNT;
        } else {
            if (list.size() % 2 == 0) {
                return CommonConstant.DEFAULT_COLUMN_COUNT;
            }else{
                return (position >1 && position == list.size()-1) ? CommonConstant.FULL_WIDTH_COLUMN : CommonConstant.DEFAULT_COLUMN_COUNT;
            }
        }
    }
}
