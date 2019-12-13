package com.example.swd1.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.database.CartItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder>{

    private List<CartItem> list;
    private OnCallback listener;

    public CartListAdapter(List<CartItem> list, OnCallback listener) {

        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new CartListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = list.get(position);
        holder.txtName.setText(cartItem.getProName());
        holder.txtPrice.setText(cartItem.getProPrice()+"");
        holder.txtQuantity.setText("x"+cartItem.getQuantity()+"");
        Picasso.get().load("https://pizzatriangle.co.uk/Images/PZ0003.png")
                .into(holder.imgvImage);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtQuantity;
        ImageView imgvImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_cart_name);
            txtPrice = itemView.findViewById(R.id.txt_cart_price);
            txtQuantity = itemView.findViewById(R.id.txt_cart_quantity);
            imgvImage = itemView.findViewById(R.id.imgv_cart_image);

        }
    }

    public interface OnCallback{
        void onItemClick();
    }
}
