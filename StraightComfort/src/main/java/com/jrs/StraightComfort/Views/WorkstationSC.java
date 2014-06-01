package com.jrs.StraightComfort.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jrs.StraightComfort.R;

import java.util.ArrayList;

/**
 * Created by Steve_2 on 2014-05-21.
 */
public class WorkstationSC extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workstation_sc);
        ListView partsList = (ListView) findViewById(R.id.lvShortcuts);
        ArrayList<String> shortCutsList = new ArrayList<String>();
        String[] shortCuts = new String[] { "Chair", "Desk", "Monitor","Crotch","Chair", "Desk", "Monitor","Crotch","Chair", "Desk", "Monitor","Crotch","Chair", "Desk", "Monitor","Crotch" };
        for (int i = 0; i< shortCuts.length;i++)
        {
            shortCutsList.add(i,shortCuts[i]);
        }

        CustomAdapter listAdapter = new CustomAdapter(this,R.layout.bodypart_check,shortCutsList);

        partsList.setAdapter(listAdapter);

    }

    private class CustomAdapter extends ArrayAdapter<String> {

        private ArrayList<String> shortCutsList;

        public CustomAdapter(Context context, int textViewResourceId,
                             ArrayList<String> shortCutsList) {
            super(context, textViewResourceId, shortCutsList);
            this.shortCutsList = new ArrayList<String>();
            this.shortCutsList.addAll(shortCutsList);
        }

        private class ViewHolder {
            Button SCname;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.shortcut_check, null);

                holder = new ViewHolder();
                holder.SCname  = (Button) convertView.findViewById(R.id.ibFurniture);
                convertView.setTag(holder);

                holder.SCname.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent mIntent = new Intent(getApplicationContext(),FullWorkStation.class);
                        Button mBtnName = (Button) v;
                        Toast.makeText(getApplicationContext(),mBtnName.getText(),Toast.LENGTH_LONG).show();
                        mIntent.putExtra("filter",mBtnName.getText());
                        startActivity(mIntent);
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            String bodyPart = shortCutsList.get(position);
            holder.SCname.setText(bodyPart);


            return convertView;

        }

    }

    @Override public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
}
