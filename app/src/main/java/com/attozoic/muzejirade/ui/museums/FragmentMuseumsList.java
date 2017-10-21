package com.attozoic.muzejirade.ui.museums;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.PostServiceFireBase;
import com.attozoic.muzejirade.presenter.MapFragmentPresenter;

import java.util.List;

/**
 * Created by nenadicivan on 10/6/2017.
 */

public class FragmentMuseumsList extends Fragment implements ListOfMuseumsInteface {

    private RecyclerView recyclerView;

    private AdapterMuseumsList adapter;

    private MapFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.fragment_pager_list,container,false);
        Log.d("merim","lista je kreiraana");

        presenter = MapFragmentPresenter.getInstance(new PostServiceFireBase());
        presenter.setiMuseumsListView(this);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMuseums);
        recyclerView.setHasFixedSize(true);

        adapter = new AdapterMuseumsList();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.getListFromDataSource();
    }

    @Override
    public void setItems(List items) {

       adapter.updateListItems(items);

    }
}
