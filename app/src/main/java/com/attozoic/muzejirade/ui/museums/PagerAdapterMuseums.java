package com.attozoic.muzejirade.ui.museums;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by nenadicivan on 10/14/2017.
 */

public class PagerAdapterMuseums extends FragmentPagerAdapter {



    public PagerAdapterMuseums(FragmentManager fm) {

        super(fm);


    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MAP";

            case 1:
                return "LIST";

            default:
                return super.getPageTitle(position);
        }
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new Fragment();

        switch (position){
            case 0:

                fragment = new FragmentMuseumsMap();



                break;
            case 1:

                fragment = new FragmentMuseumsList();



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