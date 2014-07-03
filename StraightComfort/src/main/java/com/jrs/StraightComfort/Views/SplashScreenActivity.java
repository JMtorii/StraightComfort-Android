package com.jrs.StraightComfort.Views;

/**
 * Created by JMtorii on 2014-03-19.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.FilterSCData;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class SplashScreenActivity extends FilterActivity {
    private static final int TIME = 2 * 1000;// 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        try {
            filterscData().init(context);
            filterscData().getPageInfo();
            filterscData().getDiscomfortInfo();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
                overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
            }
        }, TIME);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, TIME);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}