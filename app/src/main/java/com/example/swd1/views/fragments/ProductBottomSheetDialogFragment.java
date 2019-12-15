package com.example.swd1.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.swd1.R;
import com.example.swd1.models.database.CartItem;
import com.example.swd1.models.entities.Product;
import com.example.swd1.presenters.CartPresenter;
import com.example.swd1.presenters.CartPresenterListener;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.CartViewListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProductBottomSheetDialogFragment extends BottomSheetDialogFragment
        implements CartViewListener {

    private TextView txtName, txtPrice, txtTotal, txtQuantity;
    private TextInputEditText txtNote;
    private ImageView imgvProductImage;
    private Button btnAddToCart, btnPlus, btnMinus;
    private ImageButton btnDissmis;

    private CartPresenter presenter;

    private int currentQuantity = 1;

    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        presenter = new CartPresenter(this, getActivity());
        preferences = getActivity().getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE);


        Bundle bundle = getArguments();

        final Product product = (Product) bundle.getSerializable("PRODUCT");
        if (product != null) {
            txtName.setText(product.getProductName());
            txtPrice.setText(CommonConstant.currencyFormat(product.getPrice()));
            txtTotal.setText("Tổng : "+CommonConstant.currencyFormat(product.getPrice()));
        }
        Picasso.get().load("https://pizzatriangle.co.uk/Images/PZ0003.png")
                .into(imgvProductImage);

        btnDissmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuantity == 1) {
                    return;
                } else {
                    currentQuantity -= 1;
                    txtQuantity.setText(currentQuantity + "");
                    txtTotal.setText("Tổng : "+CommonConstant.currencyFormat(product.getPrice() * currentQuantity));
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentQuantity == 10) {
                    return;
                } else {
                    currentQuantity += 1;
                    txtQuantity.setText(currentQuantity + "");
                    txtTotal.setText((product.getPrice() * currentQuantity) + "");
                    txtTotal.setText("Tổng : "+CommonConstant.currencyFormat(product.getPrice() * currentQuantity));
                }
            }
        });


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartItem cartItem = new CartItem();
                cartItem.setTableId(preferences.getInt(CommonConstant.CURRENT_TABLE_ID, -1));
                cartItem.setProId(product.getProductId());
                int quantity = Integer.parseInt(txtQuantity.getText().toString());
                cartItem.setQuantity(quantity);
                cartItem.setProImage("https://pizzatriangle.co.uk/Images/PZ0003.png");
                cartItem.setProName(product.getProductName());
                cartItem.setProPrice(product.getPrice());
                cartItem.setProNote(txtNote.getText().toString());
                cartItem.setTotalPrice(product.getPrice() * quantity);

                cartItem.setServedStaff(preferences.getString(CommonConstant.STAFF_NAME, getActivity().getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE).getString(CommonConstant.STAFF_NAME, "anynomous")));

                presenter.insertCart(cartItem);
            }
        });

    }

    private void initView(@NonNull View view) {
        txtName = view.findViewById(R.id.txt_modal_product_name);
        txtPrice = view.findViewById(R.id.txt_modal_product_price);
        txtTotal = view.findViewById(R.id.txt_modal_product_price_total);
        txtQuantity = view.findViewById(R.id.txt_modal_product_quantity);
        txtNote = view.findViewById(R.id.txt_modal_note);
        btnDissmis = view.findViewById(R.id.btnDismiss);
        imgvProductImage = view.findViewById(R.id.imgv_modal_product_image);
        btnAddToCart = view.findViewById(R.id.btn_modal_add_to_cart);
        btnPlus = view.findViewById(R.id.btn_modal_plus);
        btnMinus = view.findViewById(R.id.btn_modal_minus);
    }

    @Override
    public void onInsertCartSuccess() {
        presenter.getAllCart();
        dismiss();
        Toast.makeText(this.getActivity(), getString(R.string.msg_insert_cart), Toast.LENGTH_SHORT).show();
    }
}
