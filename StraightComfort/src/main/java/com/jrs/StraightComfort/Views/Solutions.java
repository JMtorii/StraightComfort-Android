package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Bodypart;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.Page;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-05-24.
 */
public class Solutions extends FilterActivity {
    CustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solutions);

        displayListView();

    }
    private void displayListView() {

        ArrayList<DiscomfortInfo> contents = filterscData().getDiscomfortcontents();

        ArrayList<DiscomfortInfo> solutionList = new ArrayList<DiscomfortInfo>();
        //  ArrayList<DiscomfortInfo> solutionList = (ArrayList<DiscomfortInfo>) mIntent.getSerializableExtra("solution");

        try {
            for (DiscomfortInfo discomfort: contents)
            {
                if (discomfort.getBodypart().isSelected()) {
                    solutionList.add(discomfort);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterscData().setDiscomfortcontents(contents);
        //create an ArrayAdaptar from the String Array
        dataAdapter = new CustomAdapter(this,
                R.layout.solutiontexts, solutionList);
        ListView listView = (ListView) findViewById(R.id.lvSolutions);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                DiscomfortInfo discomfortInfo = (DiscomfortInfo) parent.getItemAtPosition(position);


                Intent mIntent = new Intent(getApplicationContext(), WorkStationView.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("filter",discomfortInfo);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);

            }
        });

    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private class CustomAdapter extends ArrayAdapter<DiscomfortInfo> {

        private ArrayList<DiscomfortInfo> bodypartList;

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<DiscomfortInfo> bodypartList) {
            super(context, textViewResourceId, bodypartList);
            this.bodypartList = new ArrayList<DiscomfortInfo>();
            this.bodypartList.addAll(bodypartList);
        }

        private class ViewHolder {
            TextView BPname;
            TextView BPactual;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;


            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.solutiontexts, null);

                holder = new ViewHolder();
                holder.BPname = (TextView) convertView.findViewById(R.id.tvProblemPart);
                holder.BPactual = (TextView) convertView.findViewById(R.id.tvSolutionlist);
                convertView.setTag(holder);


            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            DiscomfortInfo discomfortInfo = bodypartList.get(position);
            ArrayList<SolutionInfo> solutions = discomfortInfo.getSolutionInfos();
            holder.BPname.setText(discomfortInfo.getBodypart().getName());

            String bodyParts = "";
            for (SolutionInfo s : solutions)
            {
                bodyParts += s.getFurniture()+ "\n";
            }

            holder.BPactual.setText(bodyParts);
            holder.BPname.setTextSize(25);


            return convertView;

        }
    }



}
