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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Content;
import com.jrs.StraightComfort.Utilities.DiscomfortInfo;
import com.jrs.StraightComfort.Utilities.FilterActivity;
import com.jrs.StraightComfort.Utilities.SolutionInfo;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-05-24.
 */
public class Solutions extends FilterActivity {
    CustomAdapter dataAdapter = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solutions);
        ListView solutionListView = (ListView) findViewById(R.id.lvSolutions);

        ArrayList<DiscomfortInfo> contents = filterscData().getDiscomfortcontents();

        ArrayList<DiscomfortInfo> solutionList = new ArrayList<DiscomfortInfo>();

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


        dataAdapter = new CustomAdapter(this, R.layout.solutiontexts, solutionList);

        solutionListView.setAdapter(dataAdapter);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private class CustomAdapter extends ArrayAdapter<DiscomfortInfo> {

        private ArrayList<DiscomfortInfo> solutionList;

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<DiscomfortInfo> solutionList) {
            super(context, textViewResourceId, solutionList);
            this.solutionList = new ArrayList<DiscomfortInfo>();
            this.solutionList.addAll(solutionList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.solutiontexts, null);

                holder = new ViewHolder();
                holder.problemTitle = (TextView) convertView.findViewById(R.id.tvProblemPart);
                holder.sList = (TextView) convertView.findViewById(R.id.tvSolutionlist);
                holder.tvLayout = (RelativeLayout) convertView.findViewById(R.id.rlSolutionView);
                holder.tvLayout.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent mIntent = new Intent(getApplicationContext(), WorkStationView.class);
                        RelativeLayout tvTextName = (RelativeLayout) v;

                        //Toast.makeText(getApplicationContext(), tvTextName.getText(), Toast.LENGTH_LONG).show();

                       // mIntent.putExtra("filter", tvTextName.getText().toString());
                        startActivity(mIntent);
                    }
                });
                convertView.setTag(holder);


            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DiscomfortInfo discomforts = solutionList.get(position);
            String bodyPart = "";
            ArrayList<SolutionInfo> solutions = discomforts.getSolutionInfos();
            holder.problemTitle.setTextColor(Color.rgb(15, 153, 255));
            holder.problemTitle.setText(solutionList.get(position).getBodypart().getName());
            holder.problemTitle.setTextSize(25);

            for (SolutionInfo s : solutions)
            {
                bodyPart += s.getFurniture()+ "\n";
            }

            holder.sList.setTextColor(Color.rgb(15, 153, 255));
            holder.sList.setText(bodyPart);
            return convertView;

        }

        private class ViewHolder {
            TextView problemTitle;
            TextView sList;
            RelativeLayout tvLayout;
        }

    }

}
