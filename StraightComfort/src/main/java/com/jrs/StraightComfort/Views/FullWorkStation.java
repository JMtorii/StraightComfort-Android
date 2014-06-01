package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jrs.StraightComfort.R;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class FullWorkStation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);
        TextView mTitle = (TextView) findViewById(R.id.tvWorkStationTitle);
        Intent mIntent = getIntent();

        mTitle.setText(mIntent.getStringExtra("filter"));




    }
    @Override public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
