package com.jrs.StraightComfort.Views;

/**
 * Created by JMtorii on 2014-03-19.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
<<<<<<< HEAD
=======
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
>>>>>>> 7821041972c885ce01d54336c09a5afa82a19290

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Constants;
import com.jrs.StraightComfort.Utilities.FilterActivity;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;


public class SplashScreenActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
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
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.PREFERENCES,0);
                boolean firstUser = sharedPreferences.getBoolean("firstUser",true);
                if (firstUser)
                {
                    //intent = new Intent(SplashScreenActivity.this, WelcomePagerAdapter.class);
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
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