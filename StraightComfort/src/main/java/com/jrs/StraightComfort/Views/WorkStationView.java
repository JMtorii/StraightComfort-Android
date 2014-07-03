package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.Page;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class WorkStationView extends FilterActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int numPages;
    private ArrayList<Page> pages = new ArrayList<Page>();
    private ArrayList<Integer> icons = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);


       // TextView mTitle = (TextView) findViewById(R.id.tvWorkStationTitle);
        Intent mIntent = getIntent();

        ArrayList<String> filters= mIntent.getStringArrayListExtra("filter");
        ArrayList<Integer> pfilters= mIntent.getIntegerArrayListExtra("pfilter");

        ArrayList<Content> contentPages = filterscData().getContents();
        ArrayList<Content> filterPages = new ArrayList<Content>();

        for (Content content: contentPages)
        {
            for (String filter: filters) {
                if (content.getTitle().equals(filter)) {
                    filterPages.add(content);
                } else if (filter.equals("all"))
                    filterPages.add(content);
            }
        }
        for (Content f: filterPages) {
            for (Integer pfilter : pfilters)
            {
                if (pfilter.equals(-1))
                {
                    pages.addAll(f.getPages());
                    break;
                }
                else {
                    for (Page p : f.getPages()) {
                        if (p.getPageNum() == pfilter) {
                            pages.add(p);
                        }
                    }
                }
            }
        }
        numPages = pages.size();
        for (Page p: pages) {
            int iconResource = getApplicationContext().getResources().getIdentifier(p.getImageName(), "drawable", getPackageName());
            icons.add(iconResource);
        }
            mPager = (ViewPager) findViewById(R.id.contentPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });

       // mTitle.setText(filterPages.toString());
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){return contentPageFragment.create(position,pages,icons);}
        @Override
        public int getCount(){return numPages;}

    }
    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
