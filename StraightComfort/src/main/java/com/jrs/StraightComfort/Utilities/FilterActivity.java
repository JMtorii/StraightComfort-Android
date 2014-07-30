package com.jrs.StraightComfort.Utilities;

import android.app.Activity;

/**
 * Created by Steve_2 on 2014-06-18.
 */
public class FilterActivity extends Activity {
    private final FilterSCData scData = FilterSCData.getInstance();

    protected FilterSCData filterscData() {
        return scData;
    }


}
