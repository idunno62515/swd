package com.example.swd1.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.swd1.R;
import com.example.swd1.models.entities.Category;
import com.example.swd1.models.entities.Table;
import com.example.swd1.views.adapters.CategoryAdapter;
import com.example.swd1.views.adapters.ProductLinearAdapter;
import com.example.swd1.views.adapters.TableAdapter;
import com.example.swd1.views.fragments.TableFragment;
import com.example.swd1.views.fragments.CategoryFragment;
import com.example.swd1.views.fragments.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements TableAdapter.OnCallBack {

    private Fragment homeFragment, menuFragment, userFragment;
    private FragmentTransaction fragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    showHomeFragment();
                    break;
                case R.id.nav_menu:
                    showMenuFragment();
                    break;
                case R.id.nav_user:
                    showUserFragment();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        showHomeFragment();

    }


    private void showMenuFragment() {
        if (menuFragment == null) {
            menuFragment = new CategoryFragment();
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, menuFragment);
        fragmentTransaction.commit();
    }

    private void showHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new TableFragment();
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
    }

    private void showUserFragment() {
        if (userFragment == null) {
            userFragment = new AccountFragment();
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, userFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(Table table) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new CategoryFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
