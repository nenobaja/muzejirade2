package com.attozoic.muzejirade.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.attozoic.muzejirade.R;


public class MainActivity extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("fatala","aktiviti se pali");
       if(savedInstanceState == null) {
           MainListFragment fragment = new MainListFragment();

           getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, fragment).commit();
       }
    }






}
