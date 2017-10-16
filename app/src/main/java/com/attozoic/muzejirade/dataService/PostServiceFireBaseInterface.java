package com.attozoic.muzejirade.dataService;

/**
 * Created by nenadicivan on 9/30/2017.
 */

public interface PostServiceFireBaseInterface {

    void getItems(FireBaseDatabaseListener callback, String page);
}
