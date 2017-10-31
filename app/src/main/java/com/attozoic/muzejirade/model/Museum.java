package com.attozoic.muzejirade.model;

import org.parceler.Parcel;

/**
 * Created by nenadicivan on 10/20/2017.
 */
@Parcel
public class Museum implements  iListItem{

     private  double lat;
     private  double lng;
     private  String name;
     private  String link;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    protected Museum(){

    }

    public Museum(double lat, double lng, String name, String link) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.link = link;
    }
}
