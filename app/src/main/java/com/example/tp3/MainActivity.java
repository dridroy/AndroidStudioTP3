package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    PlaneteAdapter adapter;
    Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView);
        btnVer = (Button) findViewById(R.id.btnVer);
        btnVer.setEnabled(false);

        btnVer.setOnClickListener(btnVerListener);

        adapter = new PlaneteAdapter(MainActivity.this);
        listview.setAdapter(adapter);

    }

    private View.OnClickListener btnVerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int score = 0;
            String[] taillesPlanetes = Data.taillePlanetes;

            for(int i = 0; i < taillesPlanetes.length; i++) {

                Spinner spinner = listview.getChildAt(i).findViewById(R.id.spinner);
                if(spinner.getSelectedItem().toString().equals(taillesPlanetes[i])) score++;
            }

            Toast.makeText(MainActivity.this, "Score " + score + " / " + taillesPlanetes.length, Toast.LENGTH_LONG).show();
        }
    };

}