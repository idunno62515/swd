package com.example.swd1.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.swd1.R;
import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    protected void createToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    protected void createSnackBar(int tableNumber, int numberCartItem) {
        String msg = "Bàn " + tableNumber + " : " + numberCartItem + " món chờ";
        if(numberCartItem > 0){
            Snackbar.make(getRootView(), msg, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Đặt món", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), ListCartActivity.class));
                        }
                    }).show();
        }else{
            Snackbar.make(getRootView(), msg, Snackbar.LENGTH_INDEFINITE)
                    .show();
        }

    }

    private View getRootView() {
        final ViewGroup contentViewGroup = (ViewGroup) findViewById(android.R.id.content);
        View rootView = null;

        if (contentViewGroup != null)
            rootView = contentViewGroup.getChildAt(0);

        if (rootView == null)
            rootView = getWindow().getDecorView().getRootView();

        return rootView;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
