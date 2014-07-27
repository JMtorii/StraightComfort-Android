package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.Page;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class WorkStationView extends FilterActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int numPages;
    private ArrayList<String> icons = new ArrayList<String>();
    private ArrayList<Content> showingContent = new ArrayList<Content>();
    private ArrayList<Page> showingPages = new ArrayList<Page>();
    private ArrayList<String> actionBarTitles = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);


       // TextView mTitle = (TextView) findViewById(R.id.tvWorkStationTitle);
        Intent mIntent = getIntent();

        DiscomfortInfo filter = (DiscomfortInfo) mIntent.getSerializableExtra("filter");

        ArrayList<SolutionInfo> solutionFilter = filter.getSolutionInfos();
        ArrayList<Content> contentPages = filterscData().getContents();
      //  ArrayList<Page> introPage = new ArrayList<Page>();
      //  introPage.add(new Page("INTRO","INTRO",-1));
        //showingContent.add(new Content("INTRO",null,introPage));
        showingPages.add(new Page("INTRO", "INTRO", -1));
        actionBarTitles.add("Start");
        int counter1 = 0;
        int counter2 = 1;
        for (Content content: contentPages)
        {

            for (SolutionInfo s: solutionFilter) {

                if (content.getTitle().equals(s.getFurniture())||s.getFurniture().equals("all")) {
                    ArrayList<Page> currPages = new ArrayList<Page>();
                    for (Integer i : s.getPages()) {
                        for (Page p : content.getPages()) {
                            if (p.getPageNum().equals(i)) {
                               // currPages.add(p);
                                showingPages.add(p);
                                actionBarTitles.add(content.getTitle());
                            } else if (i.equals(-1)) {
                               // currPages.addAll(content.getPages());
                                showingPages.addAll(content.getPages());
                                for (Page page : content.getPages())
                                {
                                    actionBarTitles.add(content.getTitle());
                                }
                                break;
                            }
                        }
                    }
                    if (s.getFurniture().equals("all")&&counter1<contentPages.size()-1||counter2<solutionFilter.size()){
                        currPages.add(new Page("LIFEISCRAP","LIFEISCRAP",-1));
                        actionBarTitles.add("Make Life Better");
                        showingPages.add(new Page("LIFEISCRAP","LIFEISCRAP",-1));
                        counter2++;
                    }
                 //   showingContent.add(new Content(content.getTitle(),content.getIcon(),currPages));
                }

            }
            counter1++;
        }
        ArrayList<Page> endingPage = new ArrayList<Page>();
        endingPage.add(new Page("LIFEISBETTER","LIFEISBETTER",-1));
       // showingContent.add(new Content("LIFEISBETTER",null,endingPage));
        showingPages.add(new Page("LIFEISBETTER","LIFEISBETTER",-1));
        actionBarTitles.add("Life is better");
        numPages = showingPages.size();
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

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){return contentPageFragment.create(position,showingPages.get(position),actionBarTitles.get(position));}
        @Override
        public int getCount(){return numPages;}


    }
    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
