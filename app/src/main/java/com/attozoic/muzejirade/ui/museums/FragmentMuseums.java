package com.attozoic.muzejirade.ui.museums;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attozoic.muzejirade.R;

/**
 * Created by nenadicivan on 10/4/2017.
 */

public class FragmentMuseums extends Fragment implements TabLayout.OnTabSelectedListener {


    private TabLayout tabLayout;
    private ViewPager pager;

    private static FragmentMuseums instance = null;

    public static FragmentMuseums getInstance() {
        if(instance == null) {
            instance = new FragmentMuseums();
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

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);



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
        tabLayout.setupWithViewPager(pager);

        tabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), android.R.color.white),
                ContextCompat.getColor(getActivity(), R.color.colorAccent));
        tabLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

        tabLayout.addOnTabSelectedListener(this);

        PagerAdapterMuseums adapter = new PagerAdapterMuseums(getChildFragmentManager());
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
}
