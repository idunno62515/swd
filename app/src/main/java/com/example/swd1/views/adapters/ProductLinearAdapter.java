package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductLinearAdapter extends RecyclerView.Adapter<ProductLinearAdapter.ViewHolder> {

    private List<Product> list;
    private OnCallback listener;

    public ProductLinearAdapter(List<Product> list, OnCallback listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductLinearAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Product product = list.get(position);

        holder.txtProductName.setText(product.getProductName());
        Picasso.get().load("https://classiarius.net/wp-content/uploads/2019/03/tokyo-a-1000-dollar-burger-for-a-royal-celebration.jpeg")
                .into(holder.imgvProduct);


        holder.layoutItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(product.getProductId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgvProduct;
        TextView txtProductName;
        View layoutItemProduct;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgvProduct = itemView.findViewById(R.id.imgv_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            layoutItemProduct = itemView.findViewById(R.id.layout_item_product);
        }
    }

    public interface OnCallback {
        void onItemClick(int productId);
    }

}
