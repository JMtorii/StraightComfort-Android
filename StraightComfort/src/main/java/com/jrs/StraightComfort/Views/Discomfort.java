package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jrs.StraightComfort.R;
import com.jrs.StraightComfort.Utilities.Bodypart;

import java.util.ArrayList;

/**
 * Created by Steve Jung (jsh0211) on 2014-05-21.
 */
public class Discomfort extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discomfort);
        ListView partsList = (ListView) findViewById(R.id.lvAnalyze);
        ArrayList<Bodypart>  bodypartList = new ArrayList<Bodypart>();
        ImageButton btnAnalyze = (ImageButton) findViewById(R.id.btnAnalyze);


        //RelativeLayout.LayoutParams cbparams = new RelativeLayout.LayoutParams(80,RelativeLayout.LayoutParams.MATCH_PARENT);
        //cbparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        //RelativeLayout.LayoutParams tvparams = new RelativeLayout.LayoutParams(20,RelativeLayout.LayoutPara,ms.MATCH_PARENT);
        //tvparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        Bodypart[] bodyParts = new Bodypart[] { new Bodypart("Nose","Eyes","Mouth")};
        for (int i = 0; i< bodyParts.length;i++)
        {
            bodypartList.add(i,bodyParts[i]);
        }

        final CustomAdapter listAdapter = new CustomAdapter(this,R.layout.bodypart_check,bodypartList);

        partsList.setAdapter(listAdapter);


        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Bodypart> selectedParts = listAdapter.bodypartList;

            }
        });

    }
    private class CustomAdapter extends ArrayAdapter<Bodypart> {

        private ArrayList<Bodypart> bodypartList;

        public CustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Bodypart> bodypartList) {
            super(context, textViewResourceId, bodypartList);
            this.bodypartList = new ArrayList<Bodypart>();
            this.bodypartList.addAll(bodypartList);
        }

        private class ViewHolder {
            TextView BPname;
            CheckBox BPcheckBox;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.bodypart_check, null);

                holder = new ViewHolder();
                holder.BPname  = (TextView) convertView.findViewById(R.id.tvBPname);
                holder.BPcheckBox = (CheckBox) convertView.findViewById(R.id.cbBPname);
                convertView.setTag(holder);

                holder.BPcheckBox.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        cb.getTag();
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Bodypart bodyPart = bodypartList.get(position);
            holder.BPname.setText(bodyPart.getName());
            holder.BPname.setTextSize(50);


            return convertView;

        }

    }


    @Override public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
