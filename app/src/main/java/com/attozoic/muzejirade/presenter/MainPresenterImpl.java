package com.attozoic.muzejirade.presenter;

import android.os.Bundle;
import android.util.Log;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.FireBaseDatabaseListener;
import com.attozoic.muzejirade.dataService.PostServiceFireBaseInterface;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;

import com.attozoic.muzejirade.view.DetailsFragment;
import com.attozoic.muzejirade.view.MainListFragment;
import com.attozoic.muzejirade.view.MainView;
import com.attozoic.muzejirade.view.MapFragment;


import org.parceler.Parcels;

import java.util.List;

/**
 * Created by nenadicivan on 9/29/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private PostServiceFireBaseInterface service;


    public MainPresenterImpl(MainView mainView, PostServiceFireBaseInterface service) {
        this.mainView = mainView;
        this.service = service;
    }

    @Override
    public void onResume(Bundle bundle,Boolean isRefershing, String startingPageForMorePots) {
        Log.d("fatala","presenter se pali");
        if (bundle == null) {
            if (mainView != null && isRefershing == false) {
                mainView.showProgress();
            }


            service.getItems(new FireBaseDatabaseListener() {
                @Override
                public void onSuccess(List result) {

                    Log.d("fatala","useplo dobalvljanej podataka");
                    if (mainView != null && startingPageForMorePots == null) {
                        mainView.setItems((List<iListItem>) result);
                        mainView.hideProgress();

                    }
                    if (mainView != null && startingPageForMorePots != null) {
                        mainView.setMoreItems((List<iListItem>) result);
                        mainView.hideProgress();

                    }

                }
                @Override
                public void onError(String error) {

                }
            }, startingPageForMorePots);

        }else{
            List<iListItem> posts = Parcels.unwrap(bundle.getParcelable("items"));
           mainView.setItems(posts);
      }
    }


    @Override public void onItemClicked(iListItem item) {
        if (mainView != null) {
            Post post = (Post) item;
           mainView.showMessage("kliknuo si na " + post.getTitle());

            DetailsFragment fragment = DetailsFragment.getInstance(post);

            ((MainListFragment)mainView).getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, fragment).addToBackStack(null).commit();
        }
    }

    @Override public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onMapClicked() {
        MapFragment mapFragment = MapFragment.getInstance();

        ((MainListFragment)mainView).getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, mapFragment).addToBackStack(null).commit();


    }


    public MainView getMainView() {
        return mainView;
    }


}
