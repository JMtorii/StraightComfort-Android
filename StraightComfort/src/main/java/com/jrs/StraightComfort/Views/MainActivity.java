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
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends Activity {

    TextView FullSetupBtn;
    TextView WorkShortcut;
    TextView Discomfort;
    TextView WelcomeScreen;
    Intent nextScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullSetupBtn = (TextView) findViewById(R.id.FullWorkStation);
        WorkShortcut = (TextView) findViewById(R.id.WorkStation);
        Discomfort = (TextView) findViewById(R.id.Discomfort);
       // WelcomeScreen = (TextView) findViewById(R.id.tvTitle);


        FullSetupBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DiscomfortInfo solutions = null;
                ArrayList<SolutionInfo> solutionInfos = new ArrayList<SolutionInfo>();
                ArrayList<Integer> pagenumbers = new ArrayList<Integer>();

                pagenumbers.add(-1);
                String title = "all";
                solutionInfos.add(new SolutionInfo(title,pagenumbers));

                solutions = new DiscomfortInfo(null,solutionInfos);

                nextScreen = new Intent(getApplicationContext(), WorkStationView.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("filter",solutions);
                nextScreen.putExtras(mBundle);
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
                nextScreen = new Intent(getApplicationContext(), testview.class);
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
