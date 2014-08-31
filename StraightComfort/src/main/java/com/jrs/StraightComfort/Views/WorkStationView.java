package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
public class WorkStationView extends FilterActivity  {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private int numPages;
    private int mCurPos;
    private ArrayList<Page> showingPages = new ArrayList<Page>();
    private ArrayList<String> actionBarTitles = new ArrayList<String>();
    private ArrayList<ArrayList<Page>> mContentPages = new ArrayList<ArrayList<Page>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);
        Intent mIntent = getIntent();
        DiscomfortInfo filter = (DiscomfortInfo) mIntent.getSerializableExtra("filter");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<SolutionInfo> solutionFilter = filter.getSolutionInfos();
        ArrayList<Content> contentPages = filterscData().getContents();


        int counter1 = 0;
        int counter2 = 0;

        for (SolutionInfo solution :solutionFilter)
        {
            for (Content content: contentPages)
            {
                ArrayList<Page> sepPages = new ArrayList<Page>();
                if (solution.getFurniture().equals(content.getTitle())||solution.getFurniture().equals("all"))
                {
                    if (counter1 == 0 && counter2 == 0)
                    {
                        sepPages.add(new Page("INTRO","INTRO",-1));
                    }

                    for (Integer filterPages: solution.getPages())
                    {
                        if (filterPages.equals(-1))
                        {
                            sepPages.addAll(content.getPages());
                            break;
                        }
                        for (Page p: content.getPages())
                        {
                            if (filterPages.equals(p.getPageNum()) || filterPages.equals(-1))
                            {
                                sepPages.add(p);
                            }
                        }
                    }
                    actionBarTitles.add(content.getTitle());
                    sepPages.add(new Page("LIFEISCRAP","LIFEISCRAP",-1));
                    mContentPages.add(sepPages);
                    counter2++;
                }
            }
            counter1++;
            counter2=0;
        }
        mContentPages.get(mContentPages.size()-1).set(mContentPages.get(mContentPages.size()-1).size()-1,new Page("LIFEISBETTER","LIFEISBETTER",-1));


        mPager = (ViewPager) findViewById(R.id.full_workstation);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        loadCurrentContent(mCurPos);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                if (mPager.getCurrentItem() == (mContentPages.get(mCurPos).size()-1)) {
                    TextView textView = (TextView) mPager.findViewById(R.id.tvButtonView);
                    if (mCurPos == mContentPages.size()-1)
                    {
                        textView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                               onBackPressed();
                            }
                        });
                    }
                    else {
                        textView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                mCurPos += 1;
                                mPager.setCurrentItem(0);
                                loadCurrentContent(mCurPos);
                            }
                        });
                    }
                }
                 invalidateOptionsMenu();
            }
        });

    }


    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.activity_screen_slide, menu);

            menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);

            // Add either a "next" or "finish" button to the action bar, depending on which page
            // is currently selected.
            MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
                    (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
                            ? R.string.action_finish
                            : R.string.action_next);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            return true;
        }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.

                if (mCurPos>0) {
                    mCurPos-=1;
                    loadCurrentContent(mCurPos);
                }
                else if (mCurPos==0)
                    onBackPressed();
                return true;

            case R.id.action_previous:
                // Go to the previous step in the wizard. If there is no previous step,
                // setCurrentItem will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;

            case R.id.action_next:
                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
                // will do nothing.
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadCurrentContent(int position)
    {
        showingPages = mContentPages.get(position);
        numPages = showingPages.size();
        mPager.setOffscreenPageLimit(1);
        getActionBar().setTitle(actionBarTitles.get(position));
        mPagerAdapter.notifyDataSetChanged();

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public int getItemPosition(Object object){
            return PagerAdapter.POSITION_NONE;
        }
        @Override
        public Fragment getItem(int position){
            if (position == 0 && mCurPos == 0)
                 return contentPageFragment3.create();
            else if (position == numPages-1)
                 return contentPageFragment2.create(showingPages.get(position));
            else
                 return contentPageFragment.create(showingPages.get(position));

        }


        @Override
        public int getCount(){return numPages;}

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }


}
