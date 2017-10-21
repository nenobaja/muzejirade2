package com.attozoic.muzejirade.presenter;

import android.os.Bundle;

import com.attozoic.muzejirade.model.iListItem;


/**
 * Created by nenadicivan on 9/29/2017.
 */

public interface PresenterPosts {

    void onResume(Bundle bundle,Boolean isRefreshing, String startingPageForMorePots);

    void onItemClicked(iListItem item);

    void onDestroy();
}
