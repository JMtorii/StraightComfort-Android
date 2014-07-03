package com.jrs.StraightComfort.Views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.FilterSCData;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-05-21.
 */

public class WorkstationSC extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.workstation_sc);

        ArrayList<Content> contents = filterscData().getContents();

        ListView partsList = (ListView) findViewById(R.id.lvShortcuts);
        ArrayList<String[]> shortCutsList = new ArrayList<String[]>();
        try {
            for (Content content: contents) {

                shortCutsList.add(new String[]{content.getTitle(),content.getIcon()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

   /*     for (int i = 0; i< shortCuts.length;i++)
        {
            shortCutsList.add(i,shortCuts[i]);
        }
*/
        CustomAdapter listAdapter = new CustomAdapter(this, R.layout.bodypart_check, shortCutsList);

        partsList.setAdapter(listAdapter);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private class CustomAdapter extends ArrayAdapter<String[]> {

        private ArrayList<String[]> shortCutsList;

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<String[]> shortCutsList) {
            super(context, textViewResourceId, shortCutsList);
            this.shortCutsList = new ArrayList<String[]>();
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

                holder.SCname.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        ArrayList<String> solutions = new ArrayList<String>();
                        ArrayList<Integer> pagenumbers = new ArrayList<Integer>();
                        Intent mIntent = new Intent(getApplicationContext(), WorkStationView.class);
                        TextView tvTextName = (TextView) v;
                        //Toast.makeText(getApplicationContext(), tvTextName.getText(), Toast.LENGTH_LONG).show();
                        pagenumbers.add(-1);
                        solutions.add(tvTextName.getText().toString());
                        mIntent.putStringArrayListExtra("filter", solutions);
                        mIntent.putIntegerArrayListExtra("pfilter",pagenumbers);


                        startActivity(mIntent);
                    }
                });
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String icon = shortCutsList.get(position)[1];
            int iconResource = getApplicationContext().getResources().getIdentifier(icon, "drawable", getPackageName());
            String bodyPart = shortCutsList.get(position)[0];

            holder.SCname.setTextColor(Color.rgb(15, 153, 255));
            holder.SCname.setText(bodyPart);
            holder.SCname.setTextSize(25);


            holder.SCname.setGravity(Gravity.CENTER_VERTICAL);
            //Drawable res = getApplicationContext().getResources().getDrawable(iconResource);
            holder.SCIcon.setImageResource(iconResource);


            return convertView;

        }

        private class ViewHolder {
            TextView SCname;
            ImageView SCIcon;
        }

    }
}
