package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.Page;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import java.util.ArrayList;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class WorkStationView extends FilterActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int numPages;
    private ArrayList<Page> pages = new ArrayList<Page>();
    private ArrayList<String> icons = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);


       // TextView mTitle = (TextView) findViewById(R.id.tvWorkStationTitle);
        Intent mIntent = getIntent();

        DiscomfortInfo filter = (DiscomfortInfo) mIntent.getSerializableExtra("filter");

        ArrayList<SolutionInfo> solutionFilter = filter.getSolutionInfos();
        ArrayList<Content> contentPages = filterscData().getContents();
        for (Content content: contentPages)
        {
            for (SolutionInfo s: solutionFilter) {
                if (content.getTitle().equals(s.getFurniture())||s.getFurniture().equals("all")) {
                    for (Integer i : s.getPages())
                    {
                        for (Page p: content.getPages())
                        {
                           if (p.getPageNum().equals(i))
                               pages.add(p);
                           else if (i.equals(-1))
                           {
                               pages.addAll(content.getPages());
                               break;
                           }
                        }
                    }
                }
            }
        }

        numPages = pages.size();
        //for (Page p: pages) {
        //    int iconResource = getApplicationContext().getResources().getIdentifier(p.getImageName(), "drawable", getPackageName());
        //    icons.add(iconResource);
       // }
        for (Page p: pages) {
            icons.add(p.getImageName());
            //    int iconResource = getApplicationContext().getResources().getIdentifier(p.getImageName(), "drawable", getPackageName());
            //    icons.add(iconResource);
            //
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
