package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.jrs.StraightComfort.R;


public class MainActivity extends Activity {

     ImageButton FullSetupBtn;
     ImageButton WorkShortcut;
     ImageButton Discomfort;
     Intent nextScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullSetupBtn = (ImageButton) findViewById(R.id.FullWorkStation);
        WorkShortcut = (ImageButton) findViewById(R.id.WorkStation);
        Discomfort = (ImageButton) findViewById(R.id.Discomfort);


        FullSetupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(),FullWorkStation.class);
                nextScreen.putExtra("filter","all");
                startActivity(nextScreen);


            }
        })  ;
        WorkShortcut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(),WorkstationSC.class);
                startActivity(nextScreen);
            }
        })  ;
        Discomfort.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextScreen = new Intent(getApplicationContext(),Discomfort.class);
                startActivity(nextScreen);
            }
        })  ;
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
    protected void onPause(){
        super.onPause();
    }
}
