package com.attozoic.muzejirade.dataService;

import com.attozoic.muzejirade.model.iListItem;

import java.util.List;

/**
 * Created by nenadicivan on 9/30/2017.
 */

public interface FireBaseDatabaseListener<T extends iListItem> {
    public void onSuccess(List<T> result);
    public void onError(String error);
}