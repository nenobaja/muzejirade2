package com.attozoic.muzejirade.ui.posts;



import com.attozoic.muzejirade.model.iListItem;

import java.util.List;

/**
 * Created by nenadicivan on 9/29/2017.
 */

public interface PostsView {

    void showProgress();

    void hideProgress();

    void setItems(List<iListItem> items);
    void setMoreItems(List<iListItem> items);

    void showMessage(String message);
}
