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

public class ProductVerticalAdapter extends RecyclerView.Adapter<ProductVerticalAdapter.ViewHolder> {

    private List<Product> list;
    private ProductVerticalAdapter.OnCallback listener;

    public ProductVerticalAdapter(List<Product> list, ProductVerticalAdapter.OnCallback listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_vertical, parent, false);
        return new ProductVerticalAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = list.get(position);

        holder.txtProductName.setText(product.getProductName());
        holder.txtProductPrice.setText(product.getPrice()+"");
        Picasso.get().load("https://pizzatriangle.co.uk/Images/PZ0003.png")
                .into(holder.imgvProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtProductPrice;
        ImageView imgvProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtProductPrice = itemView.findViewById(R.id.txt_product_price);
            imgvProduct = itemView.findViewById(R.id.imgv_product);
        }
    }
    public interface OnCallback {
        void onItemClick(Product product);
    }

}
