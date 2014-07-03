package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Page;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-06-29.
 */
public class contentPageFragment extends Fragment{

    public static final String ARG_PAGE = "page";
    public static final String TEXT_PAGE = "text";
    public static final String IMAGE_PAGE = "image";

    private ArrayList<String> ftexts = new ArrayList<String>();
    private ArrayList<Integer> fimages = new ArrayList<Integer>();
    private int mPageNumber;

    public static Fragment create(int position,ArrayList<Page> pages,ArrayList<Integer> icons) {
        contentPageFragment fragment = new contentPageFragment();
        Bundle args = new Bundle();
        ArrayList<String> texts = new ArrayList<String>();
        ArrayList<Integer> images = new ArrayList<Integer>();


        for (Page page: pages)
        {
            texts.add(page.getContent());
        }
        images = icons;

        args.putStringArrayList(TEXT_PAGE,texts);
        args.putIntegerArrayList(IMAGE_PAGE,images);
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
        ftexts = getArguments().getStringArrayList(TEXT_PAGE);
        fimages = getArguments().getIntegerArrayList(IMAGE_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.contentholder, container, false);

        int iconResource = fimages.get(mPageNumber);

        ((TextView) rootView.findViewById(R.id.tvContentText)).setText(ftexts.get(mPageNumber));
        ((ImageView) rootView.findViewById(R.id.ivContentImage)).setImageResource(iconResource);
        return rootView;
    }
    public int getPageNumber() {
        return mPageNumber;
    }
}
