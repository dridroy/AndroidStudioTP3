package com.example.tp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

class PlaneteAdapter extends BaseAdapter {

    private String[] planetes = Data.planetes;
    private MainActivity mainActivity;
    private int nbCoche;

    PlaneteAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        nbCoche = 0;
    }

    @Override
    public int getCount() {
        return planetes.length;
    }

    @Override
    public Object getItem(int arg0) {
        return planetes[arg0];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.listitem, null);
        }

        TextView nomPlanete = (TextView) itemView.findViewById(R.id.textView);
        final CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        final Spinner spinner = (Spinner) itemView.findViewById(R.id.spinner);

        nomPlanete.setText(planetes[position]);

        //  installer l'adaptateur pour la liste déroulante (spinner)
        String[] taillePlanetes = Data.taillePlanetes;
        final ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_spinner_item, taillePlanetes);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinadapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox = (CheckBox) compoundButton.findViewById(R.id.checkbox);
                if (checkBox.isChecked()) {
                    spinner.setEnabled(false);
                    spinadapter.notifyDataSetChanged();
                    nbCoche++;
                } else {
                    spinner.setEnabled(true);
                    spinadapter.notifyDataSetChanged();
                    nbCoche--;
                }

                mainActivity.findViewById(R.id.btnVer).setEnabled(nbCoche == planetes.length);
            }
        });

        return itemView;
    }
}