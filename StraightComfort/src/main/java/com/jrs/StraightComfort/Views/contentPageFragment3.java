package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Page;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-08-30.
 */
public class contentPageFragment3  extends Fragment {



    public static Fragment create() {

        contentPageFragment3 fragment = new contentPageFragment3();
        return fragment;
    }

    public contentPageFragment3(){
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        ViewGroup rootView;

            rootView = (ViewGroup) inflater.inflate(R.layout.intropage, container,false);
            String iconResource = "finger.gif";
            String data = "<html><img src=\"" + iconResource + " \" align=\"middle\" width=\"98%\" height=\"98%\"></html>";
            WebView wv = ((WebView) rootView.findViewById(R.id.wvIntroview));
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            settings.setLoadWithOverviewMode(true);
            wv.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "UTF-8", null);


        return rootView;
    }
}
