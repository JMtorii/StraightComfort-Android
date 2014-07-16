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
    private static final int TIME = 4 * 1000;// 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
                try {
                    Context context = getApplicationContext();
                    if (filterscData().isInit == false) {
                        filterscData().init(context);
                        filterscData().getPageInfo(context);
                        filterscData().getDiscomfortInfo();
                        filterscData().isInit = true;
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