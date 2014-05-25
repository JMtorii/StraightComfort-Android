package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jrs.StraightComfort.R;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class FullWorkStation extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_workstation);



    }
    @Override public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
