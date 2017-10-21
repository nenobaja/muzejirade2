package com.attozoic.muzejirade.presenter;

import android.os.Bundle;
import android.util.Log;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.FireBaseDatabaseListener;
import com.attozoic.muzejirade.dataService.PostServiceFireBaseInterface;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;

import com.attozoic.muzejirade.ui.details.DetailsFragment;
import com.attozoic.muzejirade.ui.posts.FragmentPosts;
import com.attozoic.muzejirade.ui.posts.PostsView;


import org.parceler.Parcels;

import java.util.List;

/**
 * Created by nenadicivan on 9/29/2017.
 */

public class PresenterPostsImpl implements PresenterPosts {

    private PostsView postsView;
    private PostServiceFireBaseInterface service;


    public PresenterPostsImpl(PostsView postsView, PostServiceFireBaseInterface service) {
        this.postsView = postsView;
        this.service = service;
    }

    @Override
    public void onResume(Bundle bundle,Boolean isRefershing, String startingPageForMorePots) {
        Log.d("fatala","presenter se pali");
        if (bundle == null) {
            if (postsView != null && isRefershing == false) {
                postsView.showProgress();
            }


            service.getItems(new FireBaseDatabaseListener() {
                @Override
                public void onSuccess(List result) {

                    Log.d("fatala","useplo dobalvljanej podataka");
                    if (postsView != null && startingPageForMorePots == null) {
                        postsView.setItems((List<iListItem>) result);
                        postsView.hideProgress();

                    }
                    if (postsView != null && startingPageForMorePots != null) {
                        postsView.setMoreItems((List<iListItem>) result);
                        postsView.hideProgress();

                    }

                }
                @Override
                public void onError(String error) {

                }
            }, startingPageForMorePots);

        }else{
            List<iListItem> posts = Parcels.unwrap(bundle.getParcelable("items"));
           postsView.setItems(posts);
      }
    }


    @Override public void onItemClicked(iListItem item) {
        if (postsView != null) {
            Post post = (Post) item;
           postsView.showMessage("kliknuo si na " + post.getTitle());

            DetailsFragment fragment = DetailsFragment.getInstance(post);

            ((FragmentPosts) postsView).getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, fragment).addToBackStack(null).commit();
        }
    }

    @Override public void onDestroy() {
        postsView = null;
    }


    public PostsView getPostsView() {
        return postsView;
    }


}
