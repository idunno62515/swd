package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> listCate;
    private OnCallBack callBack;

    public CategoryAdapter(List<Category> listCate, OnCallBack callBack) {
        this.listCate = listCate;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        final Category cate = listCate.get(position);
        holder.txtCateName.setText(cate.getName());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onItemClick(cate);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCate != null ? listCate.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView txtCateName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.cate_item);
            txtCateName = itemView.findViewById(R.id.txt_cateName);
        }
    }


    public interface OnCallBack {
        void onItemClick(Category category);
    }

}
