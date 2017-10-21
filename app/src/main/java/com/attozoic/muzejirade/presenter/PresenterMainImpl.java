package com.attozoic.muzejirade.presenter;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.ui.MainActivity;
import com.attozoic.muzejirade.ui.MainView;
import com.attozoic.muzejirade.ui.museums.FragmentMuseums;
import com.attozoic.muzejirade.ui.posts.FragmentPosts;

/**
 * Created by nenadicivan on 10/21/2017.
 */

public class PresenterMainImpl implements PresenterMain {

    private MainView mainView;

    public PresenterMainImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onNavItem(int itemId) {
        switch (itemId) {
            case R.id.nav_item_dash:
                mainView.open(new FragmentPosts());
                break;
            case R.id.nav_item_prefs:
                mainView.open(FragmentMuseums.getInstance());
                break;
        }
    }
}
