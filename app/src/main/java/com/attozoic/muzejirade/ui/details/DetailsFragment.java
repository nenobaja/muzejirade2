package com.attozoic.muzejirade.ui.details;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.attozoic.muzejirade.R;
import com.attozoic.muzejirade.model.Post;

import org.parceler.Parcels;


/**
 * Created by nenadicivan on 9/29/2017.
 */

public class DetailsFragment extends Fragment{


    WebView vebView;
    ProgressDialog progressDialog;

    public  static  DetailsFragment getInstance(Post post){

        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", Parcels.wrap(post));
        detailsFragment.setArguments(bundle);

        return detailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
              Bundle data = this.getArguments();
             Post post = Parcels.unwrap(data.getParcelable("post"));
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
          progressDialog.show();

          vebView = (WebView) rootView.findViewById(R.id.webView_content);
        vebView.setWebViewClient(new MyWebViewClient());
        vebView.loadUrl(post.getContent());


        return  rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
          progressDialog.dismiss();
        }
    }


}
