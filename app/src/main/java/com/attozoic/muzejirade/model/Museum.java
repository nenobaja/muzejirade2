package com.attozoic.muzejirade.model;

/**
 * Created by nenadicivan on 10/20/2017.
 */

public class Museum implements  iListItem{

    double lat = 44.81676154700503;
    double lng= 20.459753187696833;
    String name = "Narodni muzej u Beogradu";
    String link = "null";

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

    public Museum(double lat, double lng, String name, String link) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.link = link;
    }
}
