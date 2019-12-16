package com.example.swd1.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.presenters.LoginPresenter;
import com.example.swd1.utils.CommonConstant;
import com.example.swd1.views.LoginViewListener;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements LoginViewListener {


    private EditText txtUsername, txtPassword;

    private Button btnLogin;

    private LoginPresenter loginPresenter;

    private SharedPreferences preferences;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        preferences = getSharedPreferences(CommonConstant.APP_SHARE_PREFERENCE, MODE_PRIVATE);

        loginPresenter = new LoginPresenter(this, this);

        String username = preferences.getString(CommonConstant.STAFF_NAME, "");


        if(username!=null && !username.isEmpty()){
            dialog.show();
            startActivity(new Intent(this, HomeScreenActivity.class));
            finish();
            dialog.dismiss();
        }

    }

    private void initView() {
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage(R.string.waiting)
                .setCancelable(false)
                .build();

        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_passwod);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                loginPresenter.login(txtUsername.getText().toString(), txtPassword.getText().toString());
            }
        });
    }

    @Override
    public void onConnectFailed() {
        dialog.dismiss();
        Toast.makeText(this, R.string.connect_to_server_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(String token) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CommonConstant.TOKEN, token);
        editor.putString(CommonConstant.STAFF_NAME, txtUsername.getText().toString());
        editor.commit();
        dialog.dismiss();
        txtUsername.setText(txtUsername.getText().toString());
        startActivity(new Intent(this, HomeScreenActivity.class));
        finish();

    }

    @Override
    public void conLoginFailed() {
        dialog.dismiss();
        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
    }
}
