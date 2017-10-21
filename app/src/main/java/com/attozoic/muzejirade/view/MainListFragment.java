package com.attozoic.muzejirade.view;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.PostServiceFireBase;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.presenter.MainPresenter;
import com.attozoic.muzejirade.presenter.MainPresenterImpl;
import com.attozoic.muzejirade.utils.OnRecyclerItemClickListener;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nenadicivan on 9/28/2017.
 */

public class MainListFragment extends Fragment implements MainView,OnBackPressedListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyAdapter adapter;
    ProgressDialog progressDialog;

    private DrawerLayout navDrawer;
    private NavigationView navView;
    MenuItem Mitem;
    private Toolbar toolbar;

    private ActionBarDrawerToggle toggle;

    private boolean isLoading;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;

    private MainPresenter presenter;

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();
        toggle.syncState();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_main_fragment, container, false);

        Log.d("fatala","fragment se pali");


        presenter = new MainPresenterImpl(this, new PostServiceFireBase());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onResume(savedInstanceState, true, null);

            }
        });
        adapter = new MyAdapter(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(iListItem item) {
                presenter.onItemClicked(item);
            }
        });
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fatala","aktiviti se kreated");

        toolbar = (Toolbar) getActivity().findViewById(R.id.app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        progressDialog = new ProgressDialog(getActivity());
        presenter.onResume(savedInstanceState, false, null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {

                    Toast.makeText(getActivity(), "radiiiii " + totalItemCount + " po" + lastVisibleItem, Toast.LENGTH_LONG).show();
                    Post post  = (Post) adapter.getListItems().get(totalItemCount-1);

                    presenter.onResume(savedInstanceState, false, post.getCreatedAt());

                    isLoading = true;
                }
            }
        });

        setUpNavDrawer();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("items", Parcels.wrap(adapter.getListItems()));
       // outState.putSerializable("items", (Serializable) adapter.getListItems());
        super.onSaveInstanceState(outState);

    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
        isLoading = false;
    }

    @Override
    public void setItems(List<iListItem> items) {
        adapter.updateListItems(items);

    }

    @Override
    public void setMoreItems(List<iListItem> items) {
        adapter.loadMoreListItems(items);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private  void setUpNavDrawer(){
        navDrawer = (DrawerLayout) getActivity().findViewById(R.id.nvd_act_main);

        navView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(!item.isChecked()) {
                    Mitem = item;
                    item.setChecked(true);
                    navDrawer.closeDrawers();
                    onNavigationItemClick(item.getItemId());
                    return true;
                }else{
                    navDrawer.closeDrawers();
                    return false;
                }
            }
        });

        toggle = new ActionBarDrawerToggle(
                ((AppCompatActivity)getActivity()),
                navDrawer,
                toolbar,
                R.string.open,
                R.string.close
        ) {

            public void onDrawerClosed(View drawer) {
                super.onDrawerClosed(drawer);
                ((AppCompatActivity)getActivity()).invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawer) {
                super.onDrawerOpened(drawer);
                ((AppCompatActivity)getActivity()).invalidateOptionsMenu();
            }
        };
        //metod za animaciju!!
        navDrawer.addDrawerListener(toggle);
    }

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        toggle.syncState();
//    }

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

    private void onNavigationItemClick(int itemId) {
        switch (itemId) {
            case R.id.nav_item_dash:
               // title.setText("Dashboard Activity");
                Toast.makeText(getActivity(),"1" + itemId,Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_item_cal:
               // title.setText("Calendar Activity");
                Toast.makeText(getActivity(),"2" + itemId,Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_item_prefs:
               // title.setText("Preference Activity");

                Toast.makeText(getActivity(),"3" + itemId,Toast.LENGTH_LONG).show();
                presenter.onMapClicked();

                break;
        }
    }


    @Override
    public void OnBackPressed() {
       if(!navDrawer.isDrawerOpen(GravityCompat.START) && Mitem.isChecked()) {
           Mitem.setChecked(false);
       }else {
           navDrawer.closeDrawers();
       }
    }
}
