package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowId;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.Page;
import com.jrs.StraightComfort.Utilities.SolutionInfo;
import com.jrs.StraightComfort.Utilities.CustomViewPager;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class WorkStationView extends FilterActivity  {
    private CustomViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int numPages;
    public int prevpos = -1;
    private ArrayList<Page> showingPages = new ArrayList<Page>();
    private ArrayList<String> actionBarTitles = new ArrayList<String>();
    private ArrayList<Integer> startPages = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);


       // TextView mTitle = (TextView) findViewById(R.id.tvWorkStationTitle);
        Intent mIntent = getIntent();

        DiscomfortInfo filter = (DiscomfortInfo) mIntent.getSerializableExtra("filter");

        ArrayList<SolutionInfo> solutionFilter = filter.getSolutionInfos();
        ArrayList<Content> contentPages = filterscData().getContents();

        showingPages.add(new Page("INTRO", "INTRO", -1));
        actionBarTitles.add("Start");
        int counter1 = 0;
        int counter2 = 1;
        String prevContent= "";

        for (Content content: contentPages)
        {
            for (SolutionInfo s: solutionFilter) {

                if (content.getTitle().equals(s.getFurniture())||s.getFurniture().equals("all")) {
                    ArrayList<Page> currPages = new ArrayList<Page>();
                    for (Integer i : s.getPages()) {
                        for (Page p : content.getPages()) {
                            if (p.getPageNum().equals(i)) {
                                showingPages.add(p);
                                if (prevContent!=content.getTitle())
                                {
                                    prevContent=content.getTitle();
                                    startPages.add(actionBarTitles.size());
                                }
                                prevContent = content.getTitle();
                                actionBarTitles.add(content.getTitle());
                            } else if (i.equals(-1)) {
                                showingPages.addAll(content.getPages());
                                for (Page page : content.getPages())
                                {
                                    if (prevContent!=content.getTitle())
                                    {
                                        prevContent=content.getTitle();
                                        startPages.add(actionBarTitles.size());
                                    }
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
                }

            }
            counter1++;
        }
        ArrayList<Page> endingPage = new ArrayList<Page>();
        endingPage.add(new Page("LIFEISBETTER","LIFEISBETTER",-1));
        showingPages.add(new Page("LIFEISBETTER","LIFEISBETTER",-1));
        actionBarTitles.add("Life is better");
        numPages = showingPages.size();
        mPager = (CustomViewPager) findViewById(R.id.contentPager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        (mPager.getAdapter()).getPageTitle(0);

        mPager.setOnPageChangeListener(new CustomViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, final float v, final int i2){

            }
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                mPager = (CustomViewPager) findViewById(R.id.contentPager);
                (mPager.getAdapter()).getPageTitle(position);
                mPager.setCurrPos(position);
                if ((mPager.getAdapter()).getPageTitle(position).equals("Make Life Better")) {
                    TextView textView = (TextView) mPager.findViewById(R.id.tvButtonView);

                    mPager.setPaging(false);
                   textView.setOnClickListener(new View.OnClickListener() {
                       public void onClick(View v) {
                           for(int i =0;i<startPages.size();i++){
                               if (mPager.getCurrentItem()+1==startPages.get(i)) {
                                   mPager.setCurrentItem(startPages.get(i));
                                   break;
                               }
                           }

                       }
                   });
                }
                else{
                    mPager.setPaging(true);
                    if (startPages.contains(position)==true)
                    {
                        mPager.setStartPaging(true);
                    }
                    else
                    {
                        mPager.setStartPaging(false);
                    }

                }
            }
            @Override
            public void onPageScrollStateChanged(final int i){

            }



        });

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            return contentPageFragment.create(position,showingPages.get(position));
        }
        @Override
        public int getCount(){return numPages;}
        @Override
        public CharSequence getPageTitle(int position){
            getActionBar().setTitle(actionBarTitles.get(position));
            return actionBarTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
