package com.attozoic.muzejirade.view;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.attozoic.muzejirade.R;


public class MainActivity extends AppCompatActivity {

        OnBackPressedListener onBackPressedListener;
    DrawerLayout drawerLayout;

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("fatala", "aktiviti se pali");

        drawerLayout = findViewById(R.id.nvd_act_main);

        if(savedInstanceState == null) {
            MainListFragment fragment = new MainListFragment();
            onBackPressedListener = fragment;
            getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, fragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if(onBackPressedListener != null) {
            if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                onBackPressedListener.OnBackPressed();
            } else {
                onBackPressedListener.OnBackPressed();
                super.onBackPressed();
            }
        }

    }
}