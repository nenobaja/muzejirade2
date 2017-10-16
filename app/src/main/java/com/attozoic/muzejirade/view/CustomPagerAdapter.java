package com.attozoic.muzejirade.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.attozoic.muzejirade.presenter.MapFragmentPresenter;

/**
 * Created by nenadicivan on 10/14/2017.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter {



    public CustomPagerAdapter(FragmentManager fm) {

        super(fm);


    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position){
            case 0:

                fragment = new PagerListFragment();



                break;
            case 1:
                fragment = new PagerMapFragment();




                break;

        }


        return  fragment;

    }

    @Override
    public int getCount() {
        //
        return 2;
    }

    //ovde bi trebala da bude metoda koja na osnovu pozicije dodlejuje naslov

}