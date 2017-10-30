package com.attozoic.muzejirade.ui.posts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.PostServiceFireBase;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.presenter.PresenterPosts;
import com.attozoic.muzejirade.presenter.PresenterPostsImpl;
import com.attozoic.muzejirade.utils.OnRecyclerItemClickListener;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by nenadicivan on 9/28/2017.
 */

public class FragmentPosts extends Fragment implements PostsView {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterPosts adapter;
    ProgressDialog progressDialog;


    private boolean isLoading;
    private int visibleThreshold = 3;
    private int lastVisibleItem, totalItemCount;

    private PresenterPosts presenter;

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
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

        presenter = new PresenterPostsImpl(this, new PostServiceFireBase());
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onResume(savedInstanceState, true, null);
            }
        });
        adapter = new AdapterPosts(new OnRecyclerItemClickListener() {
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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("items", Parcels.wrap(adapter.getListItems()));
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
}
