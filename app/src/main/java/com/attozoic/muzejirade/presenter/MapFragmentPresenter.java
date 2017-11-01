package com.attozoic.muzejirade.presenter;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.dataService.FireBaseDatabaseListener;
import com.attozoic.muzejirade.dataService.PostServiceFireBaseInterface;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.attozoic.muzejirade.ui.details.DetailsFragment;
import com.attozoic.muzejirade.ui.museums.FragmentMuseumsList;
import com.attozoic.muzejirade.ui.museums.FragmentMuseumsListDetails;
import com.attozoic.muzejirade.ui.museums.ListOfMuseumsInteface;
import com.attozoic.muzejirade.ui.posts.FragmentPosts;

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


     public void onItemClicked(iListItem item) {
        if (iMuseumsListView != null) {
            Museum museum = (Museum) item;
//            iMuseumsListView.showMessage("kliknuo si na " + post.getTitle());

            FragmentMuseumsListDetails fragment = FragmentMuseumsListDetails.getInstance(museum);
            ((FragmentMuseumsList) iMuseumsListView).getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_lay, fragment).addToBackStack(null).commit();
            //((FragmentMuseumsList) iMuseumsListView).getChildFragmentManager().beginTransaction().replace(R.id.frame_lay1, fragment).commit();
        }
    }
//pstalo od straog pogledati
//    @Override public void onDestroy() {
//        postsView = null;
//    }
}