package com.jrs.StraightComfort.Views;

<<<<<<< HEAD
=======

>>>>>>> ede83baceb581abba8ec58d68cfd97d3bb4a5f25
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-05-21.
 */

public class WorkstationSC extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.workstation_sc);

        ArrayList<Content> shortCutsList = filterscData().getContents();

        ListView partsList = (ListView) findViewById(R.id.lvShortcuts);


        CustomAdapter listAdapter = new CustomAdapter(this, R.layout.bodypart_check, shortCutsList);

        partsList.setAdapter(listAdapter);
        partsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Content content = (Content) parent.getItemAtPosition(position);
                DiscomfortInfo solutions = null;
                ArrayList<SolutionInfo> solutionInfos = new ArrayList<SolutionInfo>();
                ArrayList<Integer> pagenumbers = new ArrayList<Integer>();

                String title = content.getTitle();

                pagenumbers.add(-1);
                solutionInfos.add(new SolutionInfo(title,pagenumbers));

                solutions = new DiscomfortInfo(null,solutionInfos);

                Intent mIntent = new Intent(getApplicationContext(), WorkStationView.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("filter", solutions);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private class CustomAdapter extends ArrayAdapter<Content> {

        private ArrayList<Content> shortCutsList;

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<Content> shortCutsList) {
            super(context, textViewResourceId, shortCutsList);
            this.shortCutsList = new ArrayList<Content>();
            this.shortCutsList.addAll(shortCutsList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.shortcut_check, null);

                holder = new ViewHolder();
                holder.SCname = (TextView) convertView.findViewById(R.id.tvScName);
                holder.SCIcon = (ImageView) convertView.findViewById(R.id.ivIconImage);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Drawable iconResource = shortCutsList.get(position).getIcon();
            //int iconResource = getApplicationContext().getResources().getIdentifier(icon, "drawable", getPackageName());
            String furniture = shortCutsList.get(position).getTitle();

            holder.SCname.setText(furniture);
            holder.SCname.setTextSize(25);


            holder.SCname.setGravity(Gravity.CENTER_VERTICAL);
            holder.SCIcon.setImageDrawable(iconResource);


            return convertView;

        }

        private class ViewHolder {
            TextView SCname;
            ImageView SCIcon;
        }

    }
}
