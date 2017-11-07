package com.attozoic.muzejirade.ui.museums;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.Museum;
import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.ui.details.DetailsFragment;

import org.parceler.Parcels;

/**
 * Created by nenadicivan on 10/31/2017.
 */

public class FragmentMuseumsListDetails extends Fragment{

    WebView vebView;
    ProgressDialog progressDialog;

    public  static FragmentMuseumsListDetails getInstance(Museum museum){

        FragmentMuseumsListDetails fragmentMuseumsListDetails = new FragmentMuseumsListDetails();
        Bundle bundle = new Bundle();
        bundle.putParcelable("museum", Parcels.wrap(museum));
        fragmentMuseumsListDetails.setArguments(bundle);

        return fragmentMuseumsListDetails;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_museums, container, false);
        Bundle data = this.getArguments();
        Museum museum = Parcels.unwrap(data.getParcelable("museum"));
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Učitavanje...");
        progressDialog.show();

        vebView = (WebView) rootView.findViewById(R.id.webView_content_museums);
        vebView.setWebViewClient(new MyWebViewClient2());
        vebView.loadUrl(museum.getLink());


        return  rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyWebViewClient2 extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }

}
