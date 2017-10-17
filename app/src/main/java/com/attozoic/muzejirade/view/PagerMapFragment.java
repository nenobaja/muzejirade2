package com.attozoic.muzejirade.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.presenter.MapFragmentPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

/**
 * Created by nenadicivan on 10/6/2017.
 */

public class PagerMapFragment extends Fragment implements ListOfMuseumsInteface {

    TextView content;
//   private GoogleMap mMap;

    private MapFragmentPresenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_map, container, false);
         //content = (TextView)v.findViewById(R.id.text_map);

         presenter = MapFragmentPresenter.getInstance();
         presenter.setiMuseumsMapView(this);


        return  v;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
//        getMapAsync(this);
    }

    @Override
    public void setItems(String[] items) {
        content.setText(items[2]);
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
}
