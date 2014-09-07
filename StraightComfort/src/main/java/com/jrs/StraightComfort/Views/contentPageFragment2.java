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
public class contentPageFragment2 extends Fragment {
    public static final String TEXT_PAGE = "text";


    public static Fragment create(Page page) {

        contentPageFragment2 fragment = new contentPageFragment2();
        Bundle args = new Bundle();
        String text = page.getContent();

        args.putString(TEXT_PAGE, text);

        fragment.setArguments(args);
        return fragment;
    }

    public contentPageFragment2(){
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        ViewGroup rootView;
        String text = "";
        rootView = (ViewGroup) inflater.inflate(R.layout.lifeisbetter,container,false);
        if (getArguments().getString(TEXT_PAGE).equals("LIFEISCRAP"))
        {
            text = "Make life better";
            (rootView.findViewById(R.id.tvLifebetter)).setVisibility(View.VISIBLE);
            ((TextView) rootView.findViewById(R.id.tvBetter)).setText("Yay!");

        }
        else
        {
            text = "Return home";
            ( rootView.findViewById(R.id.tvLifebetter)).setVisibility(View.INVISIBLE);
            ((TextView) rootView.findViewById(R.id.tvBetter)).setText("Life's a lot better \n now :)");
        }

        ((TextView) rootView.findViewById(R.id.tvButtonView)).setText(text);
        return rootView;
    }



}
