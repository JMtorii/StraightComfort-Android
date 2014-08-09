package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Page;

/**
 * Created by Steve_2 on 2014-06-29.
 */
public class contentPageFragment extends Fragment{

    public static final String ARG_PAGE = "page";
    public static final String TEXT_PAGE = "text";
    public static final String IMAGE_PAGE = "image";

    private String ftexts = "";
    private String fimage = "";
    private String title = "";
    private int mPageNumber;

    public static Fragment create(int position,Page page) {
        contentPageFragment fragment = new contentPageFragment();
        Bundle args = new Bundle();
        String text = page.getContent();
        String image = page.getImageName();

        args.putString(TEXT_PAGE, text);
        args.putString(IMAGE_PAGE, image);
        args.putInt(ARG_PAGE, position);

        fragment.setArguments(args);
        return fragment;
    }
    public contentPageFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        ftexts = getArguments().getString(TEXT_PAGE);
        fimage = getArguments().getString(IMAGE_PAGE);
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = null;

        if (ftexts.equals("INTRO"))
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.intropage, container,false);

            String iconResource = "finger.gif";
            String data = "<html><img src=\"" + iconResource + " \"width=1000dpi height=1000dpi></html>";
            WebView wv = ((WebView) rootView.findViewById(R.id.wvIntroview));
            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            settings.setLoadWithOverviewMode(true);
            wv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            wv.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "UTF-8", null);
        }
        else if (ftexts.equals("LIFEISCRAP"))
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.lifeisbetter,container,false);
            String text = "Make life better";
            ((TextView) rootView.findViewById(R.id.tvButtonView)).setText(text);
            final TextView textView = ((TextView) rootView.findViewById(R.id.tvBetter));

            textView.setText("Yay!");

        }
        else if (ftexts.equals("LIFEISBETTER"))
        {
            rootView = (ViewGroup) inflater.inflate(R.layout.lifeisbetter,container,false);
            String text = "Return home";
            ( rootView.findViewById(R.id.tvLifebetter)).setVisibility(View.INVISIBLE);
            ((TextView) rootView.findViewById(R.id.tvBetter)).setText("Life's a lot better \n now :)");
            ((TextView) rootView.findViewById(R.id.tvButtonView)).setText(text);
            ( rootView.findViewById(R.id.tvButtonView)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    getActivity().finish();
                }
            });

        }
        else {
            rootView = (ViewGroup) inflater.inflate(R.layout.contentholder, container, false);
            String iconResource = fimage;
            String data = "<html><img src=\"" + iconResource + " \" align=\"middle\" width=\"1000dp\" height=\"1000dp\"></html>";
            TextView textView = (TextView)rootView.findViewById(R.id.tvContentText);
            ((TextView) rootView.findViewById(R.id.tvContentText)).setText(ftexts);
            RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rlContentView);

            WebView wv = ((WebView) rootView.findViewById(R.id.wvContentImage));
            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(false);
            settings.setLoadWithOverviewMode(true);
            relativeLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            wv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            wv.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "UTF-8", null);
        }

        return rootView;
    }



}
