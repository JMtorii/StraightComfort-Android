package com.jrs.StraightComfort.Views;

import android.app.Fragment;
import android.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.viewpagerindicator.CirclePageIndicator;
import com.jrs.StraightComfort.Utilities.Constants;
import com.jrs.StraightComfort.R;


public class WelcomePagerAdapter extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private CirclePageIndicator titleIndicator;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_viewpager_layout);
        titleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.welcome_viewpager_layout);
        mPagerAdapter = new WelcomePagerAdapter2(getFragmentManager()) {};
        mPager.setAdapter(mPagerAdapter);
        titleIndicator.setViewPager(mPager);
        titleIndicator.setCurrentItem(mPagerAdapter.getCount()-1);
        titleIndicator.setCurrentItem(0);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                titleIndicator.setCurrentItem(position);
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.

                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate "up" the demo structure to the launchpad activity.
                // See http://developer.android.com/design/patterns/navigation.html for more.
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES,0);
            boolean firstUser = sharedPreferences.getBoolean("firstUser",true);
            if (!firstUser)
                super.onBackPressed();
            else
            {
                Intent intent = new Intent(getApplicationContext() ,MainActivity.class);
                startActivity(intent);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstUser",false);
                editor.commit();
                this.finish();

            }

    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class WelcomePagerAdapter2 extends FragmentStatePagerAdapter {
        public WelcomePagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

                return FragmentWelcome1.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}