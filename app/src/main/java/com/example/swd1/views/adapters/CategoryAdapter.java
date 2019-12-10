package com.example.swd1.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> listCate;
    private Context context;

    public CategoryAdapter(List<Category> listCate, Context context) {
        this.listCate = listCate;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.ViewHolder holder, int position) {
        final Category cate = listCate.get(position);
        holder.txtCateName.setText(cate.getName());

        List<Product> listProduct = cate.getProducts();

        ProductLinearAdapter productLinearAdapter = new ProductLinearAdapter(
                listProduct,
                (ProductLinearAdapter.OnCallback) context
        );


        holder.lvProduct.setHasFixedSize(true);
        holder.lvProduct.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.lvProduct.setAdapter(productLinearAdapter);

        holder.lvProduct.setNestedScrollingEnabled(false);

        holder.btnMoreProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Button more : " +cate.getName() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCate != null ? listCate.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCateName;
        RecyclerView lvProduct;
        Button btnMoreProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCateName = itemView.findViewById(R.id.txt_cate_name);
            lvProduct = itemView.findViewById(R.id.lv_product_horizontal);
            btnMoreProduct = itemView.findViewById(R.id.btn_more_product);
        }
    }


}
