package com.jrs.StraightComfort.Views;

/**
 * Created by JMtorii on 2014-03-19.
 */

<<<<<<< HEAD
=======

>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
>>>>>>> 7821041972c885ce01d54336c09a5afa82a19290
=======
>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Constants;
import com.jrs.StraightComfort.Utilities.FilterActivity;
<<<<<<< HEAD

import java.io.IOException;
=======
>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25

import org.xmlpull.v1.XmlPullParserException;


public class SplashScreenActivity extends FilterActivity {
<<<<<<< HEAD
=======
    private static final int TIME = 1 * 1000;// 1 seconds
>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
<<<<<<< HEAD
<<<<<<< HEAD
                Intent intent = null;
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES, 0);
                boolean firstUser = sharedPreferences.getBoolean(Constants.SPLASH_FIRST_TIME_KEY, true);
               // if (firstUser)
               // {
             //       intent = new Intent(SplashScreenActivity.this, WelcomePagerAdapter.class);

                //}
                //else {
                  //  intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                //}
=======
                Intent intent =null;
=======
                Intent intent = null;
>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES,0);
                boolean firstUser = sharedPreferences.getBoolean("firstUser",true);
                if (firstUser)
                {
                    intent = new Intent(SplashScreenActivity.this, WelcomePagerAdapter.class);
                }
                else {
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
>>>>>>> 7821041972c885ce01d54336c09a5afa82a19290
                startActivity(intent);
                SplashScreenActivity.this.finish();
                overridePendingTransition(R.anim.splash_fade_in, R.anim.splash_fade_out);
            }
        }, Constants.SPLASH_TIME_MS);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = getApplicationContext();
                    if (!filterscData().isInit) {
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
        }, Constants.SPLASH_TIME_MS);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}