package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.jrs.StraightComfort.R;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class testview extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);
        WebView wv = (WebView) findViewById(R.id.testwebview);
        String summary ="<html><img src=\"https://i.imgur.com/b2eiP2b.png\" width=\"100%\" height=\"100%\"></html>";
        wv.loadData(summary, "text/html", null);
        WebSettings settings = wv.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}