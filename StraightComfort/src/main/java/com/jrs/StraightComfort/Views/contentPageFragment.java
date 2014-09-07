package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.text.method.ScrollingMovementMethod;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Constants;
import com.jrs.StraightComfort.Utilities.Page;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-06-29.
 */
public class contentPageFragment extends Fragment{

    public static final String TEXT_PAGE = "text";
    public static final String IMAGE_PAGE = "image";

    public static Fragment create(Page page) {

        contentPageFragment fragment = new contentPageFragment();
        Bundle args = new Bundle();
        String text = page.getContent();
        String image = page.getImageName();

        args.putString(TEXT_PAGE, text);
        args.putString(IMAGE_PAGE, image);

        fragment.setArguments(args);
        return fragment;
    }


    public contentPageFragment(){
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
            ViewGroup rootView;
            rootView = (ViewGroup) inflater.inflate(R.layout.contentholder, container, false);
            rootView.refreshDrawableState();
            TextView textView = (TextView) rootView.findViewById(R.id.tvContentText);
            String iconResource = getArguments().getString(IMAGE_PAGE);
            String data = "<html><img src=\"" + iconResource + " \" align=\"middle\" width=\"98%\" height=\"98%\"></html>";

            textView.setText(getArguments().getString(TEXT_PAGE));
            textView.setMovementMethod(new ScrollingMovementMethod());

            WebView wv = ((WebView) rootView.findViewById(R.id.wvContentImage));

            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            settings.setLoadWithOverviewMode(true);
            settings.setLoadsImagesAutomatically(true);
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
            wv.loadDataWithBaseURL("file:///android_asset/drawable", data, "text/html", "UTF-8", null);
            wv.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
            return rootView;
    }



}
