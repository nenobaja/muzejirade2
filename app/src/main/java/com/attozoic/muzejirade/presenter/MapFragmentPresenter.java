package com.attozoic.muzejirade.presenter;

import android.os.Bundle;
import android.util.Log;

import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.view.ListOfMuseumsInteface;
import com.attozoic.muzejirade.view.MapFragment;
import com.attozoic.muzejirade.view.MapFragmentInterface;

/**
 * Created by nenadicivan on 10/10/2017.
 */

public class MapFragmentPresenter {

    ListOfMuseumsInteface iMuseumsListView;
    ListOfMuseumsInteface iMuseumsMapView;

    private static MapFragmentPresenter instance = null;

    public static MapFragmentPresenter getInstance() {

        if(instance == null) {
            instance = new MapFragmentPresenter();

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
       iMuseumsListView.setItems(getListOfMuseums());
    }



}