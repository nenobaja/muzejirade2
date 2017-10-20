package com.attozoic.muzejirade.presenter;

import android.os.Bundle;
import android.util.Log;

import com.attozoic.muzejirade.dataService.FireBaseDatabaseListener;
import com.attozoic.muzejirade.dataService.PostServiceFireBaseInterface;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.view.ListOfMuseumsInteface;
import com.attozoic.muzejirade.view.MapFragment;
import com.attozoic.muzejirade.view.MapFragmentInterface;

import java.util.List;

/**
 * Created by nenadicivan on 10/10/2017.
 */

public class MapFragmentPresenter {

    ListOfMuseumsInteface iMuseumsListView;
    ListOfMuseumsInteface iMuseumsMapView;

    boolean downloading = false;

    private PostServiceFireBaseInterface service;

    private static MapFragmentPresenter instance = null;

    private MapFragmentPresenter(PostServiceFireBaseInterface service) {
        this.service = service;
    }

    public static MapFragmentPresenter getInstance(PostServiceFireBaseInterface service) {

        if(instance == null) {
            instance = new MapFragmentPresenter(service);

        }
        return instance;
    }

    public void setiMuseumsListView(ListOfMuseumsInteface iMuseumsListView) {
        this.iMuseumsListView = iMuseumsListView;
    }

    public void setiMuseumsMapView(ListOfMuseumsInteface iMuseumsMapView) {
        this.iMuseumsMapView = iMuseumsMapView;
    }

    String[] listOfMuseums = {"beogradski",
            "beogradski 1",
            "beogradski 2",
            "beogradski 3",
            "beogradski 4",
            "beogradski 5",
            "beogradski 6",
            "beogradski 7",
            "beogradski 8",
            "beogradski 10"};



//dobavljamo podatke od data sourca
    public String[] getListOfMuseums() {


        return listOfMuseums;
    }

    public void  getListFromDataSource(){
        if(!downloading && iMuseumsListView!=null && iMuseumsMapView != null) {
            downloading = true;
            service.getItems2(new FireBaseDatabaseListener() {
                @Override
                public void onSuccess(List result) {
                    iMuseumsListView.setItems(result);
                    iMuseumsMapView.setItems(result);
                    downloading = false;
                }

                @Override
                public void onError(String error) {
                    downloading = false;
                }
            });

        }
    }



}