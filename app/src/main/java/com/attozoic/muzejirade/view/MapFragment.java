package com.attozoic.muzejirade.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.presenter.MapFragmentPresenter;

import java.util.List;

/**
 * Created by nenadicivan on 10/4/2017.
 */

public class MapFragment extends Fragment implements TabLayout.OnTabSelectedListener {


    private TabLayout tabLayout;
    private ViewPager pager;

    private static MapFragment instance = null;

    public static MapFragment getInstance() {
        Log.d("merim","zove konstruktor");
        if(instance == null) {
            instance = new MapFragment();
            Log.d("merim","zove novi");
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("merim","3 createvju");

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);



        ((AppCompatActivity)getActivity()).getSupportActionBar().setElevation(0f);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tbl_basic);
        pager = (ViewPager) rootView.findViewById(R.id.vpg_map_list);


        setUpPagerAndTabs();




        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    private void setUpPagerAndTabs() {
        tabLayout.addTab(
                //ovde se moze inace postaviti i custom layout ako zelimo!!!
                tabLayout.newTab().setText("Mapa")
        );
        tabLayout.addTab(
                tabLayout.newTab().setText("Lista")
        );

        tabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), android.R.color.white),
                ContextCompat.getColor(getActivity(), R.color.colorAccent));
        tabLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        tabLayout.addOnTabSelectedListener(this);

        CustomPagerAdapter adapter = new CustomPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);


    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {


    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (pager != null) {
            Log.d("derim","sis");
            // before screen rotation it's better to detach pagerAdapter from the ViewPager, so
            // pagerAdapter can remove all old fragments, so they're not reused after rotation.
            pager.setAdapter(null);
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("derim","map je pauziran");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("derim","map je stop");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("derim","map je dview");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();


    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("derim","map je detach");
    }
}
