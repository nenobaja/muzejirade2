package com.attozoic.muzejirade.dataService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by nenadicivan on 10/3/2017.
 */

public class MyFireBaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIDService";
    @Override
    public void onTokenRefresh() {


        String recent_token = FirebaseInstanceId.getInstance().getToken();

        Log.e("tokennnn","Refreshed token: " + recent_token);
    }
}
