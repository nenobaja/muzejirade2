package com.attozoic.muzejirade.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.presenter.PresenterMain;
import com.attozoic.muzejirade.presenter.PresenterMainImpl;
import com.attozoic.muzejirade.ui.posts.FragmentPosts;

import static com.attozoic.muzejirade.R.layout.activity_main;


public class MainActivity extends AppCompatActivity implements MainView {

    private NavigationView navView;
    DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    MenuItem mitem;
    MenuItem selectedMenuItem;

    private PresenterMain presenter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(activity_main);
        Log.d("fatala", "aktiviti se pali");

        presenter = new PresenterMainImpl(this);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setUpNavDrawer();

        if(savedInstanceState == null) {

            open(new FragmentPosts());

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private  void setUpNavDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.nvd_act_main);

        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(!item.isChecked()) {
                    mitem = item;
                    item.setChecked(true);
                    drawerLayout.closeDrawer(GravityCompat.START);
//                    presenter.onNavItem(item.getItemId());
                    return true;
                } else {
                    drawerLayout.closeDrawers();
                    return false;
                }
            }
        });

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close
        ) {

            public void onDrawerClosed(View drawer) {
                super.onDrawerClosed(drawer);

                if(mitem != null && selectedMenuItem == null) {
                    selectedMenuItem = mitem;
                    presenter.onNavItem(mitem.getItemId());
                }
                if(mitem != null && mitem != selectedMenuItem){
                    selectedMenuItem = mitem;
                    presenter.onNavItem(mitem.getItemId());
                }
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawer) {
                super.onDrawerOpened(drawer);
                invalidateOptionsMenu();
            }
        };
        //metod za animaciju!!
        drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void open(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay, fragment).commit();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay, fragment).commit();
//            }
//        }, 250);



    }
}