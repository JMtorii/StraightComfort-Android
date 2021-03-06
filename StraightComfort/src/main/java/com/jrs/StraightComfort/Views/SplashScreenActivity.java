package com.jrs.StraightComfort.Views;

/**
 * Created by JMtorii on 2014-03-19.
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Constants;
import com.jrs.StraightComfort.Utilities.FilterActivity;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SplashScreenActivity extends FilterActivity {
    private static final int TIME = 1 * 1000;// 1 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES,0);
                boolean firstUser = sharedPreferences.getBoolean("firstUser",true);
                if (firstUser)
                {
                    intent = new Intent(SplashScreenActivity.this, WelcomePagerAdapter.class);
                }
                else {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                startActivity(intent);
                SplashScreenActivity.this.finish();
                overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
            }
        }, TIME);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = getApplicationContext();
                        if (!filterscData().isInit)
                        {
                        filterscData().init(context);
                        filterscData().getPageInfo(context);
                        filterscData().getDiscomfortInfo();
                        filterscData().isInit=true;
                        }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, TIME);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}