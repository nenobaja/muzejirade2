package com.attozoic.muzejirade.ui.museums;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attozoic.muzejirade.R;

import com.attozoic.muzejirade.dataService.PostServiceFireBase;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.presenter.MapFragmentPresenter;


import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

/**
 * Created by nenadicivan on 10/6/2017.
 */

public class FragmentMuseumsMap extends Fragment implements ListOfMuseumsInteface,OnMapReadyCallback {


   private GoogleMap mMap;
    boolean mapIsReady = false;

    private MapFragmentPresenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_map, container, false);

         presenter = MapFragmentPresenter.getInstance(new PostServiceFireBase());
         presenter.setiMuseumsMapView(this);

        return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment fragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
          mapIsReady = true;
        mMap = googleMap;
        presenter.getListFromDataSource();

        // Add a marker in Sydney and move the camera
        LatLng belgrade = new LatLng(44.816667, 20.466667);

        mMap.addMarker(new MarkerOptions().position(belgrade).title("Marker in Belgrade"));

       // mMap.animateCamera(CameraUpdateFactory.zoomIn());
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(belgrade));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(belgrade, 10.0f));

    }

    @Override
    public void setItems(List<Museum> items) {
        if(mapIsReady) {
            for (Museum museum : items) {
                LatLng museumPosition = new LatLng(museum.getLat(), museum.getLng());
                mMap.addMarker(new MarkerOptions().position(museumPosition).title(museum.getName()));
            }
        }
    }
}
