package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jrs.StraightComfort.R;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ImageButton FullSetupBtn;
    ImageButton WorkShortcut;
    ImageButton Discomfort;
    TextView WelcomeScreen;
    Intent nextScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullSetupBtn = (ImageButton) findViewById(R.id.FullWorkStation);
        WorkShortcut = (ImageButton) findViewById(R.id.WorkStation);
        Discomfort = (ImageButton) findViewById(R.id.Discomfort);
        WelcomeScreen = (TextView) findViewById(R.id.tvTitle);


        FullSetupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> filter = new ArrayList<String>();
                ArrayList<Integer> pfilter = new ArrayList<Integer>();
                nextScreen = new Intent(getApplicationContext(), WorkStationView.class);
                filter.add("all");
                pfilter.add(-1);

                nextScreen.putStringArrayListExtra("filter", filter);
                nextScreen.putIntegerArrayListExtra("pfilter",pfilter);
                startActivity(nextScreen);


            }
        });
        WorkShortcut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(), WorkstationSC.class);
                startActivity(nextScreen);
            }
        });
        Discomfort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(), Discomfort.class);
                startActivity(nextScreen);
            }
        });
      /*  WelcomeScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(), WelcomePagerAdapter.class);
                startActivity(nextScreen);
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @Override
    protected void onPause() {
        super.onPause();
    }
}
